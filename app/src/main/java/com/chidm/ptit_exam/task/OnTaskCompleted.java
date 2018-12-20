package com.chidm.ptit_exam.task;

import java.io.IOException;
import java.net.MalformedURLException;

public interface OnTaskCompleted<T> {
    void handle(T value) throws IOException;
}
