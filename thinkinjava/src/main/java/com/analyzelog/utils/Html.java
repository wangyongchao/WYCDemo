package com.analyzelog.utils;

public class Html {
    public static String header = "<head>\n" +
            "\n" +
            "    <meta charset=\"utf-8\"/>\n" +
            "\n" +
            "\n" +
            "    <title>%s</title>\n" +
            "\n" +
            "\n" +
            "    <style>\n" +
            "\n" +
            "        pre {outline: 1px solid #ccc; padding: 5px; margin: 5px; }\n" +
            "\n" +
            "        .string { color: green; }\n" +
            "\n" +
            "        .number { color: darkorange; }\n" +
            "\n" +
            "        .boolean { color: blue; }\n" +
            "\n" +
            "        .null { color: magenta; }\n" +
            "\n" +
            "        .key { color: red; }\n" +
            "\n" +
            "\n" +
            "    </style>\n" +
            "\n" +
            "    <script type=\"text/javascript\">\n" +
            "\n" +
            "    function syntaxHighlight(json) {\n" +
            "\n" +
            "        if (typeof json != 'string') {\n" +
            "\n" +
            "            json = JSON.stringify(json, undefined, 2);\n" +
            "\n" +
            "        }\n" +
            "\n" +
            "        json = json.replace(/&/g, '&').replace(/</g, '<').replace(/>/g, '>');\n" +
            "\n" +
            "        return json.replace(/(\"(\\\\u[a-zA-Z0-9]{4}|\\\\[^u]|[^\\\\\"])*\"(\\s*:)" +
            "?|\\b(true|false|null)\\b|-?\\d+(?:\\.\\d*)?(?:[eE][+\\-]?\\d+)?)/g, function(match)" +
            " {\n" +
            "\n" +
            "            var cls = 'number';\n" +
            "\n" +
            "            if (/^\"/.test(match)) {\n" +
            "\n" +
            "                if (/:$/.test(match)) {\n" +
            "\n" +
            "                    cls = 'key';\n" +
            "\n" +
            "                } else {\n" +
            "\n" +
            "                    cls = 'string';\n" +
            "\n" +
            "                }\n" +
            "\n" +
            "            } else if (/true|false/.test(match)) {\n" +
            "            \n" +
            "                cls = 'boolean';\n" +
            "                \n" +
            "            } else if (/null/.test(match)) {\n" +
            "            \n" +
            "                cls = 'null';\n" +
            "                \n" +
            "            }\n" +
            "            return '<span class=\"' + cls + '\">' + match + '</span>';\n" +
            "\n" +
            "        });\n" +
            "    }\n" +
            "\n" +
            "    </script>\n" +
            "\n" +
            "</head>\n" +
            "<body>";

    public static final String pre_content = "<pre id=\"%s\">\n" +
            "\n" +
            "</pre>";

    public static final String scirpt1 = "\n" +
            "<script type=\"text/javascript\">";

    public static final String data = "var %s = %s ";
    public static final String result = " document.getElementById('%s').innerHTML = \"%s\\r\\n\"" +
            "+syntaxHighlight(%s);";

    public static final String last = "</script>\n" +
            "\n" +
            "\n" +
            "</body>";


}
