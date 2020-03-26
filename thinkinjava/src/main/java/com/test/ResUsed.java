package com.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Set;

public class ResUsed {

	public static void main(String[] args) {
		ResUsed res = new ResUsed();
		res.checkRes();
	}

	public void checkRes() {
//		File javaDir = new File(
//				"/Users/linyuqiang/AndroidStudioProjects/feature_record_live/xueersiwangxiao/business/livevideo/src/main");
		String dir = "E:\\workspace\\xueersiwangxiao_other\\xueersiwangxiao\\business-base\\livevideo";
		File javaDir = new File(dir);
//		File xmlDir = new File(
//				"/Users/linyuqiang/AndroidStudioProjects/feature_record_live/xueersiwangxiao/business/livevideo/src/main/res");
//		File resDir = new File(
//				"/Users/linyuqiang/AndroidStudioProjects/feature_record_live/xueersiwangxiao/business/livevideo/src/main");
		File resDir = new File(dir);
		File layoutDir = new File(dir);
		long alltotal = 0;
		ArrayList<DrawEntity> findres = new ArrayList<DrawEntity>();
		ArrayList<DrawEntity> res = new ArrayList<DrawEntity>();
		{
			ArrayList<File> bitmaps = new ArrayList<File>();
			ArrayList<DrawEntity> xmlFile = new ArrayList<DrawEntity>();
			addDrawableDir(resDir, bitmaps);
			System.out.println("bitmaps=" + bitmaps.size());
			for (int i = 0; i < bitmaps.size(); i++) {
				File drawDir = bitmaps.get(i);
				File[] files = drawDir.listFiles();
				for (int j = 0; j < files.length; j++) {
					File f = files[j];
//					if (!f.getName().startsWith("livevideo_enpk_toast_chidao_bg_normal")) {
//						continue;
//					}
					alltotal += f.length();
					DrawEntity drawEntity = new DrawEntity();
					drawEntity.file = f;
					if (f.getName().endsWith(".xml")) {
//						System.out.println("chongfu:draw=" + f);
						drawEntity.type = 1;
						xmlFile.add(drawEntity);
					}
//					if (f.getPath().contains("bg_live_ke_energy_quanli_icon_normal.png")) {
//						System.out.println("addDrawableLayout:path=" + f.getPath());
//					}
					res.add(drawEntity);
					findres.add(drawEntity);
				}
			}
			{
				for (int i = 0; i < xmlFile.size(); i++) {
					DrawEntity drawEntity = xmlFile.get(i);
					File file = drawEntity.file;
					ArrayList<String> drawables = readFromResFile(file);
					int size = drawables.size();
					for (int j = 0; j < size; j++) {
						String drawableling = drawables.get(j);
						int index = drawableling.indexOf("\"");
						if (index != -1) {
							drawableling = drawableling.substring(0, index);
						} else {
							index = drawableling.indexOf("<");
							if (index != -1) {
								drawableling = drawableling.substring(0, index);
							}
						}
						boolean remove = false;
						for (int r = 0; r < res.size(); r++) {
							DrawEntity drawEntity2 = res.get(r);
							File re = drawEntity2.file;
							if (re.getName().startsWith(drawableling)) {
								res.remove(drawEntity2);
								remove = true;
								drawEntity.size += re.length();
								drawEntity2.tagobj = drawEntity;
								r--;
								System.out.println(
										"drawable_xml\t" + remove + "\t" + drawableling + ",size=" + drawEntity.size);
//								break;
							}
						}
//						System.out.println(drawableling);
					}
				}
			}
		}
		int oldres = res.size();
		ArrayList<XmlSize> xmlSizes = new ArrayList<XmlSize>();
		{
			ArrayList<File> xmls = new ArrayList<File>();
			addLayout(layoutDir, xmls);
			System.out.println(xmls.size());
			for (int i = 0; i < xmls.size(); i++) {
				File xmlFile = xmls.get(i);
//				if (!xmlFile.getName().startsWith("layout_livevideo_en_team_join.xml")) {
//					continue;
//				}
				XmlSize xmlSize = new XmlSize();
				xmlSize.filename = xmlFile.getName();
				xmlSizes.add(xmlSize);
				ArrayList<String> drawables = readFromResFile(xmlFile);
				int size = drawables.size();
				for (int j = 0; j < size; j++) {
					String drawableling = drawables.get(j);
					int index = drawableling.indexOf("\"");
					if (index != -1) {
						drawableling = drawableling.substring(0, index);
					} else {
						index = drawableling.indexOf("<");
						if (index != -1) {
							drawableling = drawableling.substring(0, index);
						}
					}
					boolean remove = false;
					for (int r = 0; r < res.size(); r++) {
						DrawEntity drawEntity = res.get(r);
						File re = drawEntity.file;
						if (re.getName().startsWith(drawableling)) {
							res.remove(drawEntity);
							remove = true;
							xmlSize.size += re.length();
							drawEntity.tagobj = xmlSize;
							r--;
//							break;
						}
					}
					System.out.println("drawableling\t" + remove + "\t" + drawableling + ",xml=" + xmlSize.size);
//					System.out.println(drawableling);
				}
			}
		}
		int oldres2 = xmlSizes.size();
		HashMap<String, FileSize> modules = new HashMap<String, FileSize>();
		{
			ArrayList<File> java = new ArrayList<File>();
			addJava(javaDir, java);
			for (int i = 0; i < java.size(); i++) {
				File javaFile = java.get(i);
				String mode = javaFile.getPath();
				String findStr2 = "modules\\livevideo";
				int index2 = mode.indexOf(findStr2);
				if (index2 != -1) {
					mode = mode.substring(index2 + findStr2.length() + 1);
					index2 = mode.indexOf("\\");
					if (index2 != -1) {
						mode = mode.substring(0, index2);
					} else {
						continue;
					}
				} else {
					continue;
				}
				ArrayList<String> drawables = readFromJavaFile(javaFile);
				ArrayList<String> reaLayouts = readFromJavaFileLayout(javaFile);
				int size = drawables.size();
				if (size > 0) {
//					System.out.println(drawDir.getName());
				}
				for (int j = 0; j < size; j++) {
					String drawableling = drawables.get(j);
					while (true) {
//						System.out.println("drawable\t" + drawableling);
						String findStr = "R.drawable.";
						int index = drawableling.indexOf(findStr);
						if (index != -1) {
							String drawable = drawableling.substring(index + findStr.length());
							drawableling = drawable;
							index = drawable.indexOf(",");
							if (index != -1) {
								drawable = drawable.substring(0, index);
							} else {
								index = drawable.indexOf(")");
								if (index != -1) {
									drawable = drawable.substring(0, index);
								} else {
									index = drawable.indexOf("}");
									if (index != -1) {
										drawable = drawable.substring(0, index);
									} else {
										index = drawable.indexOf(";");
										if (index != -1) {
											drawable = drawable.substring(0, index);
										}
									}
								}
							}
							boolean remove = false;
							long total = 0;
							int times = 0;
							for (int r = 0; r < res.size(); r++) {
								DrawEntity drawEntity = res.get(r);
								File re = drawEntity.file;
//								if (re.getPath().contains("bg_live_ke_energy_quanli_icon_normal.png")) {
//									System.out.println("addDrawableLayout2:path=" + re.getPath());
//								}
								if (re.getName().startsWith(drawable)) {
									res.remove(drawEntity);
									total += re.length();
									if (drawEntity.type == 1) {
										total += re.length() + drawEntity.size;
									}
									remove = true;
									times++;
									drawEntity.tagobj = javaFile;
									r--;
//									break;
								}
							}
//							System.out.println("drawable1\t" + remove + ",name=" + javaFile.getName() + "\t" + drawable
//									+ ",javaFile=" + javaFile);
							if (remove) {
								FileSize ressize = modules.get(mode);
								if (ressize == null) {
									ressize = new FileSize();
									modules.put(mode, ressize);
								}
								ressize.size += total;
							}
						} else {
//							System.out.println("drawable2\t" + drawableling);
							break;
						}
					}
				}
				size = reaLayouts.size();
//				System.out.println("size2=" + size);
				for (int j = 0; j < size; j++) {
					String layoutStr = reaLayouts.get(j);
					while (true) {
//						System.out.println("drawable\t" + drawableling);
						String findStr = "R.layout.";
						int index = layoutStr.indexOf(findStr);
						if (index != -1) {
							String layout = layoutStr.substring(index + findStr.length());
							layoutStr = layout;
							index = layout.indexOf(",");
							if (index != -1) {
								layout = layout.substring(0, index);
							} else {
								index = layout.indexOf(")");
								if (index != -1) {
									layout = layout.substring(0, index);
								} else {
									index = layout.indexOf("}");
									if (index != -1) {
										layout = layout.substring(0, index);
									} else {
										index = layout.indexOf(";");
										if (index != -1) {
											layout = layout.substring(0, index);
										}
									}
								}
							}
//							System.out.println("layoutStr=" + layout);
							boolean remove = false;
							for (int r = 0; r < xmlSizes.size(); r++) {
								XmlSize re = xmlSizes.get(r);
								if (re.filename.startsWith(layout)) {
									xmlSizes.remove(r);
									remove = true;
									if (remove) {
										FileSize ressize = modules.get(mode);
										if (ressize == null) {
											ressize = new FileSize();
											modules.put(mode, ressize);
										}
										ressize.size2 += re.size;
										System.out.println("mode=" + mode + "\t" + layout + ",size=" + re.size);
									}
									r--;
//									break;
								}
							}
//							System.out.println("drawable1\t" + remove + "\t" + layout);
						} else {
//							System.out.println("drawable2\t" + drawableling);
							break;
						}
					}
				}
			}
		}
		int newSize = res.size();
		System.out.println("oldres=" + oldres + "\t" + newSize);
		System.out.println("oldres2=" + oldres2 + "\t" + xmlSizes.size());
		long lastTotal = 0;
		for (int i = 0; i < xmlSizes.size(); i++) {
			XmlSize xmlSize = xmlSizes.get(i);
			if (xmlSize.size != 0) {
				lastTotal += xmlSize.size;
				System.out.println(
						"xmlname=" + xmlSize.filename + "\t" + xmlSize.size + "\t" + FormetFileSize(xmlSize.size));
			}
		}
		Collections.sort(res, new Comparator<DrawEntity>() {

			@Override
			public int compare(DrawEntity o1, DrawEntity o2) {
				return (int) (o2.file.length() - o1.file.length());
			}
		});
		int maxSize = Math.min(50, newSize);

		for (int i = 0; i < newSize; i++) {
			File re = res.get(i).file;
			if (re.getName().endsWith("xml")) {
//				tota1 += re.length();
			} else {
//				System.out.println("res\t" + re.length() + "\t" + re);
				lastTotal += re.length();
//				re.delete();
			}
		}
		System.out.println("-------------------");
		Set<String> keys = modules.keySet();
		long total = 0;
		long total2 = 0;
		for (String key : keys) {
			FileSize fileSize = modules.get(key);
			long size = fileSize.size + fileSize.size2;
			if (size == 0) {
				continue;
			}
			total += fileSize.size;
			total2 += fileSize.size2;
//			System.out.println("modules\t" + key + "\t" + FormetFileSize(fileSize.size));
//			System.out.println("" + key + "\t&\t" + FormetFileSize(fileSize.size));
//			System.out.println("" + key + "\t&\t" + size + "&" + FormetFileSize(size));
			System.out.println("" + key + "\t" + size + "\t" + FormetFileSize(size));
		}
		System.out.println("size=" + modules.size() + "\t" + FormetFileSize(total) + "\t" + FormetFileSize(total2)
				+ "\t" + FormetFileSize(total + total2) + "\t" + FormetFileSize(lastTotal) + "\t"
				+ FormetFileSize(alltotal));
		for (int i = 0; i < findres.size(); i++) {
			DrawEntity drawEntity = findres.get(i);
			Object tagobj = drawEntity.tagobj;
			if (tagobj == null) {
//				System.out.println("" + drawEntity.file.getName() + ",obj=" + tagobj);
			}
		}
	}

	public static String FormetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	public static ArrayList<String> readFromJavaFile(File file) {
		BufferedReader bufferedReader = null;
		ArrayList<String> drawables = new ArrayList<String>();
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader(file));
			boolean addnexLinex = false;
			String lastLine = "";
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				if (addnexLinex) {
					addnexLinex = false;
					line = lastLine + line.trim();
//					System.out.println("readFromJavaFile2:line=" + line);
					drawables.add(line);
				} else {
					String findStr = "R.drawable";
					int index = line.indexOf(findStr);
					if (index != -1) {
						if (line.length() - (index + findStr.length()) < 3) {
//							System.out.println("readFromJavaFile:line=" + line);
							addnexLinex = true;
							lastLine = line;
						} else {
							drawables.add(line);
						}
					}
					if (line.endsWith("R")) {
//						System.out.println("readFromJavaFile1:line=" + line);
						addnexLinex = true;
						lastLine = line;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return drawables;
	}

	public static ArrayList<String> readFromResFile(File file) {
		BufferedReader bufferedReader = null;
		ArrayList<String> drawables = new ArrayList<String>();
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader(file));
			while ((line = bufferedReader.readLine()) != null) {
				String findStr = "@drawable/";
				int index = line.indexOf(findStr);
				if (index != -1) {
					line = line.substring(index + findStr.length());
					drawables.add(line);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return drawables;
	}

	public static void addJava(File javaDir, ArrayList<File> java) {
		if (javaDir.isFile()) {
			if (javaDir.getName().endsWith(".java")) {
//				if (javaDir.getName().equals("BaseLiveMessagePager.java"))
				java.add(javaDir);
			}
		} else {
			File[] list = javaDir.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					File drawDir = list[i];
					addJava(drawDir, java);
				}
			} else {
//				System.out.println("addJava:javaDir=" + javaDir);
			}
		}
	}

	public static void addLayout(File javaDir, ArrayList<File> java) {
//		System.out.println("" + javaDir);
		if (javaDir.isFile()) {
			if (javaDir.getName().endsWith(".xml") && javaDir.getPath().contains("layout")) {
//				System.out.println("addLayout:javaDir=" + javaDir);
//				if (javaDir.getName().equals("pager_stand_live_roleplayer.xml"))
				java.add(javaDir);
			}
		} else {
			File[] list = javaDir.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					File drawDir = list[i];
					addLayout(drawDir, java);
				}
			}
		}
	}

	public static void addDrawableDir(File javaDir, ArrayList<File> bitmaps) {
		if (javaDir.isFile()) {
			return;
		}
		if (javaDir.getName().startsWith("drawable")) {
			bitmaps.add(javaDir);
		} else {
			File[] list = javaDir.listFiles();
			if (list != null) {
				for (int i = 0; i < list.length; i++) {
					File drawDir = list[i];
					addDrawableDir(drawDir, bitmaps);
				}
			} else {
//				System.out.println("addDrawableLayout:javaDir=" + javaDir);
			}
		}
	}

//	public static void addLayout(File javaDir, ArrayList<File> java) {
//		if (javaDir.isFile()) {
//			return;
//		}
//		if (javaDir.getName().startsWith("layout")) {
//			java.add(javaDir);
//		} else {
//			File[] list = javaDir.listFiles();
//			if (list != null) {
//				for (int i = 0; i < list.length; i++) {
//					File drawDir = list[i];
//					addDrawableLayout(drawDir, java);
//				}
//			} else {
////				System.out.println("addDrawableLayout:javaDir=" + javaDir);
//			}
//		}
//	}

	public static ArrayList<String> readFromJavaFileLayout(File file) {
		BufferedReader bufferedReader = null;
		ArrayList<String> drawables = new ArrayList<String>();
		try {
			String line;
			bufferedReader = new BufferedReader(new FileReader(file));
			boolean addnexLinex = false;
			String lastLine = "";
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				if (addnexLinex) {
					addnexLinex = false;
					line = lastLine + line.trim();
//					System.out.println("readFromJavaFile2:line=" + line);
					drawables.add(line);
				} else {
					String findStr = "R.layout";
					int index = line.indexOf(findStr);
					if (index != -1) {
						if (line.length() - (index + findStr.length()) < 3) {
//							System.out.println("readFromJavaFile:line=" + line);
							addnexLinex = true;
							lastLine = line;
						} else {
							drawables.add(line);
						}
					}
					if (line.endsWith("R")) {
//						System.out.println("readFromJavaFile1:line=" + line);
						addnexLinex = true;
						lastLine = line;
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return drawables;
	}

	static class DrawEntity {
		File file;
		int type;
		long size;
		Object tagobj;

		@Override
		public boolean equals(Object obj) {
			if (!(obj instanceof DrawEntity)) {
				return false;
			}
			DrawEntity other = (DrawEntity) obj;
			return other.file.equals(file);
		}
	}

	static class XmlSize {
		String filename;
		long size;
	}

	static class FileSize {
		long size;
		long size2;
	}
}
