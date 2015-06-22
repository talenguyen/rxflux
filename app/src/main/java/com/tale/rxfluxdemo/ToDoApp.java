package com.tale.rxfluxdemo;

import android.app.Application;

import com.tale.rxflux.Dispatcher;

/**
 * Author tale. Created on 6/22/15.
 */
public class ToDoApp extends Application {

    private Dispatcher dispatcher;

    @Override
    public void onCreate() {
        super.onCreate();
        dispatcher = new Dispatcher();
    }

    public Dispatcher getDispatcher() {
        return dispatcher;
    }
}
