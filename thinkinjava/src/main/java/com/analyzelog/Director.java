package com.analyzelog;

public class Director {
    private static String fileName = "D:/logfile/2020_03_23_20758941_15912403790$android.txt";

    public static void main(String[] args) {

        FileAnalyzer analyzer = new FileAnalyzer(fileName);
        analyzer.startAnalyzer();

        JsonAnalyzer jsonAnalyzer = new JsonAnalyzer(analyzer.getJonsStrings());
        jsonAnalyzer.startAnalyzer();

        ProcessAnalyzer processAnalyzer = new ProcessAnalyzer(jsonAnalyzer.getLogBeans());
        processAnalyzer.setEventId("live_debug_message");
        processAnalyzer.setTAG("LiveBll2");
        processAnalyzer.startAnalyzer();

        OutputAnalyzer outputAnalyzer = new OutputAnalyzer(processAnalyzer.getLogBeans(),
                processAnalyzer.getTagLogBeans(),
                processAnalyzer.getEventIdLogBeans(), fileName, processAnalyzer.getTAG(),
                processAnalyzer.getEventId());
        outputAnalyzer.startAnalyzer();
    }
}
