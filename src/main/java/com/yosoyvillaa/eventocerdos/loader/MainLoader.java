package com.yosoyvillaa.eventocerdos.loader;

import com.google.inject.Inject;
import com.yosoyvillaa.eventocerdos.hook.placeholderapi.EventExpansion;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;

public class MainLoader implements Loader {

    @Inject private TeamManager teamManager;
    @Inject private CommandsLoader commandsLoader;
    @Inject private ListenersLoader listenersLoader;
    @Inject private EventExpansion eventExpansion;

    @Override
    public void load() {
        teamManager.loadTeams();
        commandsLoader.load();
        listenersLoader.load();
        eventExpansion.register();
    }
}
