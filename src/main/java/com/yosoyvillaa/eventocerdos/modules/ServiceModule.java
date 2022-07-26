package com.yosoyvillaa.eventocerdos.modules;

import com.google.inject.AbstractModule;
import com.yosoyvillaa.eventocerdos.service.MainService;
import com.yosoyvillaa.eventocerdos.service.Service;

public class ServiceModule extends AbstractModule {

    @Override
    public void configure() {
        bind(Service.class).to(MainService.class);
    }
}
