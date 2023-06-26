package de.rembel.Listener;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.General.General;
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
        NormalConfig normalConfig = new NormalConfig("plugins//Positionator//config.yml");
        Config config = new Config("plugins//Positionator//"+ event.getEntity().getUniqueId().toString()+".conf");
        boolean finish = false;
        int temp = 1;
        Player player = event.getEntity();
        while(!finish){
            if(!config.existdata("Death-"+temp)){
                String publicposition = (int) player.getLocation().getX()+" "+(int) player.getLocation().getY()+" "+(int) player.getLocation().getZ();

                config.set("Death-"+temp,publicposition,player.getName(),player.getWorld().getEnvironment().name(),1);

                if(normalConfig.getBoolean("setDeathPositionInBossbar")){
                    if(Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString()))!=null) {
                        Bukkit.getBossBar(NamespacedKey.fromString(player.getUniqueId().toString())).removeAll();
                        Bukkit.removeBossBar(NamespacedKey.fromString(player.getUniqueId().toString()));
                    }

                    General.BossBarPosition.remove(player.getUniqueId().toString());

                    if(!config.get("Death-"+temp)[3].equalsIgnoreCase(player.getLocation().getWorld().getEnvironment().name())){
                        BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Dimension: "+ChatColor.GREEN+config.get("Death-"+temp)[3],BarColor.GREEN,BarStyle.SOLID);
                        bar.setProgress(1.0);
                        bar.addPlayer(player);
                    }else{
                        String[] position = config.get("Death-"+temp)[1].split(" ");
                        Location target = new Location(player.getWorld(), Integer.valueOf(position[0]), Integer.valueOf(position[1]), Integer.valueOf(position[2]));
                        BossBar bar = Bukkit.createBossBar(NamespacedKey.fromString(player.getUniqueId().toString()), ChatColor.GOLD+"Coordinates: "+ChatColor.GREEN+config.get("Death-"+temp)[1]+ChatColor.GOLD+"     Distance: "+ChatColor.GREEN+Math.round(player.getLocation().distance(target)),BarColor.RED,BarStyle.SOLID);
                        bar.setProgress(1.0);
                        bar.addPlayer(player);
                    }

                    General.BossBarPosition.put(player.getUniqueId().toString(), config.get("Death-"+temp)[3]+"->"+config.get("Death-"+temp)[1]);
                }

                finish=true;
            }
            temp++;
        }
    }
}
