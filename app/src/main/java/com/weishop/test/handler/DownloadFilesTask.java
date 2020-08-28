package com.weishop.test.handler;


import java.net.URL;
import java.util.concurrent.TimeUnit;

public class DownloadFilesTask extends AsyncTask<String, Integer, Long> {
    @Override
    protected Long doInBackground(String... urls) {
        System.out.println("urls=" + urls[0]);
        long totalSize = 0;
        try {
            Thread.sleep(2000);
            totalSize = 1000;
            publishProgress(21);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return totalSize;
    }

    /**
     * 开始执行前
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        System.out.println("onPreExecute" + Thread.currentThread().getName());
    }

    /**
     * 执行完成后
     *
     * @param aLong
     */
    @Override
    protected void onPostExecute(Long aLong) {
        super.onPostExecute(aLong);
        System.out.println("onPostExecute " + Thread.currentThread().getName());
    }

    /**
     * 发布进度
     *
     * @param values
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        System.out.println("onProgressUpdate=" + values[0] + "," + Thread.currentThread().getName());
    }

    @Override
    protected void onCancelled(Long aLong) {
        super.onCancelled(aLong);
        System.out.println("onCancelled along" + Thread.currentThread().getName());
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
        System.out.println("onCancelled" + Thread.currentThread().getName());
    }
}
