package de.rembel.Menus;

import de.rembel.General.Command;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class Confirmation {

    public static HashMap<String, Command> Confirm = new HashMap<String, Command>();
    public static HashMap<String, Command> Cancel = new HashMap<String, Command>();

    public Confirmation(Player player, Command confirmCommand, Command cancelCommand){
        removeConfirmation(player);
        Confirm.put(player.getUniqueId().toString(), confirmCommand);
        Cancel.put(player.getUniqueId().toString(), cancelCommand);

        Inventory inv = Bukkit.createInventory(null, 1*9, ChatColor.GOLD+"Confirmation");

        ItemStack confirm = new ItemStack(Material.GREEN_WOOL);
        ItemMeta confirmMeta = confirm.getItemMeta();
        confirmMeta.setDisplayName(ChatColor.GREEN+"Confirm");
        confirm.setItemMeta(confirmMeta);

        ItemStack cancel = new ItemStack(Material.RED_WOOL);
        ItemMeta cancelMeta = cancel.getItemMeta();
        cancelMeta.setDisplayName(ChatColor.RED+"Cancel");
        cancel.setItemMeta(cancelMeta);

        inv.setItem(3,confirm);
        inv.setItem(5,cancel);

        player.openInventory(inv);
    }

    public static void removeConfirmation(Player player){
        Confirmation.Confirm.remove(player.getUniqueId().toString());
        Confirmation.Cancel.remove(player.getUniqueId().toString());
    }
}
