package com.yosoyvillaa.eventocerdos.listeners;

import com.destroystokyo.paper.event.player.PlayerPostRespawnEvent;
import com.google.inject.Inject;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.objects.SpawnLocation;
import com.yosoyvillaa.eventocerdos.utils.PigUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerPostRespawnListener implements Listener {

    @Inject private TeamManager teamManager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPostRespawn(PlayerPostRespawnEvent event) {
        teamManager.getPlayerTeam(event.getPlayer().getName()).ifPresent(team -> PigUtils.spawnPigAndSetPassenger(SpawnLocation.of(team.getSpawnLocation()), event.getPlayer()));
    }
}
