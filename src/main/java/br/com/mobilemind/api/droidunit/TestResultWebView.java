package br.com.mobilemind.api.droidunit;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.ContextMenu.ContextMenuInfo;
import android.webkit.WebView;
import android.widget.LinearLayout;

public class TestResultWebView extends Activity {

    private ProgressBarManager progressBarManager;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        setContentView(linearLayout);


        this.webView = new WebView(this);

        this.webView.setLayoutParams(new ViewGroup.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.FILL_PARENT));
        linearLayout.addView(this.webView);


        this.progressBarManager = new ProgressBarManager(this, this.webView);
        this.webView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        runTests();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Run again").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem mi) {
                runTests();
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    private void runTests() {
        this.progressBarManager.openProgressDialog();


        new Thread(new Runnable() {

            @Override
            public void run() {
                try {

                    try {
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }

                    TestSuite.runTestSuite(TestResultWebView.this);
                    String htmlFileLocation = null;
                    try {
                        htmlFileLocation = TestSuite.printTestsResults(getApplication());
                    } catch (Exception e) {
                        Log.d("TestResultWebView", e.getMessage(), e);
                    }

                    final String htmlFile = htmlFileLocation;

                    progressBarManager.post(new Runnable() {

                        @Override
                        public void run() {
                            if (htmlFile != null) {
                                webView.loadUrl("file:///" + htmlFile);
                            } else {
                                webView.loadUrl("http://www.mobilemind.com.br");
                            }
                        }
                    });
                } finally {
                    progressBarManager.closeProgressDialog();
                }
            }
        }).start();
    }
}
