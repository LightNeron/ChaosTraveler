package me.lightneron.chaostraveler.Exceptions;

import me.lightneron.chaostraveler.ChaosTraveler;
import org.bukkit.plugin.java.JavaPlugin;

public class WorldValidException extends Exception{
    public WorldValidException() {
        JavaPlugin.getPlugin(ChaosTraveler.class).getLogger().warning("Мира указанного в конфиге не существует либо он не загружен в памяти");
        JavaPlugin.getPlugin(ChaosTraveler.class).getLogger().warning("Название мира в конфиге изменено на world");
        JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().set("NormalTeleport.WorldName", "world");
        JavaPlugin.getPlugin(ChaosTraveler.class).saveConfig();
    }

}
