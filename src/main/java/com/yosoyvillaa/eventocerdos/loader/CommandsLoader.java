package com.yosoyvillaa.eventocerdos.loader;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.yosoyvillaa.eventocerdos.EventoCerdos;
import com.yosoyvillaa.eventocerdos.commands.JoinTeamCommand;
import com.yosoyvillaa.eventocerdos.commands.EventCommand;
import me.fixeddev.commandflow.CommandManager;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilder;
import me.fixeddev.commandflow.annotated.AnnotatedCommandTreeBuilderImpl;
import me.fixeddev.commandflow.annotated.CommandClass;
import me.fixeddev.commandflow.annotated.SubCommandInstanceCreator;
import me.fixeddev.commandflow.annotated.builder.AnnotatedCommandBuilder;
import me.fixeddev.commandflow.annotated.part.PartInjector;
import me.fixeddev.commandflow.annotated.part.SimplePartInjector;
import me.fixeddev.commandflow.annotated.part.defaults.DefaultsModule;
import me.fixeddev.commandflow.bukkit.BukkitCommandManager;
import me.fixeddev.commandflow.bukkit.factory.BukkitModule;

public class CommandsLoader implements Loader {

    @Inject private EventoCerdos eventoCerdos;
    @Inject private Injector injector;

    @Inject private EventCommand eventCommand;
    @Inject private JoinTeamCommand joinTeamCommand;

    @Override
    public void load() {
        PartInjector partInjector = new SimplePartInjector();
        partInjector.install(new DefaultsModule());
        partInjector.install(new BukkitModule());

        AnnotatedCommandBuilder builder = AnnotatedCommandBuilder.create(partInjector);
        SubCommandInstanceCreator instanceCreator = (clazz, parent) -> injector.getInstance(clazz);

        AnnotatedCommandTreeBuilder annotatedCommandTreeBuilder = new AnnotatedCommandTreeBuilderImpl(builder, instanceCreator);
        CommandManager commandManager = new BukkitCommandManager(eventoCerdos.getName());
        registerCommands(annotatedCommandTreeBuilder, commandManager, eventCommand, joinTeamCommand);
    }

    private void registerCommands(AnnotatedCommandTreeBuilder commandBuilder, CommandManager commandManager, CommandClass... commandClasses) {
        for (CommandClass commandClass : commandClasses) {
            commandManager.registerCommands(commandBuilder.fromClass(commandClass));
        }
    }
}
