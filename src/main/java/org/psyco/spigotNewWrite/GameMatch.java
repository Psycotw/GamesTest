package org.psyco.spigotNewWrite;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GameMatch {

    private final String name;
    private final List<UUID> players;
    private boolean started;

    public GameMatch(String name) {
        this.name = name;
        this.players = new ArrayList<>();
        this.started = false;
    }

    public String getName() {
        return name;
    }
    public List<UUID> getPlayers() {

        return players;
    }
    public boolean isStarted() {
        return started;
    }
    public void setStarted(boolean started) {
        this.started = started;
    }

    public void addPlayer(UUID uuid) {
        players.add(uuid);
    }
    public void removePlayer(UUID uuid) {
        players.remove(uuid);
    }
}
