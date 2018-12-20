package com.chidm.ptit_exam.task.validation;

import android.content.Context;
import android.util.Log;
import com.chidm.ptit_exam.task.OnTaskCompleted;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import static android.support.constraint.Constraints.TAG;

public class StudentValidation extends ValidationTask<String, Void, Boolean>{

    public StudentValidation(Context context, OnTaskCompleted<Boolean> listener) {
        super(context, listener);
    }

    @Override
    protected Boolean doInBackground(String... strings) {
        String URL = buildURL(strings);

        String jsonResponse = super.submitRequest(URL);
        return parseJson(jsonResponse);
    }

    private Boolean parseJson(String jsonResponse) {
        return Boolean.valueOf(jsonResponse);
    }

    private String buildURL(String[] params) {
        String trainingID = params[0];
        String groupID= params[1];
        String idRoom= params[2];
        StringBuilder url = new StringBuilder("http://");
        url.append(HOST_NAME)
                .append(":")
                .append(PORT_NUMBER)
                .append("/exam/updateExamStatus?tpi=").append(trainingID)
                .append("&groupID=").append(groupID)
                .append("&idRoom=").append(idRoom);
        Log.d(TAG, "buildURL: sucess");
        return url.toString();
    }
}
