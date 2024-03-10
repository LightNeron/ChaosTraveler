package me.lightneron.chaostraveler;

import me.lightneron.chaostraveler.Commands.PluginConfigReload;
import me.lightneron.chaostraveler.Commands.RTPMenuCommand;
import me.lightneron.chaostraveler.Exceptions.RadiusValidException;
import me.lightneron.chaostraveler.Exceptions.WorldValidException;
import me.lightneron.chaostraveler.Listeners.RTPMenu;
import org.bukkit.Bukkit;
import org.bukkit.World;
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
        try {
            CheckRadiusValid();
        } catch (RadiusValidException e) {
            e.printStackTrace();
        }
        try {
            CheckWorldValid();
        } catch (WorldValidException e) {
            e.printStackTrace();
        }
        rtpMenuCommand.MenuCommand();
        pluginConfigReload.reload();
        getServer().getPluginManager().registerEvents(new RTPMenu(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void CheckRadiusValid() throws RadiusValidException {
        if (this.getConfig().getInt("NormalTeleport.RadiusMin") > this.getConfig().getInt("NormalTeleport.RadiusMax")) throw new RadiusValidException();
    }

    public void CheckWorldValid() throws WorldValidException {
        String WorldName = this.getConfig().getString("NormalTeleport.WorldName");
        assert WorldName != null;
        World world = Bukkit.getWorld(WorldName);
        if (world == null) throw new WorldValidException();
    }
}
