package com.yosoyvillaa.eventocerdos.commands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.utils.PigUtils;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.OptArg;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = "joinTeam")
public class JoinTeamCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;
    @Inject private TeamManager teamManager;

    @Command(names = "")
    public void onJoinTeamCommand(@Sender Player sender, @OptArg String team) {
        if (teamManager.getPlayerTeam(sender.getName()).isPresent()) {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "jointeam", "already_in_team").getString()));
            return;
        }

        if (team == null) {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "jointeam", "help").getString()));
            return;
        }

        teamManager.findTeam(team).ifPresentOrElse(team1 -> {

            if (team1.getPlayerList().size() >= config.get("max_players_per_team").getInt()) {
                sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "jointeam", "max_players").getString()));
                return;
            }

            team1.addPlayer(sender.getName());
            PigUtils.spawnPigAndSetPassenger(team1.getSpawnLocation(), sender);
        }, () -> sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "jointeam", "team_not_found").getString())));
    }
}
