package org.psyco.spigotNewWrite.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.psyco.spigotNewWrite.SpigotNewWrite;
import org.psyco.spigotNewWrite.manager.GameManager;
import org.psyco.spigotNewWrite.subcommands.GameSubCommand;

public class GameCommand implements CommandExecutor {

    private final SpigotNewWrite plugin;
    private  GameSubCommand sub;
    private final GameManager manager;


    public GameCommand(SpigotNewWrite plugin, GameSubCommand sub, GameManager manager) {
        this.plugin = plugin;
        this.manager = manager;
        this.sub = new GameSubCommand(plugin,manager);
    }




    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){

        if(!(sender instanceof Player player)) return false;


        player = (Player) sender;

        if(player.hasPermission("game.admin")) {
            if (args.length == 0) {


                player.sendMessage(ChatColor.GOLD + "---Game Command---");
                player.sendMessage("/game start");
                player.sendMessage("/game stop");
                player.sendMessage("/game add <player>");
                player.sendMessage("/game remove <player>");
                player.sendMessage("/game info");
                player.sendMessage("/game setspawn");
                player.sendMessage("/game quit");
                player.sendMessage("/game join");
                player.sendMessage("/game joincommand");
                player.sendMessage(ChatColor.GOLD + "---Game Command---");

                return true;
            }
        } else {
            if (args.length == 0) {
                player.sendMessage(ChatColor.GOLD + "---Game Command---");
                player.sendMessage("/game join");
                player.sendMessage("/game quit");
                player.sendMessage("/game info");
                player.sendMessage(ChatColor.GOLD + "---Game Command---");

                return true;
            }
        }

            if (player.hasPermission("game.admin")) {
                switch (args[0].toLowerCase()) {

                    case "add":
                        sub.add(player, args);
                        break;
                    case "start":
                        sub.start(player);
                        break;
                    case "stop":
                        sub.stop(player);
                        break;
                    case "remove":
                        sub.remove(player, args);
                        break;
                    case "info":
                        sub.info(player);
                        break;
                    case "setspawn":
                        sub.setSpawn(player);
                        break;
                    case "join":
                        sub.join(player);
                        break;
                    case "quit":
                        sub.quit(player);
                        break;
                    case "joincommand":
                        sub.joinCommand(player);

                    default:
                        player.sendMessage(ChatColor.RED + "No such subcommand");
                }
            } else {
                switch (args[0].toLowerCase()) {
                    case "info":
                        sub.info(player);
                        break;
                    case "join":
                        sub.join(player);
                        break;
                    case "quit":
                        sub.quit(player);
                        break;

                    default:
                        player.sendMessage(ChatColor.RED + "No such subcommand");
                }
            }




        return true;
    }
}
