package com.blogspot.fuud.java.tests;

public class CreateInConstructorWithArgs {

    private ClassToMockWithArgs classToMock;

    public CreateInConstructorWithArgs() {
        classToMock = new ClassToMockWithArgs("old text");
    }

    public String getText() {
        return classToMock.getText();
    }
}
