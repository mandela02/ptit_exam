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

public class IDUpdate extends ValidationTask<Void, Void, Boolean> {
    private String trainingGroupID;
    private String trainingPersonID;

    public IDUpdate(Context context, OnTaskCompleted<Boolean> listener,
                    String trainingGroupID, String trainingPersonID) {
        super(context, listener);
        this.trainingGroupID = trainingGroupID;
        this.trainingPersonID = trainingPersonID;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        String URL = buildURL();
        String jsonResponse = super.submitRequest(URL);
        return parseJson(jsonResponse);
    }

    private Boolean parseJson(String jsonResponse) {
        return false;
    }

    private String buildURL() {
        StringBuilder url = new StringBuilder("http://");
        url.append(HOST_NAME)
                .append(":")
                .append(PORT_NUMBER)
                .append("/exam/update?trainingGroupID=")
                .append(trainingGroupID)
                .append("&trainingPersonID=")
                .append(trainingPersonID);
        return url.toString();
    }
}
