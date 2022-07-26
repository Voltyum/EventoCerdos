package com.yosoyvillaa.eventocerdos.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleExitEvent;

public class VehicleExitListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onVehicleExit(VehicleExitEvent event) {
        if (event.isCancellable())
            event.setCancelled(true);
    }
}
