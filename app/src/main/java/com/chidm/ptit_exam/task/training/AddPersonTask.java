package com.chidm.ptit_exam.task.training;

import android.content.Context;
import android.os.AsyncTask;
import com.chidm.ptit_exam.common.Constant;
import com.chidm.ptit_exam.task.BasicTask;
import com.chidm.ptit_exam.task.OnTaskCompleted;
import com.microsoft.projectoxford.face.FaceServiceClient;
import com.microsoft.projectoxford.face.FaceServiceRestClient;
import com.microsoft.projectoxford.face.contract.CreatePersonResult;

public class AddPersonTask extends TrainingBasicTask<String, String, String> {
    // Indicate the next step is to add face in this person, or finish editing this person.

    private String personId;

    public AddPersonTask(Context context, OnTaskCompleted<String> listener) {
        super(context, listener);
    }


    @Override
    protected String doInBackground(String... params) {
        // Get an instance of face service client.
        try {
            publishProgress("Syncing with server to add person...");
            // Start the request to creating person.
            CreatePersonResult createPersonResult =
                    super.faceServiceClient.createPersonInLargePersonGroup(
                            params[0],
                            "Person name", "User data");
            Constant.INDEX = 2;
            return createPersonResult.personId.toString();
        } catch (Exception e) {
            publishProgress(e.getMessage());
            return null;
        }
    }

}