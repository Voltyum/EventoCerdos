package com.yosoyvillaa.eventocerdos.service;

import com.google.inject.Inject;
import com.yosoyvillaa.eventocerdos.loader.MainLoader;

public class MainService implements Service {

    @Inject private MainLoader mainLoader;

    @Override
    public void start() {
        mainLoader.load();
    }

    @Override
    public void stop() {
    }
}
