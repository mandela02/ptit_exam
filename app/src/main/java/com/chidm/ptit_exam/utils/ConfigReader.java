package com.chidm.ptit_exam.utils;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by tatsuya on 07/04/2018.
 * a singleton object
 * read necessary properties for application in config.properties file, located in assets folder
 */

public class ConfigReader {
    private static final ConfigReader instance = new ConfigReader();

    private static Properties properties = new Properties();

    public static ConfigReader getInstance() {
        return instance;
    }

    private ConfigReader() {

    }

    public String getProperty(Context context, String key) {
        AssetManager assetManager = context.getAssets();
        try {
            properties.load(assetManager.open("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
