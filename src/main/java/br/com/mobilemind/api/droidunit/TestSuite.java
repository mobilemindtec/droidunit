package br.com.mobilemind.api.droidunit;

import android.content.Context;
import android.util.Log;
import java.util.HashSet;
import java.util.Set;

/**
 * Ricardo Bocchi
 * 
 * @author ricardo
 */
public class TestSuite {

    private static Set<Class<? extends TestBehavior>> testsCase = new HashSet<Class<? extends TestBehavior>>();

    /**
     * Add Test Class
     * 
     * @param cls 
     */
    public static void addTestCase(Class<? extends TestBehavior> cls) {
        if (!testsCase.contains(cls)) {
            testsCase.add(cls);
        }
    }

    /**
     * Print results in HTML format
     * 
     * @param context
     * @return
     * @throws Exception 
     */
    public static String printTestsResults(Context context) throws Exception {
        String testsResult = TestRunner.printTestsResult(context);
        TestRunner.cleanResults();
        return testsResult;
    }

    /**
     * Run unit tests
     * 
     */
    public static void runTestSuite(Context context) {
        for (Class<? extends TestBehavior> clazz : TestSuite.testsCase) {
            try {
                TestRunner.run(clazz, context);
            } catch (Exception e) {
                Log.d("TestSuite error", e.getMessage() == null ? "" : e.getMessage(), e);
            }
        }
    }
}
