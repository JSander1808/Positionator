package de.rembel.Main;

import de.rembel.Commands.PositionCommand;
import de.rembel.Config.Config;
import de.rembel.Listener.*;
import org.bukkit.Bukkit;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Iterator;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        Config publicConfig = new Config("data//position//public.conf");
        publicConfig.init();

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new InventoryListener(),this);
        pluginManager.registerEvents(new PublicMenuListener(),this);
        pluginManager.registerEvents(new PublicSettingsMenuListener(),this);
        pluginManager.registerEvents(new PrivateMenuListener(),this);
        pluginManager.registerEvents(new PrivateSettingsMenuListener(),this);
        pluginManager.registerEvents(new addMenuListener(),this);
        pluginManager.registerEvents(new PlayerDeathListener(),this);
        pluginManager.registerEvents(new PlayerMoveListener(), this);
        pluginManager.registerEvents(new PlayerJoinListener(), this);

        getCommand("pos").setExecutor(new PositionCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
