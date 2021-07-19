package com.string;

import java.io.File;

/**
 * Created by ZJSoft on 2019/2/25.
 */

public class BuildDiscoverIdsMain {
    /**
     * 程序生成后的json  会多一个 ,  请自行去除
     * @param args
     */
    public static void main(String[] args){
        //文件目录
        String path = "/Users/wangyongchao/Downloads/yoga_for_weight_loss_20210714162245_workouts";
        File file = new File(path);
        String[] names = file.list();
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for (String name : names){
            name = name.replace(".zip","");
            String[] tmp = name.split("_");
            String id = tmp[0];
            String version = tmp[1];
            sb.append("\""+id+"\":"+version+",");
        }
        sb.append("}");
        System.out.println(sb.toString());
    }

}
