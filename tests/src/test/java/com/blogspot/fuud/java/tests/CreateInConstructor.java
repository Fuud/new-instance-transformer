package com.blogspot.fuud.java.tests;

public class CreateInConstructor {

    private ClassToMock classToMock;

    public CreateInConstructor() {
        classToMock = new ClassToMock();
    }

    public String getText() {
        return classToMock.getText();
    }
}
