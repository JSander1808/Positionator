package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
import de.rembel.General.Position;
import de.rembel.General.PositionType;
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
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathListener implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent event){
        NormalConfig normalConfig = new NormalConfig("plugins//Positionator//Data//User//"+event.getEntity().getUniqueId().toString()+"//config.yml");
        Config config = new Config("plugins//Positionator//Data//User//"+ event.getEntity().getUniqueId().toString()+"//data.conf");
        boolean finish = false;
        int temp = 1;
        Player player = event.getEntity();
        while(!finish){
            Position position = new Position("Death-"+temp, new String[]{String.valueOf(player.getLocation().getBlockX()), String.valueOf(player.getLocation().getBlockY()), String.valueOf(player.getLocation().getBlockZ())}, player.getName(), player.getWorld().getEnvironment().name(), PositionType.DEATHPOSITION);
            if(!config.existPosition(position)){

                config.set(position);

                if(normalConfig.getBoolean("setDeathPositionInBossbar")){
                    if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
                        Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                        Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                    }

                    General.BossBarPosition.remove(player.getUniqueId().toString());

                    if(!config.get(position.getName()).getDimension().equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                        BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Dimension: "+ChatColor.GREEN+config.get(position.getName()).getDimension(),BarColor.GREEN,BarStyle.SOLID);
                        bar.setProgress(1.0);
                        bar.addPlayer(player);
                    }else{
                        BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Coordinates: "+ChatColor.GREEN+config.get(position.getName()).getPositionAsString()+ChatColor.GOLD+"     Distance: "+ChatColor.GREEN+Math.round(player.getLocation().distance(position.getLocation())),BarColor.RED,BarStyle.SOLID);
                        bar.setProgress(1.0);
                        bar.addPlayer(player);
                    }

                    General.BossBarPosition.put(player.getUniqueId().toString(), config.get(position.getName()).getDimension()+"->"+config.get(position.getName()).getPositionAsString());
                }

                finish=true;
            }
            temp++;
        }
    }
}
