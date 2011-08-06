package com.blogspot.fuud.java.tests;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.Assert.assertEquals;

@Ignore
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

        assertEquals("new text", createInConstructor.getClassToMock().getText());

        NewInstanceReplacer.rollbackReplaces();

        assertEquals("original text", createInConstructor.getClassToMock().getText());
    }
}
