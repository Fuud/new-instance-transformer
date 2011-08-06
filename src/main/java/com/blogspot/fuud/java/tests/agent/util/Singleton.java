package com.blogspot.fuud.java.tests.agent.util;

public class Singleton {
    private static Class clazz;

    public static Class getClazz() {
        return clazz;
    }

    public static void setClazz(Class clazz) {
        Singleton.clazz = clazz;
    }
}
