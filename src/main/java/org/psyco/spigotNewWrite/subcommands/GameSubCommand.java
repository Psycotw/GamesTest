package org.psyco.spigotNewWrite.subcommands;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.psyco.spigotNewWrite.SpigotNewWrite;
import org.psyco.spigotNewWrite.gui.JoinGUI;
import org.psyco.spigotNewWrite.manager.GameManager;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class GameSubCommand {


    private final SpigotNewWrite plugin;
    private final GameManager gameManager;

    public GameSubCommand(SpigotNewWrite plugin, GameManager gameManager) {
        this.plugin = plugin;
        this.gameManager = gameManager;
    }


    public void add(CommandSender sender, String[] args) {
        if (sender.hasPermission("game.admin")) {

            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "/game add <player>");
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null || !target.isOnline()) {
                sender.sendMessage(ChatColor.RED + "Player not found or offline");
                return;
            }

            UUID id = target.getUniqueId();

            if (plugin.gameList.contains(id)) {
                sender.sendMessage(ChatColor.RED + "Player is already in the game");
                return;
            }

            if (plugin.isGameStarted) {

                plugin.gameList.add(id);
                sender.sendMessage(ChatColor.GREEN + "Player added to the game");


            }
        }


    }

    public void join(CommandSender sender){

        if(!(sender instanceof Player)) return;

        Player player = (Player) sender;

        JoinGUI gui = new JoinGUI(plugin,gameManager);

        gui.onJoin(player);

    }

    public void joinCommand(CommandSender sender) {


        Player player = (Player) sender;

        UUID id = player.getUniqueId();

        if(!plugin.isGameStarted) {
            player.sendMessage(ChatColor.RED + "nessun game cominciato");
            return;
        }
        if(plugin.gameList.contains(id)) {
            player.sendMessage(ChatColor.RED + "sei già in game");
            return;
        }
        if(plugin.spawnLoc.isEmpty()){
            player.sendMessage(ChatColor.RED + "Lo spawn non è stato settato");
            return;
        }

        if(player != null && player.isOnline()) {

            plugin.gameList.add(id);
            player.teleport(plugin.spawnLoc.get(0));
            player.sendMessage(ChatColor.GREEN + "ti sei unito al game!");

        }

    }

    public void quit(CommandSender sender) {

        Player player = (Player) sender;

        if(player == null ) return;

        if(player.isOnline()) {
            UUID id = player.getUniqueId();

            if (!plugin.isGameStarted) {
                player.sendMessage(ChatColor.RED + "nessun game cominciato");
                return;
            }
            if (plugin.gameList.contains(id)) {
                plugin.gameList.remove(id);
                player.sendMessage(ChatColor.GREEN + "sei uscito dal game con successo!");


            }
        }

    }

    public void remove(CommandSender sender, String[] args) {
        if (sender.hasPermission("game.admin")) {
            if (args.length < 2) {
                sender.sendMessage(ChatColor.RED + "/game remove <player>");
                return;
            }

            Player target = Bukkit.getPlayer(args[1]);

            if (target == null) {
                sender.sendMessage(ChatColor.RED + "Player not found");
                return;
            }

            UUID id = target.getUniqueId();

            if (!plugin.gameList.contains(id)) {
                sender.sendMessage(ChatColor.RED + "Player not in the game");
                return;
            }

            if (plugin.isGameStarted) {
                plugin.gameList.remove(id);
                sender.sendMessage(ChatColor.GREEN + "Player removed from the game");

            }
        }
    }

    public void start(CommandSender sender) {
        if (sender.hasPermission("game.admin")) {

            Player player = (Player) sender;

            if (!plugin.isGameStarted) {


                plugin.isGameStarted = true;
                gameManager.createMatch(player.getName());
                sender.sendMessage(ChatColor.GREEN + "hai cominciato un game");

                for (Player people : Bukkit.getOnlinePlayers()) {

                    people.sendMessage(ChatColor.GOLD + "fai /game join per unirti al game di" + sender.getName() + "!");

                }
                return;
            }
            sender.sendMessage(ChatColor.RED + "Il game è già iniziato!");

        }
    }

    public void stop(CommandSender sender) {
        if (sender.hasPermission("game.admin")) {

            Player creator = (Player) sender;


            if (!plugin.isGameStarted) {


                sender.sendMessage(ChatColor.RED + "nessun game in corso");
                return;
            }

            plugin.isGameStarted = false;

            gameManager.deleteMatch(creator.getName());
            sender.sendMessage(ChatColor.RED + "hai stoppato un game");

            for (UUID id : plugin.gameList) {
                Player player = Bukkit.getPlayer(id);
                if (player != null) {
                    player.sendMessage(ChatColor.RED + "sei stato tolto dal game");
                }
            }

            plugin.gameList.clear();
        }
    }

    public void info(CommandSender sender) {

        Player exect = (Player) sender;
        List<UUID> uuidList = plugin.gameList;
        List<String> names =  new ArrayList<>();

        if(!plugin.gameList.contains(exect.getUniqueId())) {

            exect.sendMessage(ChatColor.RED + "non puoi eseguire questo comando perchè non sei in un game!");
            return;
        }

        for(UUID id : uuidList){

            Player player = Bukkit.getPlayer(id);

            if(player != null){
                names.add(player.getName());

            }else {
                names.add("Offline(" + id + ")");
            }


        }

        sender.sendMessage("Giocatori: " + String.join(", ", names));



    }

    public void setSpawn(CommandSender sender) {


        if (!(sender instanceof Player)) return;
        if (sender.hasPermission("game.admin")) {

            Player host = (Player) sender;

            Location spawn = host.getLocation();


            plugin.spawnLoc.clear();
            plugin.spawnLoc.add(spawn);

            host.sendMessage(ChatColor.GREEN + "hai settato lo spawn della zona combattimenti!!");
        }
    }

}
