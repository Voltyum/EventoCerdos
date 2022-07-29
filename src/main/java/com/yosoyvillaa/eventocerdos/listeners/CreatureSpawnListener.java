package com.yosoyvillaa.eventocerdos.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class CreatureSpawnListener implements Listener {


    @EventHandler
    public void onEntitySpawn(CreatureSpawnEvent event) {
        if (event.getEntityType() != EntityType.PIG && event.getEntityType() != EntityType.PLAYER && event.getEntityType() != EntityType.SPLASH_POTION) {
            event.setCancelled(true);
        }
        if (event.getEntityType() == EntityType.PIG && event.getSpawnReason() != CreatureSpawnEvent.SpawnReason.CUSTOM) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        event.getDrops().clear();
    }
}
