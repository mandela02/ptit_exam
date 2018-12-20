package com.chidm.ptit_exam.utils;


import android.util.Log;
import com.chidm.ptit_exam.task.OnTaskCompleted;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by tatsuya on 17/04/2018.
 */

public class EndPoint<T> implements OnTaskCompleted<T> {
    //for getting result from server but not display in any activity
    @Override
    public void handle(T value) {
        if (value instanceof List) {
            List<String> lineList = (List<String>) value;
            for (String line : lineList) {
                Log.e("result: ", line);
            }
        } else {
            Log.i("result from service:", value.toString());

        }
    }
}
