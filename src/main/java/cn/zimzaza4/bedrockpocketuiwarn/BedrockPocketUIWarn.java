package cn.zimzaza4.bedrockpocketuiwarn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BedrockPocketUIWarn extends JavaPlugin {
    public static BedrockPocketUIWarn inst;
    @Override
    public void onEnable() {
         inst = this;
        if (!new File(getDataFolder(), "config.yml").exists()){

            saveDefaultConfig();
        }

        Bukkit.getPluginManager().registerEvents(new Listener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
