package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class AdminSettingsMenu {

    public AdminSettingsMenu(Player player){
        Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.RED+"Admin Settings");
        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");

        for(int i = 0;i<9*3;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack deletePositionsFromOtherPlayer = new ItemStack(Material.COMPARATOR);
        ItemMeta deletePositionFromOtherPlayerMeta = deletePositionsFromOtherPlayer.getItemMeta();
        deletePositionFromOtherPlayerMeta.setDisplayName(ChatColor.GOLD+"Allow Player to delete position from other player");
        ArrayList deletePositionFromOtherPlayerLore = new ArrayList();
        if(config.getBoolean("enableDeletePositionsFromOtherPlayer")){
            deletePositionFromOtherPlayerLore.add(ChatColor.GREEN+"Active");
        }else{
            deletePositionFromOtherPlayerLore.add(ChatColor.RED+"Inactive");
        }
        deletePositionFromOtherPlayerLore.add(ChatColor.GRAY+"When active, players in the public list");
        deletePositionFromOtherPlayerLore.add(ChatColor.GRAY+"can delete positions from other players.");
        deletePositionFromOtherPlayerMeta.setLore(deletePositionFromOtherPlayerLore);
        deletePositionsFromOtherPlayer.setItemMeta(deletePositionFromOtherPlayerMeta);

        ItemStack sendUpdateMessages = new ItemStack(Material.BELL);
        ItemMeta sendUpdateMessagesMeta = sendUpdateMessages.getItemMeta();
        sendUpdateMessagesMeta.setDisplayName(ChatColor.GOLD+"Send update messages");
        ArrayList sendUpdateMessagesLore = new ArrayList();
        if(config.getBoolean("sendUpdateMessages")){
            sendUpdateMessagesLore.add(ChatColor.GREEN+"Active");
        }else{
            sendUpdateMessagesLore.add(ChatColor.RED+"Inactive");
        }
        sendUpdateMessagesLore.add(ChatColor.GRAY+"If active you will be sent a");
        sendUpdateMessagesLore.add(ChatColor.GRAY+"message when there is a new update.");
        sendUpdateMessagesMeta.setLore(sendUpdateMessagesLore);
        sendUpdateMessages.setItemMeta(sendUpdateMessagesMeta);

        ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName(ChatColor.GOLD+"Teleport");
        ArrayList teleportLore = new ArrayList();
        if(!config.getBoolean("allowOpToTeleport") && !config.getBoolean("allowPlayerToTeleport")){
            teleportLore.add(ChatColor.RED+"Inactive");
        }else if(config.getBoolean("allowOpToTeleport") && !config.getBoolean("allowPlayerToTeleport")){
            teleportLore.add(ChatColor.GREEN+"Active (OP only)");
        }else if(config.getBoolean("allowOpToTeleport") && config.getBoolean("allowPlayerToTeleport")){
            teleportLore.add(ChatColor.GREEN+"Active (everyone)");
        }
        teleportLore.add(ChatColor.GRAY+"When active you can teleport to a location");
        teleportMeta.setLore(teleportLore);
        teleport.setItemMeta(teleportMeta);


        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(ChatColor.RED+"Close");
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(ChatColor.GOLD+"Back");
        back.setItemMeta(backmeta);

        inv.setItem(0, deletePositionsFromOtherPlayer);
        inv.setItem(1, sendUpdateMessages);
        inv.setItem(2, teleport);
        inv.setItem(25, back);
        inv.setItem(26, close);

        player.openInventory(inv);
    }

    public ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
