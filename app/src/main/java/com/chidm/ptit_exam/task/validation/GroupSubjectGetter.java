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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GroupSubjectGetter extends ValidationTask<Void, Void, List<String>> {
    private String roomID;

    public GroupSubjectGetter(Context context, OnTaskCompleted<List<String>> listener, String roomID) {
        super(context, listener);
        this.roomID = roomID;
    }

    @Override
    protected List<String> doInBackground(Void... voids) {
        String URL = buildURL();
        String result = super.submitRequest(URL);
        return parseJson(result);
    }

    private List<String> parseJson(String jsonResponse) {
        Log.e("result from server: ", jsonResponse);
        JSONArray jsonList = null;
        List<String> result = new ArrayList<>();
        try {
            jsonList = new JSONArray(jsonResponse);
            for (int i = 0; i < jsonList.length(); i++) {
                String line = (String) jsonList.get(i);
                result.add(line);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String buildURL() {
        StringBuilder url = new StringBuilder("http://");
        url.append(HOST_NAME)
                .append(":")
                .append(PORT_NUMBER)
                .append("/exam/getGroups?idRoom=").append(roomID);
        return url.toString();
    }
}
