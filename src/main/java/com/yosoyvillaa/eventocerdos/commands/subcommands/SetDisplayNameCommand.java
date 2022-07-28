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
import org.bukkit.entity.Player;

@Command(names = "setdisplayname")
public class SetDisplayNameCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;
    @Inject private TeamManager teamManager;

    @Command(names = "")
    public void onSetDisplayNameCommand(@Sender Player sender, String id, @Text String displayName) {
        teamManager.findTeam(id).ifPresentOrElse(team -> {
            team.setDisplayName(displayName);
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setDisplayName", "success").getString()
                    .replace("%displayName%", displayName)));
        }, () -> {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setDisplayName", "team_not_found").getString()));
        });
    }
}
