package br.com.mobilemind.api.droidunit;

import java.util.ArrayList;
import java.util.List;

/**
 * Ricardo Bocchi
 *
 * @author ricardo
 */
class TestResult {

    public static final String SUCCESS_MESSAGE = "test passed";
    private String className;
    private String methodName;
    private String testMessage;
    private long executionTime;
    private List<String> messages= new ArrayList<String>();

    public TestResult() {
    }

    public TestResult(String className, String methodName, String testMessage, long executionTime) {
        super();
        this.className = className;
        this.methodName = methodName;
        this.testMessage = testMessage;
        this.executionTime = executionTime;
    }

    public String getClassName() {
        return className;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getTestMessage() {
        return testMessage;
    }

    public boolean isFailed() {
        return !this.testMessage.equals(TestResult.SUCCESS_MESSAGE);
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public void setTestMessage(String testMessage) {
        this.testMessage = testMessage;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    @Override
    public String toString() {
        return this.className + "." + this.methodName;
    }
}
