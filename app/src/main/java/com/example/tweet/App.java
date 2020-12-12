package com.example.tweet;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ZUt4n2RCRarbymRvHV1252kvtOjSGiwHkGlcLTAJ")
                .clientKey("GBl2AUWaWG2BEjjohO1tNevgUUIAucQKLuckU8at")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
