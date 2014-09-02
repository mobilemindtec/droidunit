package br.com.mobilemind.api.droidunit;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;

/**
 *
 * @author Ricardo Bocchi
 */
class ProgressBarManager {

    private ProgressDialog progressDialog;
    private final Object progressSyncronize = new Object();
    private View mainView;
    private Context context;

    public ProgressBarManager(Context context, View mainView) {
        super();
        this.mainView = mainView;
        this.context = context;
    }

    public void openProgressDialog() {
        synchronized (this.progressSyncronize) {
            if (this.progressDialog == null) {
                this.progressDialog = this.createProgressDialog();
            }
        }

    }

    public void closeProgressDialog() {
        this.post(new Runnable() {

            @Override
            public void run() {
                synchronized (progressSyncronize) {
                    progressDialog.dismiss();
                    progressDialog = null;
                }
            }
        });
    }

    public ProgressDialog createProgressDialog() {
        return ProgressDialog.show(context, "Droid Unit", "Wait... run unit tests..", true);
    }

    public void post(Runnable run) {
        this.mainView.post(run);
    }

    public ProgressDialog getProressBar() {
        return this.progressDialog;
    }
}
