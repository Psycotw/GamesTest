package org.psyco.spigotNewWrite.listener;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.psyco.spigotNewWrite.SpigotNewWrite;
import org.psyco.spigotNewWrite.subcommands.GameSubCommand;

public class PlayerListener implements Listener {

    private final GameSubCommand sub;

    public PlayerListener(GameSubCommand sub) {
        this.sub = sub;

    }


    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {

        if(e.getClickedInventory() == null) return;

        if(!(e.getWhoClicked() instanceof Player)) return;

        if(e.getView().getTitle().equalsIgnoreCase("Join GUI")) {

            e.setCancelled(true);

            Player player = (Player) e.getWhoClicked();

            ItemStack item = e.getCurrentItem();

            if(item == null || item.getType() == Material.AIR) return;

            if(item.getType() == Material.PAPER && item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {

                e.setCancelled(true);

                sub.joinCommand(player);

            }


        }

    }

}
