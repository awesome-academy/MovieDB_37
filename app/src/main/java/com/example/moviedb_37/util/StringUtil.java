package com.example.moviedb_37.util;

public class StringUtil {
    private StringUtil() {

    }

    public static String append(String... strings){
        StringBuilder stringBuilder = new StringBuilder();
        for (String string:strings) stringBuilder.append(string);
        return stringBuilder.toString();
    }

    public static String getImageLink(int size, String url) {
        return StringUtil.append(Constans.IMAGE_LINK, String.valueOf(size), Constans.SLASH, url);
    }
}
