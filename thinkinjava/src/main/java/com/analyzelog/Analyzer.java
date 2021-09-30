package com.analyzelog;

/**
 * 日志分析程序
 */
public abstract class Analyzer {
    Analyzer mNextAnalyzer;

    public void nextAnalyzer(Analyzer analyzer) {
        this.mNextAnalyzer = analyzer;
    }

    public void begin(Response response) {
        startAnalyzer(response);
        if (mNextAnalyzer != null) {
            mNextAnalyzer.begin(response);
        } else {
//            response.release();
        }
    }

    protected abstract void startAnalyzer(Response response);


}
