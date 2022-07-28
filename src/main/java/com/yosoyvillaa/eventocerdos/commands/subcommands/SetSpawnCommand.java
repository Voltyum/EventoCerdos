package com.yosoyvillaa.eventocerdos.commands.subcommands;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.objects.SpawnLocation;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.annotation.Command;
import me.fixeddev.commandflow.bukkit.annotation.Sender;
import org.bukkit.entity.Player;
import org.spongepowered.configurate.serialize.SerializationException;

@Command(names = "setspawn")
public class SetSpawnCommand implements CommandClass {

    @Inject @Named("config") private YAMLFile config;
    @Inject @Named("data") private  YAMLFile data;

    @Command(names = "")
    public void onSetSpawnCommand(@Sender Player sender) {
        try {
            data.get("main_spawn").set(SpawnLocation.class, SpawnLocation.of(sender.getLocation()));
            data.save();
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setSpawn", "success").getString()));
        } catch (SerializationException e) {
            sender.sendMessage(TextUtils.colorize(config.get("messages", "commands", "admin", "setSpawn", "error").getString()));
            throw new RuntimeException(e);
        }
    }
}
