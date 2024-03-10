package me.lightneron.chaostraveler.Exceptions;

import me.lightneron.chaostraveler.ChaosTraveler;
import org.bukkit.plugin.java.JavaPlugin;

public class RadiusValidException extends Exception{
    public RadiusValidException() {
        JavaPlugin.getPlugin(ChaosTraveler.class).getLogger().warning("Минимальный радиус не может быть больше чем максимальный");
        JavaPlugin.getPlugin(ChaosTraveler.class).getLogger().warning("Поэтому минимальный и максимальный радиус был сброшен до дефолтных значений");
        JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().set("NormalTeleport.RadiusMin", -3000);
        JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().set("NormalTeleport.RadiusMax", 3000);
        JavaPlugin.getPlugin(ChaosTraveler.class).saveConfig();
    }
}
