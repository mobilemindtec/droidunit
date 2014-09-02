package br.com.mobilemind.api.droidunit;

import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import android.content.Context;
import android.util.Log;

class TestRunner {

    private static Map<Class<?>, List<TestResult>> testFails = new HashMap<Class<?>, List<TestResult>>();

    public static void cleanResults() {
        TestRunner.testFails.clear();
    }

    public static String printTestsResult(Context context) throws Exception {
        File outputDir = context.getCacheDir(); // context being the Activity pointer
        File file = File.createTempFile("TestResult", "html", outputDir);
        String html = new HtmlGenerator(testFails).build();
        FileWriter writer = new FileWriter(file);

        try {
            writer.append(html);
            writer.flush();
        } finally {
            writer.close();
        }

        return file.getAbsolutePath();
    }

    public static void run(Class<? extends TestBehavior> clazz, Context context) throws Exception {

        List<Method> testMethods = new LinkedList<Method>();
        Method methods[] = clazz.getMethods();
        TestBehavior testCase = null;

        String testMessage = null;
        long testTime = 0;

        try {
            testCase = clazz.newInstance();
            testCase.setContext(context);
        } catch (Exception e) {
            Log.e("DroidUnit", "can't create instance of ", e);
            throw new RuntimeException("can't create instance of " + clazz.getName());
        }

        for (Method method : methods) {
            if (method.getName().startsWith("test")) {
                testMethods.add(method);
            }
        }

        try {
            testCase.setUpClass();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        for (Method testMethod : testMethods) {
            try {
                testCase.clearMessages();
                testTime = System.currentTimeMillis();
                testCase.setUp();
                testMethod.invoke(testCase);
                testCase.tearDown();
            } catch (Exception e) {
                testMessage = treatException(e);
            }

            if (testMessage == null) {
                testMessage = TestResult.SUCCESS_MESSAGE;
            }

            testTime = System.currentTimeMillis() - testTime;

            TestResult tr = new TestResult(clazz.getName(), testMethod.getName(), testMessage, testTime);
            for (String msg : testCase.getMessage()) {
                tr.getMessages().add(msg);
            }

            testCase.clearMessages();

            if (TestRunner.testFails.containsKey(clazz)) {
                TestRunner.testFails.get(clazz).add(tr);
            } else {
                List<TestResult> trs = new LinkedList<TestResult>();
                trs.add(tr);
                TestRunner.testFails.put(clazz, trs);
            }

            testTime = 0;
            testMessage = null;
        }

        try {
            testCase.tearDownClass();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static String treatException(Exception e) {
        String testMessage = null;

        if (e.getCause() instanceof TestException) {
            testMessage = e.getCause().getMessage();
        } else if (e.getCause() != null) {
            testMessage = e.getCause().getClass().getSimpleName() + ":" + e.getCause().getMessage() + "<br/>";
            for (StackTraceElement st : e.getCause().getStackTrace()) {
                testMessage += st.getClassName() + "." + st.getMethodName() + "=>Line:" + st.getLineNumber() + "<br/>";
            }
        } else {
            if (e.getCause() != null) {
                testMessage = e.getClass().getSimpleName() + ":" + e.getCause().getMessage() == null ? "NULL"
                        : e.getCause().getMessage() + "<br/>";
            } else {
                testMessage = e.getClass().getSimpleName() + ": NULL <br/>";
            }
            for (StackTraceElement st : e.getStackTrace()) {
                testMessage += st.getClassName() + "." + st.getMethodName() + "=>Line:" + st.getLineNumber() + "<br/>";
            }
        }

        return testMessage;
    }
}
