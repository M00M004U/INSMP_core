package org.insmp.iNSMPCore;

import org.bukkit.plugin.java.JavaPlugin;
import org.insmp.iNSMPCore.listeners.BlockInteractListener;
import org.insmp.iNSMPCore.listeners.PortalListener;

public final class INSMPCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        saveDefaultConfig(); //Creates a new config.yml if one doesn't exist.
        getConfig(); //Loads the config file(s) values.

        getServer().getPluginManager().registerEvents(new BlockInteractListener(this), this);
        getServer().getPluginManager().registerEvents(new PortalListener(this), this);

        getLogger().info("INSMP-Core enabled!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

        getLogger().info("INSMP-Core disabled!");
    }
}
