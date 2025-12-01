package org.psyco.spigotNewWrite.manager;

import org.psyco.spigotNewWrite.GameMatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class GameManager {


    private final List<GameMatch> matches;

    public List<GameMatch> getMatches() {
        return matches;
    }
    public GameManager() {
        this.matches = new ArrayList<>();
    }

    public GameMatch getMatchByName(String name) {

        for(GameMatch match : matches) {

            if(match.getName().equalsIgnoreCase(name)){

                return match;
            }

        }

        return null;
    }

    public GameMatch getMatchByPlayer(UUID uuid) {

        for(GameMatch match : matches) {

            if(match.getPlayers().contains(uuid)){

                return match;
            }

        }
        return null;

    }

    public void createMatch(String name) {
        if (getMatchByName(name) == null) {
            matches.add(new GameMatch(name));
        }
    }
    public void deleteMatch(String name) {
        if(getMatchByName(name) != null) {
            matches.remove(getMatchByName(name));
        }
    }
    public List<GameMatch> getAllMatches() {

        return matches;
    }

}
