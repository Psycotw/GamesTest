package org.psyco.spigotNewWrite;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.psyco.spigotNewWrite.commands.GameCommand;
import org.psyco.spigotNewWrite.listener.PlayerListener;
import org.psyco.spigotNewWrite.manager.GameManager;
import org.psyco.spigotNewWrite.subcommands.GameSubCommand;
import org.psyco.spigotNewWrite.tabcompleter.GameTabCompleter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public final class SpigotNewWrite extends JavaPlugin {

    private GameSubCommand sub;

    public List<UUID> gameList = new ArrayList<>();
    public List<Location> spawnLoc;
    public List<Player> gameCreator;
    public  boolean isGameStarted = false;
    private GameManager gameManager;


    @Override
    public void onEnable() {
        gameManager = new GameManager();
        spawnLoc = new ArrayList<>();
        gameCreator = new ArrayList<>();
        this.sub = new GameSubCommand(this,gameManager);

        getCommand("game").setExecutor(new GameCommand(this,sub,gameManager));
        getCommand("game").setTabCompleter(new GameTabCompleter());
        getServer().getPluginManager().registerEvents(new PlayerListener(sub), this);

    }

    @Override
    public void onDisable() {
    }
}
