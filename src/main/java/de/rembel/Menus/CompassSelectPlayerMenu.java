package de.rembel.Menus;

import de.rembel.CBossbar.CBossbar;
import de.rembel.CBossbar.CPosition;
import de.rembel.General.General;
import de.rembel.Language.LanguageManager;
import de.rembel.Main.PositionatorMain;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CompassSelectPlayerMenu {

    public CompassSelectPlayerMenu(Player player, int page){
        LanguageManager language = new LanguageManager(player);
        Inventory inventory = Bukkit.createInventory(null, 3*9, language.transalte(164)+page+" / "+((Bukkit.getOnlinePlayers().size()/(9*2))+1));
        player.openInventory(inventory);

        for(int i = 0;i<9*3;i++){
            inventory.setItem(i,placeholder());
        }

        ItemStack close = new ItemStack(Material.BARRIER);
        ItemMeta closemeta = close.getItemMeta();
        closemeta.setDisplayName(language.transalte(10));
        close.setItemMeta(closemeta);

        ItemStack previouspage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta previousmeta = previouspage.getItemMeta();
        previousmeta.setDisplayName(language.transalte(11));
        previouspage.setItemMeta(previousmeta);

        ItemStack back = new ItemStack(Material.SPRUCE_DOOR);
        ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName(language.transalte(12));
        back.setItemMeta(backmeta);

        ItemStack nextpage = new ItemStack(Material.SPRUCE_SIGN);
        ItemMeta nextmeta = nextpage.getItemMeta();
        nextmeta.setDisplayName(language.transalte(13));
        nextpage.setItemMeta(nextmeta);

        ArrayList<String> Players = new ArrayList<String>();
//        if(CBossbar.getByPlayer(player)==null){
//            CBossbar compass = new CBossbar(PositionatorMain.getPlugin());
//            compass.createBossbar(player);
//        }
        ArrayList<String> tempPositionName = new ArrayList<String>();
        if(CBossbar.getByPlayer(player) != null){
            for(CPosition position : CBossbar.getByPlayer(player).getPositions()){
                tempPositionName.add(position.getDescription());
            }
        }
        for(Player tempPlayer : Bukkit.getOnlinePlayers()){
                if(!tempPositionName.contains(tempPlayer.getName())){
                    if(!tempPlayer.getName().equals(player.getName())) Players.add(tempPlayer.getName());
                    //Players.add(tempPlayer.getName());
                }
        }
        int multiplier = 18;

        for(int i = 0;i<=17;i++){
            if(Players.size()>i+(multiplier*(page-1))){
                ItemStack playerSkull = new ItemStack(Material.PLAYER_HEAD);
                SkullMeta playerSkullMeta = (SkullMeta) playerSkull.getItemMeta();
                playerSkullMeta.setOwner(Players.get(i+(multiplier*(page-1))));
                playerSkullMeta.setDisplayName(ChatColor.GOLD+Players.get(i+(multiplier*(page-1))));
                ArrayList playerSkullLore = new ArrayList();
                if(General.PublicFilter.containsKey(player.getUniqueId().toString())){
                    if(General.PublicFilter.get(player.getUniqueId().toString()).hasPlayername()){
                        if(General.PublicFilter.get(player.getUniqueId().toString()).getPlayername().equalsIgnoreCase(Players.get(i+(multiplier*(page-1))))){
                            playerSkullLore.add(language.transalte(66));
                            playerSkull.addUnsafeEnchantment(Enchantment.LURE,1);
                        }
                    }
                }
                playerSkullMeta.setLore(playerSkullLore);
                playerSkull.setItemMeta(playerSkullMeta);
                inventory.setItem(i, playerSkull);
            }
        }


        inventory.setItem(18, previouspage);
        inventory.setItem(24, nextpage);
        inventory.setItem(25, back);
        inventory.setItem(26, close);
    }

    public static ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
