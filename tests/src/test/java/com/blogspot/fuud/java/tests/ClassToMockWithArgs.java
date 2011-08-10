package com.blogspot.fuud.java.tests;

public class ClassToMockWithArgs {
    private final String text;

    public ClassToMockWithArgs(String text) {
        this.text = text;
    }

    public String getText(){
        return text;
    }
}
