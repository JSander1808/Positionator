package de.rembel.Menus;

import de.rembel.Config.NormalConfig;
import de.rembel.Language.LanguageManager;
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
        LanguageManager language = new LanguageManager(player);
        Inventory inv = Bukkit.createInventory(null, 9*3, language.transalte(83));
        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");

        for(int i = 0;i<9*3;i++){
            inv.setItem(i,placeholder());
        }

        ItemStack deletePositionsFromOtherPlayer = new ItemStack(Material.COMPARATOR);
        ItemMeta deletePositionFromOtherPlayerMeta = deletePositionsFromOtherPlayer.getItemMeta();
        deletePositionFromOtherPlayerMeta.setDisplayName(language.transalte(84));
        ArrayList deletePositionFromOtherPlayerLore = new ArrayList();
        if(config.getBoolean("enableEditPositionsFromOtherPlayer")){
            deletePositionFromOtherPlayerLore.add(language.transalte(66));
        }else{
            deletePositionFromOtherPlayerLore.add(language.transalte(73));
        }
        deletePositionFromOtherPlayerLore.add(language.transalte(85));
        deletePositionFromOtherPlayerLore.add(language.transalte(86));
        deletePositionFromOtherPlayerMeta.setLore(deletePositionFromOtherPlayerLore);
        deletePositionsFromOtherPlayer.setItemMeta(deletePositionFromOtherPlayerMeta);

        ItemStack sendUpdateMessages = new ItemStack(Material.BELL);
        ItemMeta sendUpdateMessagesMeta = sendUpdateMessages.getItemMeta();
        sendUpdateMessagesMeta.setDisplayName(language.transalte(87));
        ArrayList sendUpdateMessagesLore = new ArrayList();
        if(config.getBoolean("sendUpdateMessages")){
            sendUpdateMessagesLore.add(language.transalte(66));
        }else{
            sendUpdateMessagesLore.add(language.transalte(73));
        }
        sendUpdateMessagesLore.add(language.transalte(88));
        sendUpdateMessagesLore.add(language.transalte(89));
        sendUpdateMessagesMeta.setLore(sendUpdateMessagesLore);
        sendUpdateMessages.setItemMeta(sendUpdateMessagesMeta);

        ItemStack teleport = new ItemStack(Material.ENDER_PEARL);
        ItemMeta teleportMeta = teleport.getItemMeta();
        teleportMeta.setDisplayName(language.transalte(90));
        ArrayList teleportLore = new ArrayList();
        if(!config.getBoolean("allowOpToTeleport") && !config.getBoolean("allowPlayerToTeleport")){
            teleportLore.add(language.transalte(73));
        }else if(config.getBoolean("allowOpToTeleport") && !config.getBoolean("allowPlayerToTeleport")){
            teleportLore.add(language.transalte(91));
        }else if(config.getBoolean("allowOpToTeleport") && config.getBoolean("allowPlayerToTeleport")){
            teleportLore.add(language.transalte(92));
        }
        teleportLore.add(language.transalte(93));
        teleportMeta.setLore(teleportLore);
        teleport.setItemMeta(teleportMeta);

        ItemStack backUpByServerRestart = new ItemStack(Material.RESPAWN_ANCHOR);
        ItemMeta backUpByServerRestartMeta = backUpByServerRestart.getItemMeta();
        backUpByServerRestartMeta.setDisplayName(language.transalte(94));
        ArrayList backUpByServerRestartLore = new ArrayList();
        if(config.getBoolean("createBackUpByServerRestart")){
            backUpByServerRestartLore.add(language.transalte(66));
        }else{
            backUpByServerRestartLore.add(language.transalte(73));
        }
        backUpByServerRestartLore.add(language.transalte(95));
        backUpByServerRestartLore.add(language.transalte(96));
        backUpByServerRestartMeta.setLore(backUpByServerRestartLore);
        backUpByServerRestart.setItemMeta(backUpByServerRestartMeta);


        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        inv.setItem(0, deletePositionsFromOtherPlayer);
        inv.setItem(1, sendUpdateMessages);
        inv.setItem(2, teleport);
        inv.setItem(3, backUpByServerRestart);
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
