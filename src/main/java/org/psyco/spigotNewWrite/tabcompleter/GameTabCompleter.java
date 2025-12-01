package org.psyco.spigotNewWrite.tabcompleter;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GameTabCompleter implements TabCompleter {


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String s, String[] args) {

        if(sender.hasPermission("game.admin")) {
            if (args.length == 1) {
                return Arrays.asList("start", "stop", "info", "add", "remove", "setspawn", "join", "quit","joincommand");
            }
        } else {
            if (args.length == 1) {
                return Arrays.asList("info", "join", "quit");
            }
        }




        return null;
    }
}
