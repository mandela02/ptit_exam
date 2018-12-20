package com.chidm.ptit_exam.task.training;

import android.content.Context;
import com.chidm.ptit_exam.common.Constant;
import com.chidm.ptit_exam.task.BasicTask;
import com.chidm.ptit_exam.task.OnTaskCompleted;
import com.microsoft.projectoxford.face.contract.Face;

import java.io.InputStream;

public class DetectionTask extends TrainingBasicTask<InputStream, String, Face[]> {


    public DetectionTask(Context context, OnTaskCompleted<Face[]> listener) {
        super(context, listener);
    }


    @Override
    protected Face[] doInBackground(InputStream... params) {
        // Get an instance of face service client to detect faces in image.
        try {
            publishProgress("Detecting...");
            // Start detection.
            if(Constant.INDEX == 2) {
                Constant.INDEX = 3;
            } else {
                Constant.INDEX = 6;
            }

            return super.faceServiceClient.detect(
                    params[0],  /* Input stream of image to detect */
                    true,       /* Whether to return face ID */
                    false,       /* Whether to return face landmarks */
                        /* Which face attributes to analyze, currently we support:
                           age,gender,headPose,smile,facialHair */
                    null);
        } catch (Exception e) {
            publishProgress(e.getMessage());
            return null;
        }
    }

}