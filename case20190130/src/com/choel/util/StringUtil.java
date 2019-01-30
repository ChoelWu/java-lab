package com.choel.util;

public class StringUtil {
    public static boolean validateNull(String args) {
        if (args == null || args.length() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static String changeNull(String source, String target) {
        if (source == null || source.length() == 0 || source.equalsIgnoreCase("null")) {
            return target;
        } else {
            return source;
        }
    }

    public static String filterHtml(String html) {
        if(html == null) {
            return null;
        }
        if(html.length() == 0) {
            return html;
        }
        html = html.replaceAll("&", "&amp;");
        html = html.replaceAll(" ", "&nbsp;");
        html = html.replaceAll("'", "&#39;");
        html = html.replaceAll("\"", "&quot;");
        html = html.replaceAll("<", "&lt;");
        html = html.replaceAll(">", "&gt;");
        html = html.replaceAll("\n", "<br>");
        return html;
    }
}
