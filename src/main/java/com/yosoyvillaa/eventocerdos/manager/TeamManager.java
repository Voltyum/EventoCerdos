package com.yosoyvillaa.eventocerdos.manager;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.EventoCerdos;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.objects.SpawnLocation;
import com.yosoyvillaa.eventocerdos.objects.Team;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.spongepowered.configurate.serialize.SerializationException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class TeamManager {

    private final HashMap<String, Team> teamMap = new HashMap<>();

    @Inject @Named("data") private YAMLFile data;
    @Inject private EventoCerdos eventoCerdos;

    public void loadTeams() {
        if (!data.getConfigurationNode().hasChild("teams")) return;
        List<Team> teamList = data.getList(Team.class, "teams");
        teamList.forEach(team -> teamMap.put(team.getId(), team));
        System.out.println(teamList);
        Bukkit.getScheduler().runTaskTimer(eventoCerdos, () -> {
            System.out.println("Saving teams...");
            saveTeams();
        }, 60L, 20*120L);
    }

    public Optional<Team> findTeam(String id) {
        return Optional.ofNullable(teamMap.get(id));
    }

    public void createTeam(String id, Location location) {
        teamMap.put(id, new Team(id, id, new HashSet<>(), SpawnLocation.of(location)));
        saveTeams();
    }

    public void addPlayerToTeam(String team, String playerName) {
        teamMap.get(team).addPlayer(playerName);
    }

    public Optional<Team> getPlayerTeam(String playerName) {
        Team team = null;

        for (Team team1 : teamMap.values()) {
            if (team1.getPlayerList().contains(playerName))
                team = team1;
        }

        return Optional.ofNullable(team);
    }

    public void saveTeams() {
        try {
            data.get("teams").setList(Team.class, teamMap.values().stream().toList());
        } catch (SerializationException e) {
            throw new RuntimeException(e);
        }
        data.save();
    }

    public void resetTeams() {
        teamMap.values().forEach(team -> team.setPlayerList(new HashSet<>()));
        saveTeams();
    }
}
