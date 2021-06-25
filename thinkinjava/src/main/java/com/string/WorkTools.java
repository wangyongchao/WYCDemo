package com.string;


/**
 * Created by yh on 2019/11/18
 **/
public class WorkTools {
    public static void main(String[] args) {
        languages();
    }





    private static void languages(){
//        //将XML转为stringname 用,号分隔  方便从服务器上进行多语言下载
//        XmlStringToService.goServiceFormat("strings.xml");
        //从服务器下载的多语言文件的替换
        new ReadServiceLanguages(ReadServiceLanguages.BASE_SRC_PATH,ReadServiceLanguages.BASE_DES_PATH,ReadServiceLanguages.languagesGradle).startCopyFile();
    }
}
