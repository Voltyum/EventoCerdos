package com.yosoyvillaa.eventocerdos.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Pig;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class PigUtils {

    public static void spawnPigAndSetPassenger(Location location, Player entity) {
        entity.getInventory().clear();
        entity.teleport(location);
        Pig pig = (Pig) location.getWorld().spawnEntity(location, EntityType.PIG, CreatureSpawnEvent.SpawnReason.CUSTOM);
        pig.setSaddle(true);
        pig.addPassenger(entity);
        ItemStack carrot = new ItemStack(Material.CARROT_ON_A_STICK, 1);
        ItemMeta itemMeta = carrot.getItemMeta();
        itemMeta.setUnbreakable(true);
        carrot.setItemMeta(itemMeta);
        entity.getInventory().setItemInMainHand(carrot);
    }
}
