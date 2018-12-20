package com.chidm.ptit_exam.task.training;

import android.content.Context;
import com.chidm.ptit_exam.common.Constant;
import com.chidm.ptit_exam.task.OnTaskCompleted;

public class AddPersonGroupTask extends TrainingBasicTask<String, String, String> {


    public AddPersonGroupTask(Context context, OnTaskCompleted<String> listener) {
        super(context, listener);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            publishProgress("Syncing with server to add person group...");
            // Start creating person group in server.
            super.faceServiceClient.createLargePersonGroup(
                    params[0],
                    "Person group name",
                    "user data");
            Constant.INDEX = 1;
            return params[0];
        } catch (Exception e) {
            publishProgress(e.getMessage());
            return null;
        }
    }

}
