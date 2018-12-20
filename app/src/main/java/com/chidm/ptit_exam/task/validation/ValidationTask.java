package com.chidm.ptit_exam.task.validation;

import android.content.Context;
import android.util.Log;
import com.chidm.ptit_exam.task.BasicTask;
import com.chidm.ptit_exam.task.OnTaskCompleted;
import com.chidm.ptit_exam.utils.ConfigReader;
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

public abstract class ValidationTask<T, M, O> extends BasicTask<T, M, O> {

    protected String HOST_NAME;
    protected int PORT_NUMBER;
    protected ConfigReader configReader; // read config.properties file in assert folder

    public ValidationTask(Context context, OnTaskCompleted<O> listener) {
        super(context, listener);
        this.configReader = ConfigReader.getInstance();
        this.HOST_NAME = configReader.getProperty(context, "host.name");
        this.PORT_NUMBER = Integer.parseInt(configReader.getProperty(context, "port.number"));
    }

    /**
     * call to a webservice using URL
     *
     * @param URL url to submit
     * @return json String from web service
     */
    protected String submitRequest(String URL) {
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
        return jsonResponse;
    }
}
