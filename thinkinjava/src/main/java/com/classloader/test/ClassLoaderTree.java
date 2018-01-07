
package com.classloader.test;

import java.lang.reflect.Method;

public class ClassLoaderTree {

    public static void main(String[] args) {
//        ClassLoader loader = ClassLoaderTree.class.getClassLoader();
//        while (loader != null) {
//            System.out.println(loader.toString());
//            loader = loader.getParent();
//        }

        testClassIdentity();
//        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
//        System.out.println(systemClassLoader);

    }

    public static void testClassIdentity() {
        String classDataRootPath = "/Users/wangyongchao/datums";
        FileSystemClassLoader fscl1 = new FileSystemClassLoader(classDataRootPath);
        System.out.println(fscl1.getParent());
        FileSystemClassLoader fscl2 = new FileSystemClassLoader(classDataRootPath);
        String className = "com.classloader.test.Sample";
        try {
            Class<?> class1 = fscl1.loadClass(className);
            Object obj1 = class1.newInstance();
            System.out.println(obj1.toString());
            Class<?> class2 = fscl2.loadClass(className);
            System.out.println(class1+","+class2);
            Object obj2 = class2.newInstance();
            System.out.println(obj2.toString());
            Method setSampleMethod = class1.getMethod("setSample", java.lang.Object.class);
            setSampleMethod.invoke(obj1, obj2);
            System.out.println("dfasd");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
