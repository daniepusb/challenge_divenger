package com.pdaniel.swapi.proxy.util;

public class Util {

    private Util() {
        throw new IllegalStateException("Utility class");
    }

    public static String getID(String url) {
        String[] stringArray = url.split("/");
        return stringArray[stringArray.length-1];
    }

}
