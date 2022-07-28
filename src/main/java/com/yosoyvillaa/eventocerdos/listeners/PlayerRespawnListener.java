package com.yosoyvillaa.eventocerdos.listeners;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.objects.SpawnLocation;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Objects;

public class PlayerRespawnListener implements Listener {

    @Inject private TeamManager teamManager;
    @Inject @Named("data") private YAMLFile data;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerRespawn(PlayerRespawnEvent event) {
        teamManager.getPlayerTeam(event.getPlayer().getName()).ifPresentOrElse(team -> {
            event.setRespawnLocation(SpawnLocation.of(team.getSpawnLocation()));
        }, () -> {
            try {
                Location location = SpawnLocation.of(Objects.requireNonNull(data.get("main_spawn").get(SpawnLocation.class)));
                event.setRespawnLocation(location);
            } catch (SerializationException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
