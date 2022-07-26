package com.yosoyvillaa.eventocerdos.commands.subcommands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.Text;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.serialize.SerializationException;

@Command(names = "admin", permission = "evento.admin", permissionMessage = "§6§l⚡ §e§lVoltyum §6§l⚡ §7» §c¡Permisos insuficientes!")
public class AdminCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;
    @Inject @Named("data") private  YAMLFile data;
    @Inject private TeamManager teamManager;

    @Command(names = "")
    public void onAdminCommand(@Sender Player sender) {
        sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "help").getString()));
    }

    @Command(names = "createTeam")
    public void onCreateTeamCommand(@Sender Player sender, String id) {
        teamManager.findTeam(id).ifPresentOrElse(team -> {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "createTeam", "already_exists").getString()));
        }, () -> {
            teamManager.createTeam(id, sender.getLocation());
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "createTeam", "success").getString()
                    .replace("%id%", id)));
        });
    }

    @Command(names = "setDisplayName")
    public void onSetDisplayNameCommand(@Sender Player sender, String id, @Text String displayName) {
        teamManager.findTeam(id).ifPresentOrElse(team -> {
            team.setDisplayName(displayName);
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setDisplayName", "success").getString()
                    .replace("%displayName%", displayName)));
        }, () -> {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setDisplayName", "team_not_found").getString()));
        });
    }

    @Command(names = "setSpawn")
    public void onSetSpawnCommand(@Sender Player sender) {
        try {
            data.get("main_spawn").set(sender.getLocation());
            data.save();
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setSpawn", "success").getString()));
        } catch (SerializationException e) {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setSpawn", "error").getString()));
            throw new RuntimeException(e);
        }
    }

    @Command(names = "resetTeams")
    public void onResetTeamsCommand(@Sender Player sender) {
        try {
            teamManager.resetTeams();
            Location spawn = data.get("main_spawn").get(Location.class);
            assert spawn != null;
            Bukkit.getServer().getOnlinePlayers().forEach(player -> {
                player.teleport(spawn);
            });
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "resetTeams", "success").getString()));
        } catch (SerializationException e) {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "resetTeams", "error").getString()));
            throw new RuntimeException(e);
        }
    }
}
