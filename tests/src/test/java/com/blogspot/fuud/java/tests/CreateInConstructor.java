package com.blogspot.fuud.java.tests;

public class CreateInConstructor {

    private ClassToMock classToMock;

    public CreateInConstructor() {
        classToMock = new ClassToMock();
    }

    public ClassToMock getClassToMock() {
        return classToMock;
    }
}
