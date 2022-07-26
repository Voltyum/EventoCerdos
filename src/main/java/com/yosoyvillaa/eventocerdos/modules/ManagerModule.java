package com.yosoyvillaa.eventocerdos.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;

public class ManagerModule extends AbstractModule {

    @Override
    public void configure() {
        bind(TeamManager.class).in(Scopes.SINGLETON);
    }
}
