package com.yosoyvillaa.eventocerdos.file;

import com.google.common.collect.Lists;
import org.bukkit.plugin.Plugin;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.serialize.SerializationException;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class YAMLFile {

    private static final String DEFAULT_EXTENSION = ".yml";

    private final String fileName;
    private final Plugin plugin;
    private final File folder;
    private YamlConfigurationLoader yamlConfigurationLoader;
    private ConfigurationNode configurationNode;

    public YAMLFile(Plugin plugin, String fileName, String fileExtension,
                    File folder) {
        this.folder = folder;
        this.plugin = plugin;
        this.fileName = fileName + (fileName.endsWith(fileExtension) ? "" : fileExtension);
        this.createFile();
    }

    public YAMLFile(Plugin plugin, String fileName) {
        this(plugin, fileName, DEFAULT_EXTENSION);
    }

    public YAMLFile(Plugin plugin, String fileName, String fileExtension) {
        this(plugin, fileName, fileExtension, plugin.getDataFolder());
    }

    private void createFile() {
        File file = new File(folder, fileName);
        if (!file.exists()) {
            try {
                folder.mkdirs();
                Files.copy(Objects.requireNonNull(plugin.getResource(fileName)), file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        yamlConfigurationLoader = YamlConfigurationLoader.builder().path(file.toPath()).build();
        try {
            configurationNode = yamlConfigurationLoader.load();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        try {
            this.configurationNode = yamlConfigurationLoader.load();
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            this.yamlConfigurationLoader.save(configurationNode);
        } catch (ConfigurateException e) {
            e.printStackTrace();
        }
    }

    public ConfigurationNode getConfigurationNode() {
        return this.configurationNode;
    }

    public ConfigurationNode get(Object... path) {
        return this.configurationNode.node(path);
    }

    public List<String> getStringList(Object... path) {
        try {
            return this.configurationNode.node(path).getList(String.class);
        } catch (SerializationException e) {
            e.printStackTrace();
            return Lists.newArrayList("No result for path: ", Arrays.toString(path));
        }
    }

    public <T> List<T> getList(Class<T> clazz, Object... path) {
        try {
            return this.configurationNode.node(path).getList(clazz);
        } catch (SerializationException e) {
            e.printStackTrace();
            return Lists.newArrayList();
        }
    }

}