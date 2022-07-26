package com.yosoyvillaa.eventocerdos.modules;

import com.google.inject.AbstractModule;
import com.yosoyvillaa.eventocerdos.EventoCerdos;
import org.bukkit.plugin.Plugin;

public class MainModule extends AbstractModule {

    private final EventoCerdos eventoCerdos;

    public MainModule(EventoCerdos eventoCerdos) {
        this.eventoCerdos = eventoCerdos;
    }

    @Override
    public void configure() {
        bind(EventoCerdos.class).toInstance(eventoCerdos);
        bind(Plugin.class).to(EventoCerdos.class);
    }
}
