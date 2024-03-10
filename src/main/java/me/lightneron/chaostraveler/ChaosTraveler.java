package me.lightneron.chaostraveler;

import me.lightneron.chaostraveler.Commands.PluginConfigReload;
import me.lightneron.chaostraveler.Commands.RTPMenuCommand;
import me.lightneron.chaostraveler.Listeners.RTPMenu;
import org.bukkit.plugin.java.JavaPlugin;

public final class ChaosTraveler extends JavaPlugin {
    RTPMenuCommand rtpMenuCommand = new RTPMenuCommand();
    PluginConfigReload pluginConfigReload = new PluginConfigReload();


    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        saveDefaultConfig();
        CheckRadiusValid();
        rtpMenuCommand.MenuCommand();
        pluginConfigReload.reload();
        getServer().getPluginManager().registerEvents(new RTPMenu(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void CheckRadiusValid() {
        if (this.getConfig().getInt("NormalTeleport.RadiusMin") > this.getConfig().getInt("NormalTeleport.RadiusMax")) {
            getLogger().warning("Минимальный радиус не может быть больше чем максимальный");
            getLogger().warning("Поэтому минимальный и максимальный радиус был сброшен до дефолтных значений");
            getConfig().set("NormalTeleport.RadiusMin", 3000);
            getConfig().set("NormalTeleport.RadiusMax", 3000);
            saveConfig();
        }
    }
}
