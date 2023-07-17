package de.rembel.Listener;

import de.rembel.General.General;
import de.rembel.Language.LanguageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();
        LanguageManager languageManager = new LanguageManager(player);
        if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
            BossBar bar = Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
            if(General.BossBarPosition.get(player.getUniqueId().toString()) == null){
                Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
            }else{
                if(!General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[0].equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                    bar.setTitle(languageManager.transalte(44)+ChatColor.GREEN+General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[0]);
                }else{
                    String[] position = General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[1].split(" ");
                    Location target = new Location(player.getWorld(), Integer.valueOf(position[0]), Integer.valueOf(position[1]), Integer.valueOf(position[2]));
                    bar.setTitle(languageManager.transalte(45)+ChatColor.GREEN+General.BossBarPosition.get(player.getUniqueId().toString()).split("->")[1]+languageManager.transalte(46)+ChatColor.GREEN+Math.round(player.getLocation().distance(target)));
                }
            }
        }
    }
}
