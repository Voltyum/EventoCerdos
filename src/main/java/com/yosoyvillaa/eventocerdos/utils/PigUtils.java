package com.yosoyvillaa.eventocerdos.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;

public class PigUtils {

    public static void spawnHorseAndSetPassenger(Location location, Player entity) {
        entity.getInventory().clear();
        entity.teleport(location);
        Horse horse = (Horse) location.getWorld().spawnEntity(location, EntityType.HORSE, CreatureSpawnEvent.SpawnReason.CUSTOM);
        horse.setJumpStrength(1);
        horse.getInventory().setSaddle(new ItemStack(Material.SADDLE));
        horse.addPassenger(entity);
    }
}
