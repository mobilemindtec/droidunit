package br.com.mobilemind.api.droidunit;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author Ricardo Bocchi
 */
public class HtmlGenerator {

    private Map<Class<?>, List<TestResult>> testFails;
    private StringBuilder html = new StringBuilder();

    public HtmlGenerator(Map<Class<?>, List<TestResult>> testFails) {
        this.testFails = testFails;
    }

    public String build() {

        this.startTag("html");
        this.startTag("head");
        this.startTag("title");        
        this.content("Test Results");
        this.endTag("title");
        this.endTag("head");

        this.startTag("body");

        this.startTag("h1", "color:#006400");
        this.content("Test Result");
        this.endTag("h1");        
        html.append("<HR color='#FF4500'/>");

        Set<Class<?>> results = this.testFails.keySet();
        for (Class<?> c : results) {
            startTag("center");
            startTag("span", "color:#0000CD; font-size:14px; font-weight:bold;");
            content(c.getSimpleName());
            endTag("span");
            endTag("center");
            
            for (TestResult result : this.testFails.get(c)) {
                startTag("span", "font-size:15px; color:" + (result.isFailed() ? "#FF0000" : "#228B22"));
                content(result.getMethodName());
                endTag("span");
                newLine();
                
                for (String msg : result.getMessages()) {
                    startTag("span", "font-size:12px");
                    startTag("b");
                    content("Message: ");
                    endTag("b");
                    content(msg);
                    endTag("span");
                    newLine();
                }

                startTag("span", "font-size:12px; margin-top:5px");
                startTag("b");
                content("Status: ");
                endTag("b");
                content(result.getTestMessage() + " ms");
                endTag("span");
                newLine();

                startTag("span", "font-size:12px; margin-top:5px");
                startTag("b");
                content("Time: ");
                endTag("b");
                content(result.getExecutionTime() + " ms");
                endTag("span");
                newLine();

                startTag("center");
                html.append("<HR color='#FFD700' style='width:150px'/>");
                endTag("center");
            }
            html.append("<HR color='#FF4500'/>");
        }       
        
        this.endTag("body");
        this.endTag("html");
        
        return this.html.toString();
    }

    private void newLine() {
        this.html.append("<br/>");
    }

    private void content(String text) {
        this.html.append(text);
    }

    private void startTag(String tagName) {
        this.startTag(tagName, null);
    }

    private void startTag(String tagName, String cssProperty) {

        this.html.append("<").append(tagName);

        if (cssProperty != null) {
            this.html.append(" style='").append(cssProperty).append("'");
        }

        this.html.append(">");
    }

    private void endTag(String tagName) {
        this.html.append("</").append(tagName).append(">");
    }
}
