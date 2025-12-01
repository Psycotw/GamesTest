package org.psyco.spigotNewWrite.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.psyco.spigotNewWrite.GameMatch;
import org.psyco.spigotNewWrite.SpigotNewWrite;
import org.psyco.spigotNewWrite.manager.GameManager;

import java.util.List;
import java.util.UUID;

public class JoinGUI {

    private final SpigotNewWrite plugin;
    private final GameManager manager;

    public JoinGUI(SpigotNewWrite plugin, GameManager manager) {
        this.plugin = plugin;
        this.manager = manager;
    }


    public void onJoin(Player player) {

        Inventory inv = Bukkit.createInventory(null, 27, "Join GUI");

        List<GameMatch> matches = manager.getAllMatches();


        int slot = 9;
        for (GameMatch match : matches) {



                if (slot >= 18) break;

                ItemStack item = new ItemStack(Material.PAPER);
                ItemMeta meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.GREEN + match.getName());
                meta.setDisplayName(ChatColor.GREEN + match.getName());
                meta.setLore(List.of(
                        ChatColor.YELLOW + "Giocatori: " + match.getPlayers().size(),
                        ChatColor.GRAY + "Clicca per joinare!"
                ));
                item.setItemMeta(meta);

                inv.setItem(slot, item);

                slot++;

            }

            player.openInventory(inv);
        }

}
