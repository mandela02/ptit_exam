package com.chidm.ptit_exam.task.validation;

import android.content.Context;
import android.util.Log;
import com.chidm.ptit_exam.model.Student;
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
import java.util.List;

public class StudentGetter extends ValidationTask<Void, Void, List<Student>> {
    public StudentGetter(Context context, OnTaskCompleted<List<Student>> listener) {
        super(context, listener);
    }

    @Override
    protected List<Student> doInBackground(Void... voids) {
        String URL = buildURL();
        StringBuilder stringBuilder = new StringBuilder();
        HttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(URL);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode == HttpStatus.SC_OK) {
                HttpEntity entity = response.getEntity();
                InputStream inputStream = entity.getContent();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream));
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                inputStream.close();
            } else {
                Log.d("JSON", "Failed to download file");
            }
        } catch (Exception e) {
            Log.d("readJSONFeed", e.getLocalizedMessage());
        }
        String jsonResponse = stringBuilder.toString();
        return parseJson(jsonResponse);
    }

    private List<Student> parseJson(String jsonResponse) {
        return null;
    }

    private String buildURL() {
        StringBuilder url = new StringBuilder("http://");
        url.append(HOST_NAME)
                .append(":")
                .append(PORT_NUMBER)
                .append("/exam/getAllStudents");
        return url.toString();
    }
}
