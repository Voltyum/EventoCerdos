package com.yosoyvillaa.eventocerdos.hook.placeholderapi;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.yosoyvillaa.eventocerdos.file.YAMLFile;
import com.yosoyvillaa.eventocerdos.manager.TeamManager;
import com.yosoyvillaa.eventocerdos.objects.Team;
import com.yosoyvillaa.eventocerdos.utils.TextUtils;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class EventExpansion extends PlaceholderExpansion {

    @Inject private TeamManager teamManager;
    @Inject @Named("config") private YAMLFile config;

    @Override
    public @NotNull String getIdentifier() {
        return "evento";
    }

    @Override
    public @NotNull String getAuthor() {
        return "YoSoyVillaa";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public String onRequest(OfflinePlayer player, @NotNull String params) {
        /*
        %evento_team_id_display%
        %evento_team_id_players%
        %evento_player_team%
         */
        if (params.startsWith("team")) {
            String args[] = params.split("_");
            Optional<Team> team = teamManager.findTeam(args[1]);
            if (team.isEmpty()) return null;
            return switch (args[2]) {
                case "diaplay" -> TextUtils.colorize(team.get().getDisplayName());
                case "players" -> String.valueOf(team.get().getPlayerList().size());
                default -> null;
            };
        } else if (params.equalsIgnoreCase("player_team")) {
            Optional<Team> team = teamManager.getPlayerTeam(player.getName());
            if (team.isEmpty()) {
                return TextUtils.colorize(config.get("messages", "placeholder", "not_in_team").getString());
            }
            return team.get().getDisplayName();
        }

        return null;
    }
}
