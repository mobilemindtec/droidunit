package br.com.mobilemind.api.droidunit;

import android.content.Context;
import java.util.LinkedList;
import java.util.List;

/**
 *
 *
 * @author Ricardo Bocchi
 */
public abstract class TestBehaviorImpl implements TestBehavior {

    private List<String> message = new LinkedList<String>();
    private Context context;

    @Override
    public void setUp() throws Exception {
    }

    @Override
    public void setUpClass() throws Exception {
    }

    @Override
    public void tearDown() throws Exception {
    }

    @Override
    public void tearDownClass() throws Exception {
    }

    @Override
    public void say(String message) {
        this.message.add(message);
    }

    @Override
    public void clearMessages() {
        this.message.clear();
    }

    @Override
    public List<String> getMessage() {
        return message;
    }

    @Override
    public void setContext(Context context) {
        this.context = context;
    }

    @Override
    public Context getContext() {
        return context;
    }
}
