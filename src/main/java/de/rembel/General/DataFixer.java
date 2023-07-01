package de.rembel.General;

import de.rembel.Config.Config;
import de.rembel.Config.NormalConfig;
import de.rembel.Config.OldNormalConfig;
import de.rembel.Main.PositionatorMain;
import org.bukkit.plugin.Plugin;

import java.util.logging.Level;

public class DataFixer {

    private Plugin plugin = PositionatorMain.getPlugin();

    public DataFixer(){
        plugin.getLogger().log(Level.INFO, "[DataFixer] Starting...");
        DataFixerV1_0_4();

        //after Fixes

        Config publicConfig = new Config("plugins//Positionator//public.conf");
        publicConfig.init();

        NormalConfig config = new NormalConfig("plugins//Positionator//config.yml");
        config.init();
        if(!config.existdata("showDeathPositionInList")) config.set("showDeathPositionInList","true");
        if(!config.existdata("setDeathPositionInBossbar")) config.set("setDeathPositionInBossbar","true");
        if(!config.existdata("enableFilter")) config.set("enableFilter","true");
        if(!config.existdata("enableMenuClickSound")) config.set("enableMenuClickSound","false");
        if(!config.existdata("enableDeletePositionsFromOtherPlayer")) config.set("enableDeletePositionsFromOtherPlayer","true");
        if(!config.existdata("sendUpdateMessages")) config.set("sendUpdateMessages","true");
        plugin.getLogger().log(Level.INFO, "[DataFixer] Completed");
    }

    private boolean DataFixerV1_0_4(){
        //convert old config from V.1.0.3-SNAPSHOT to config from V.1.0.4
        OldNormalConfig oldConfig = new OldNormalConfig("plugins//Positionator//config.yml");
        oldConfig.init();
        if(oldConfig.existdata("firstUse")){
            oldConfig.clearFile();
            plugin.getLogger().log(Level.INFO,"[DataFixer] convert Config in new Format");
        }
        return true;
    }
}
