package com.yosoyvillaa.eventocerdos;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.yosoyvillaa.eventocerdos.modules.MainModule;
import com.yosoyvillaa.eventocerdos.service.Service;
import org.bukkit.plugin.java.JavaPlugin;

public final class EventoCerdos extends JavaPlugin {

    @Override
    public void onEnable() {
        Injector injector = Guice.createInjector(new MainModule(this));
        injector.injectMembers(this);

        Service service = injector.getInstance(Service.class);
        service.start();
    }

    @Override
    public void onDisable() {
    }
}