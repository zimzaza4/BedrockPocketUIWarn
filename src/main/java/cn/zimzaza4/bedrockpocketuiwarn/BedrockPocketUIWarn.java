package cn.zimzaza4.bedrockpocketuiwarn;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class BedrockPocketUIWarn extends JavaPlugin {
    public static BedrockPocketUIWarn inst;
    public WarnType ConfigWarnType;
    @Override
    public void onEnable() {
        inst = this;
        if (!new File(getDataFolder(), "config.yml").exists()){
            saveDefaultConfig();
        }
        String type = getConfig().getString("warn-type", "form");
        if (type.equals("form")){
            ConfigWarnType = WarnType.FORM;
        }else if (type.equals("message")){
            ConfigWarnType = WarnType.MESSAGE;
        }else if (type.equals("kick")){
            ConfigWarnType = WarnType.KICK;
        }else{
            System.out.println("BedrockPocketUIWarn: 未知类型");
        }
        Bukkit.getPluginManager().registerEvents(new Listener(), this);
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }


    public enum WarnType{
        KICK,
        MESSAGE,
        FORM,
    }
}
