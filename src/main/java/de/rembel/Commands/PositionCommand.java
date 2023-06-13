package de.rembel.Commands;

import de.rembel.Config.Config;
import de.rembel.Menus.StartMenu;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class PositionCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = ((Player) sender).getPlayer();
            if(args.length==0){
                new StartMenu(player);
            }else if(args.length>=1){
                String data = args[0];

                for(int i = 1;i< args.length;i++){
                    data +="-"+args[i];
                }
                for(int i = 0;i<100;i++){
                    data = data.replace("->","");
                }
                Inventory inv = Bukkit.createInventory(null,1*9, ChatColor.GOLD+"Add "+data);
                for(int i = 0;i<1*9;i++){
                    inv.setItem(i,placeholder());
                }
                ItemStack addpublic = new ItemStack(Material.CHEST);
                ItemMeta addpublicMeta = addpublic.getItemMeta();
                addpublicMeta.setDisplayName(ChatColor.GOLD+"Add to public list");
                ArrayList addpubliclore = new ArrayList();
                addpubliclore.add(ChatColor.GREEN+"Anyone can see this list");
                addpublicMeta.setLore(addpubliclore);
                addpublic.setItemMeta(addpublicMeta);

                ItemStack addprivate = new ItemStack(Material.ENDER_CHEST);
                ItemMeta addprivateMeta = addprivate.getItemMeta();
                addprivateMeta.setDisplayName(ChatColor.GOLD+"Add to private list");
                ArrayList addprivatelore = new ArrayList();
                addprivatelore.add(ChatColor.GREEN+"Only you can see this list");
                addprivateMeta.setLore(addprivatelore);
                addprivate.setItemMeta(addprivateMeta);

                inv.setItem(3,addpublic);
                inv.setItem(5,addprivate);
                player.openInventory(inv);
            }
        }
        return false;
    }

    public ItemStack placeholder(){
        ItemStack nul = new ItemStack(Material.BLACK_STAINED_GLASS_PANE);
        ItemMeta nulmeta = nul.getItemMeta();
        nulmeta.setDisplayName(" ");
        nul.setItemMeta(nulmeta);
        return nul;
    }
}
