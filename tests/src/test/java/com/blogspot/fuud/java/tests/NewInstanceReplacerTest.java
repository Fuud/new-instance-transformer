package com.blogspot.fuud.java.tests;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@RunWith(JMock.class)
public class NewInstanceReplacerTest {
    private final Mockery mockery = new Mockery();
    {
        mockery.setImposteriser(ClassImposteriser.INSTANCE);
    }

    @Test
    public void testReplaceNewInstance() throws Exception {
        final ClassToMock mockClass = mockery.mock(ClassToMock.class);

        NewInstanceReplacer.replaceNewInstance(CreateInConstructor.class, ClassToMock.class, mockClass);

        mockery.checking(new Expectations() {{
            allowing(mockClass).getText();
            will(returnValue("new text"));
        }});

        CreateInConstructor createInConstructor = new CreateInConstructor();

        assertEquals("new text", createInConstructor.getText());

        NewInstanceReplacer.rollbackReplaces();

        createInConstructor = new CreateInConstructor();
        assertEquals("original text", createInConstructor.getText());
    }

    @Test
    public void testReplaceNewInstance_withArgs() throws Exception {
        final ClassToMockWithArgs mockClass = mockery.mock(ClassToMockWithArgs.class);

        NewInstanceReplacer.replaceNewInstance(CreateInConstructorWithArgs.class, ClassToMockWithArgs.class, mockClass);

        mockery.checking(new Expectations() {{
            allowing(mockClass).getText();
            will(returnValue("new text"));
        }});

        CreateInConstructorWithArgs createInConstructor = new CreateInConstructorWithArgs();

        assertEquals("new text", createInConstructor.getText());

        NewInstanceReplacer.rollbackReplaces();

        createInConstructor = new CreateInConstructorWithArgs();
        assertEquals("old text", createInConstructor.getText());
    }
}
