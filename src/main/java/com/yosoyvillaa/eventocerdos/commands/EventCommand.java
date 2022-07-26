package com.yosoyvillaa.eventocerdos.commands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.commands.subcommands.AdminCommand;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;

@Command(names = {"event", "evento"})
@SubCommandClasses(
        AdminCommand.class
)
public class EventCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;

    @Command(names = "")
    public void onEventCommand(@Sender Player sender) {
        sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "main", "help").getString()));
    }
}
