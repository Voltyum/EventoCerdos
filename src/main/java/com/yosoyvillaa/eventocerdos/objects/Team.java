package com.yosoyvillaa.eventocerdos.objects;

import org.bukkit.Location;

import java.util.Set;

public class Team {

    private final String id;
    private String displayName;
    private Set<String> playerList;
    private Location spawnLocation;

    public Team(String id, String displayName, Set<String> playerList, Location spawnLocation) {
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

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }
}
