package com.yosoyvillaa.eventocerdos.commands;

import com.yosoyvillaa.eventocerdos.commands.subcommands.CreateTeamCommand;
import com.yosoyvillaa.eventocerdos.commands.subcommands.ResetTeamsCommand;
import com.yosoyvillaa.eventocerdos.commands.subcommands.SetDisplayNameCommand;
import com.yosoyvillaa.eventocerdos.commands.subcommands.SetSpawnCommand;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.annotated.annotation.SubCommandClasses;

@Command(names = "eventadmin", permission = "evento.admin", permissionMessage = "§6§l⚡ §e§lVoltyum §6§l⚡ §7» §c¡Permisos insuficientes!")
@SubCommandClasses({
        CreateTeamCommand.class,
        SetDisplayNameCommand.class,
        ResetTeamsCommand.class,
        SetSpawnCommand.class
})
public class EventCommand implements CommandClass {
}
