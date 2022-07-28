package com.yosoyvillaa.eventocerdos.objects;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

import java.util.Set;

@ConfigSerializable
public class Team {

    private String id;
    private String displayName;
    private Set<String> playerList;
    private SpawnLocation spawnLocation;

    public Team() {
    }

    public Team(String id, String displayName, Set<String> playerList, SpawnLocation spawnLocation) {
        this.id = id;
        this.displayName = displayName;
        this.playerList = playerList;
        this.spawnLocation = spawnLocation;
    }

    public String getId() {
        return id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Set<String> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Set<String> playerList) {
        this.playerList = playerList;
    }

    public void addPlayer(String playerName) {
        playerList.add(playerName);
    }

    public void removePlayer(String playerName) {
        playerList.remove(playerName);
    }

    public SpawnLocation getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(SpawnLocation spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
}
