package com.chidm.ptit_exam.task.training;

import android.content.Context;
import com.chidm.ptit_exam.common.Constant;
import com.chidm.ptit_exam.task.BasicTask;
import com.chidm.ptit_exam.task.OnTaskCompleted;

public class TrainPersonGroupTask extends TrainingBasicTask<String, String, String> {

    public TrainPersonGroupTask(Context context, OnTaskCompleted<String> listener) {
        super(context, listener);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            faceServiceClient.trainLargePersonGroup(params[0]);
            Constant.INDEX = 5;
            return params[0];
        } catch (Exception e) {
            publishProgress(e.getMessage());
            return null;
        }
    }

}