package com.blogspot.fuud.java.tests;

import com.blogspot.fuud.java.tests.agent.AgentInitialization;
import com.blogspot.fuud.java.tests.agent.InstrumentationHolder;
import com.blogspot.fuud.java.tests.agent.MyClassFileTransformer;

import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;
import java.util.ArrayList;
import java.util.List;

public class NewInstanceReplacer {
    private static final List<ClassFileTransformer> transformers = new ArrayList<ClassFileTransformer>();
    private static final List<Class> modifiedClasses = new ArrayList<Class>();

    public static synchronized void replaceNewInstance(Class whereReplace, Class whatReplace, Object replacement) {
        AgentInitialization.initialize();
        try {
            Instrumentation instrumentation = InstrumentationHolder.getInstrumentation();
            MyClassFileTransformer transformer = new MyClassFileTransformer(whereReplace.getName(), whatReplace.getName(), replacement);
            transformers.add(transformer);
            modifiedClasses.add(whereReplace);
            instrumentation.addTransformer(transformer, true);
            instrumentation.retransformClasses(whereReplace);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized void rollbackReplaces() {
        AgentInitialization.initialize();

        Instrumentation instrumentation = InstrumentationHolder.getInstrumentation();
        for (ClassFileTransformer transformer : transformers) {
            instrumentation.removeTransformer(transformer);
        }
        transformers.clear();
        try {
            instrumentation.retransformClasses(modifiedClasses.toArray(new Class<?>[modifiedClasses.size()]));
        } catch (UnmodifiableClassException e) {
            throw new RuntimeException(e);
        }
    }
}
