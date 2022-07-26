package com.yosoyvillaa.eventocerdos.file;

import com.google.inject.Module;
import com.google.inject.name.Names;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class FileMatcher {

    private final Map<String, YAMLFile> files = new HashMap<>();

    public FileMatcher bind(String name, YAMLFile file) {
        files.put(name, file);

        return this;
    }

    public Optional<YAMLFile> get(String name) {
        return Optional.ofNullable(files.get(name));
    }

    public Collection<YAMLFile> values() {
        return files.values();
    }

    public Module build(){
        return  binder -> files.forEach((name, file) ->
                binder.bind(YAMLFile.class).annotatedWith(Names.named(name)).toInstance(file));
    }
}
