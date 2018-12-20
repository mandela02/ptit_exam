package com.chidm.ptit_exam.task.validation;

import android.content.Context;
import com.chidm.ptit_exam.task.OnTaskCompleted;

public class StudentUpdator extends ValidationTask<String, Void, Boolean> {

    /**
     * update state of exam
     * @param context
     * @param listener
     */

    public StudentUpdator(Context context, OnTaskCompleted<Boolean> listener) {
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
        String id = params[0];
        String tgi = params[1];
        String tpi = params[2];
        StringBuilder url = new StringBuilder("http://");
        url.append(HOST_NAME)
                .append(":")
                .append(PORT_NUMBER)
                .append("/student/update?id=").append(id)
                .append("&tgi=").append(tgi)
                .append("&tpi=").append(tpi);
        return url.toString();
    }
}
