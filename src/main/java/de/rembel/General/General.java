package de.rembel.General;

import de.rembel.CBossbar.CBossbar;
import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.boss.BarColor;
import org.bukkit.entity.Player;

import java.io.File;
import java.util.*;

public class General {

    public static HashMap<String, String> BossBarPosition = new HashMap<String, String>();
    public static HashMap<UUID, CBossbar> bossbarContainer = new HashMap<UUID, CBossbar>();
    public static HashMap<String, PositionFilter> PublicFilter = new HashMap<String, PositionFilter>();
    public static HashMap<String, PositionFilter> PrivateFilter = new HashMap<String, PositionFilter>();

    public static ArrayList<String> getRegisteredPlayers(){
        ArrayList<String> playerList = new ArrayList<String>();

        for(Player tempPlayer : Bukkit.getOnlinePlayers()){
            if(!playerList.contains(tempPlayer.getName())) playerList.add(tempPlayer.getName());
        }
        for(OfflinePlayer player : Bukkit.getOfflinePlayers()){
            if(!playerList.contains(player.getName())) playerList.add(player.getName());
        }
        return playerList;
    }

    public static String encode(String data){
        return new String(Base64.getEncoder().encode(data.getBytes()));
    }

    public static String decode(String data){
        return new String(Base64.getDecoder().decode(data.getBytes()));
    }

    public static boolean loadCompassData(CBossbar compass){
        if(compass==null || CBossbar.get(compass.getUuid())==null) return false;
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+compass.getPlayer().getUniqueId().toString()+"//config.yml");
        compass.setEnableDirectionWiser(config.getBoolean("compassDirectionWiser"));
        if(config.get("compassBossbarColor").equals("white")) compass.setBarColor(BarColor.WHITE);
        if(config.get("compassBossbarColor").equals("red")) compass.setBarColor(BarColor.RED);
        if(config.get("compassBossbarColor").equals("green")) compass.setBarColor(BarColor.GREEN);
        if(config.get("compassBossbarColor").equals("blue")) compass.setBarColor(BarColor.BLUE);
        if(config.get("compassBossbarColor").equals("purple")) compass.setBarColor(BarColor.PURPLE);
        if(config.get("compassBossbarColor").equals("yellow")) compass.setBarColor(BarColor.YELLOW);
        if(config.get("compassBossbarColor").equals("pink")) compass.setBarColor(BarColor.PINK);
        if(config.get("compassPlaceholder") != null || config.get("compassPlaceholder") != "" || config.get("compassPlaceholder") != " ") compass.setPlaceholder(config.get("compassPlaceholder")); else compass.setPlaceholder("|");
        compass.renderBossbar();
        return true;
    }

    public static boolean resetCompass(Player player){
        CBossbar compass = CBossbar.getByPlayer(player);
        NormalConfig config = new NormalConfig("plugins//Positionator//Data//User//"+player.getUniqueId().toString()+"//config.yml");
        config.set("compassAlwaysActive","false");
        config.set("compassSave","");
        if(compass!=null) compass.setBarColor(BarColor.WHITE);
        config.set("compassBossbarColor","white");
        if(compass!=null) compass.setPlaceholder("*");
        config.set("compassPlaceholder","*");
        if(compass!=null){
            compass.removeAllPoints();
            compass.remove();
        }
        return true;
    }

    public static List<Position> getAllPositions(){
        List<Position> allPositions = new ArrayList<Position>();

        Config publicConfig = new Config("plugins//Positionator//Data//public.conf");
        for(Position p : publicConfig.list()){
            allPositions.add(p);
        }

        File file = new File("plugins//Positionator//Data//User//");
        for(File f : file.listFiles()){
            Config privateConfig = new Config("plugins//Positionator//Data//User//"+ f.getName() +"//data.conf");

            for(Position p : privateConfig.list()){
                allPositions.add(p);
            }
        }

        return allPositions;
    }
}
