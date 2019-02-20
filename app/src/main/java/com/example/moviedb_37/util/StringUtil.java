package com.example.moviedb_37.util;

public class StringUtil {
    private StringUtil() {

    }

    public static String append(String... strings){
        StringBuilder stringBuilder = new StringBuilder() ;
        for (String string:strings) stringBuilder.append(string);
        return stringBuilder.toString();
    }

}
