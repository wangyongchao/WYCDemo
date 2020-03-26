package com.test;

import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;


public class test2 {


    public static void main(String[] args) {

        List<Bean> data = new ArrayList<Bean>();
        //输入一个路径
//        String path = "H:\\livebusiness\\livebusiness\\drawable-xxhdpi";
//        String path = "D:\\tongji\\livevideo-7.10.01" +
//                ".01\\classes\\com\\xueersi\\parentsmeeting\\modules\\livevideo";
        String path = "D:\\tongji\\livebusiness-7.10.01" +
                ".01\\classes\\com\\xueersi\\parentsmeeting\\modules\\livebusiness\\business";
//        String soPath = "D:\\tongji\\app-xesmarket-Release (1)\\lib\\armeabi-v7a";
//        tongjiso(soPath);
        tongjiMode(path);
        System.out.println("end...........");
    }

    public static void tongjiso(String path) {
        File f = new File(path);
        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isFile();
            }
        });

        ArrayList<SizeBean> sizeBeans = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            String subname = file.getName().substring(3);
            System.out.println(subname+ "\t" + file.length());
            sizeBeans.add(new SizeBean(subname, file.length()));
        }
        save(sizeBeans);
    }

    public static void tongjiMode(String path) {
        File f = new File(path);
        File[] files = f.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                return file.isDirectory();
            }
        });
        ArrayList<SizeBean> sizeBeans = new ArrayList<>();
        for (int i = 0; i < files.length; i++) {
            File file = files[i];
            ArrayList<File> filesSize = new ArrayList<>();
            addJava(file, filesSize);
            long modeSize = getModeSize(filesSize);
            System.out.println(file.getName() + "\t" + modeSize);
            sizeBeans.add(new SizeBean(file.getName(), modeSize));
        }
        save(sizeBeans);
    }

    private static long getModeSize(ArrayList<File> files) {
        long sum = 0;
        for (int i = 0; i < files.size(); i++) {
            sum = sum + files.get(i).length();
        }
        return sum;
    }

    public static void addJava(File javaDir, ArrayList<File> java) {
        if (javaDir.isFile()) {
            if (javaDir.getName().endsWith(".class")) {
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

    private static void save(List<SizeBean> data) {
        OutputStream os = null;
        OutputStreamWriter osw = null;
        try {
            //将统计的数据大小统计为文件     路径
            os = new FileOutputStream(new File("E:\\livevideo.csv"));
            osw = new OutputStreamWriter(os, "UTF-8");
            for (SizeBean bean : data) {
                osw.write(bean.getModeName() +  " \t\t" + bean.getSize() + "\r\n");
            }
            osw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (osw != null) {
                try {
                    osw.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void getFile(List<Bean> data, String path) {

        File f = new File(path);
        File[] fs = f.listFiles();

        if (fs == null) {
            return;
        }
        for (File file : fs) {
            //将统计的文件的字节数(单位：B)    方便计算大小
            if (file.isFile()) {
                //getFileSize(file)返回的int最大显示2G
                //System.out.println("文件名："+ file.getName()+",文件大小是："+ getFileSize(file));
                //file.length()可显示完整大小
                System.out.println(file.getName());
                //System.out.println(""+ file.length()/1024);


                data.add(new Bean(file.getParentFile().getAbsolutePath(), file.getName(),
                        (file.length())));
            } else {
                getFile(data, file.getAbsolutePath());
            }
        }
    }

    private static int getFileSize(File f) {
        InputStream fis = null;
        try {
            fis = new FileInputStream(f);
            int docsize = fis.available();
            return docsize;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }

}


class SizeBean {
    private String modeName;
    private long size;

    public SizeBean(String modeName, long size) {
        this.modeName = modeName;
        this.size = size;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }
}

class Bean {
    private String filePath;
    private String fileName;
    private long size;

    public Bean(String filePath, String fileName, long size) {
        this.filePath = filePath;
        this.fileName = fileName;
        this.size = size;
    }
}