package com.chidm.ptit_exam.task.training;

import android.content.Context;
import android.os.AsyncTask;
import com.chidm.ptit_exam.common.Constant;
import com.chidm.ptit_exam.task.BasicTask;
import com.chidm.ptit_exam.task.OnTaskCompleted;
import com.microsoft.projectoxford.face.FaceServiceClient;
import com.microsoft.projectoxford.face.FaceServiceRestClient;

import java.io.IOException;

public abstract class TrainingBasicTask<I, M, O> extends BasicTask<I, M, O> {

    /*
    for AsyncTask
    I: Input
    M: Middle Result
    O: Output
     */

    protected FaceServiceClient faceServiceClient;

    public TrainingBasicTask(Context context, OnTaskCompleted<O> listener) {
        super(context,listener);
        this.faceServiceClient = new FaceServiceRestClient(Constant.API_ENDPOINT, Constant.API_KEY);
    }

}
