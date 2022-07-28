package com.yosoyvillaa.eventocerdos.loader;

import com.google.inject.Inject;
import com.yosoyvillaa.eventocerdos.EventoCerdos;
import com.yosoyvillaa.eventocerdos.listeners.PlayerPostRespawnListener;
import com.yosoyvillaa.eventocerdos.listeners.PlayerRespawnListener;
import com.yosoyvillaa.eventocerdos.listeners.CreatureSpawnListener;
import com.yosoyvillaa.eventocerdos.listeners.VehicleExitListener;
import org.bukkit.event.Listener;

public class ListenersLoader implements Loader {

    @Inject private EventoCerdos eventoCerdos;

    @Inject private VehicleExitListener vehicleExitListener;
    @Inject private PlayerRespawnListener playerRespawnListener;
    @Inject private PlayerPostRespawnListener playerPostRespawnListener;
    @Inject private CreatureSpawnListener creatureSpawnListener;

    @Override
    public void load() {
        registerListeners(vehicleExitListener, playerRespawnListener, playerPostRespawnListener,
                creatureSpawnListener);
    }

    private void registerListeners(Listener... listeners) {
        for (Listener listener : listeners) {
            eventoCerdos.getServer().getPluginManager().registerEvents(listener, eventoCerdos);
        }
    }
}
