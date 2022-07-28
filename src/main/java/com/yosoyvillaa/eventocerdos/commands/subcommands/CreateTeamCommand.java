package com.yosoyvillaa.eventocerdos.commands.subcommands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = "createteam")
public class CreateTeamCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;
    @Inject private TeamManager teamManager;

    @Command(names = "")
    public void onCreateTeamCommand(@Sender Player sender, String id) {
        teamManager.findTeam(id).ifPresentOrElse(team -> {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "createTeam", "already_exists").getString()));
        }, () -> {
            teamManager.createTeam(id, sender.getLocation());
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "createTeam", "success").getString()
                    .replace("%id%", id)));
        });
    }
}
