package com.yosoyvillaa.eventocerdos.commands.subcommands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.objects.SpawnLocation;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.Objects;

@Command(names = "resetteams")
public class ResetTeamsCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;
    @Inject @Named("data") private  YAMLFile data;
    @Inject private TeamManager teamManager;

    @Command(names = "")
    public void onResetTeamsCommand(@Sender Player sender) {
        try {
            teamManager.resetTeams();
            Location spawn = SpawnLocation.of(Objects.requireNonNull(data.get("main_spawn").get(SpawnLocation.class)));
            Bukkit.getServer().getOnlinePlayers().forEach(player1 -> {
                player1.getInventory().clear();
                player1.teleport(spawn);
            });
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "resetTeams", "success").getString()));
        } catch (SerializationException e) {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "resetTeams", "error").getString()));
            throw new RuntimeException(e);
        }

        // Kill all horses in the server
        Bukkit.getServer().getWorlds().forEach(world -> {
            world.getEntities().forEach(entity -> {
                if (entity.getType().equals(EntityType.HORSE)) {
                    entity.remove();
                }
            });
        });
    }
}
