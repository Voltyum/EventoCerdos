package com.yosoyvillaa.eventocerdos.modules;

import com.google.inject.AbstractModule;
import com.yosoyvillaa.eventocerdos.EventoCerdos;
import com.yosoyvillaa.eventocerdos.file.FileMatcher;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
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

        FileMatcher fileMatcher = new FileMatcher()
                .bind("config", new YAMLFile(eventoCerdos, "config"))
                .bind("data", new YAMLFile(eventoCerdos, "data"));
        install(fileMatcher.build());

        install(new ServiceModule());
        install(new ManagerModule());
    }
}
