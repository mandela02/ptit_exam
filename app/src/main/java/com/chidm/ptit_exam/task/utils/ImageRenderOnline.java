package com.chidm.ptit_exam.task.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import java.io.InputStream;

public class ImageRenderOnline extends AsyncTask<String, Void, Bitmap> {
    // ImageView imageView;

    public ImageRenderOnline() {
    //    this.imageView = imageView;
//        Toast.makeText(null, "Please wait, it may take a few minute...", Toast.LENGTH_SHORT).show();
    }

    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bimage = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }

    protected void onPostExecute(Bitmap result) {
        super.onPostExecute(result);
    }
}