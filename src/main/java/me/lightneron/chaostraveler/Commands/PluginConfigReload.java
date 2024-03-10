package me.lightneron.chaostraveler.Commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.lightneron.chaostraveler.ChaosTraveler;
import me.lightneron.chaostraveler.Exceptions.RadiusValidException;
import me.lightneron.chaostraveler.Exceptions.WorldValidException;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginConfigReload {
    public void reload() {
        new CommandAPICommand("rtp")
                .withSubcommand(new CommandAPICommand("reload")
                        .executes((player, commandArguments) -> {
                            String Prefix = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("prefix");
                            String NoPermission = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("NoPermission");
                            if (!player.hasPermission("ChaosTraveler.reload")) {
                                player.sendMessage(MiniMessage.miniMessage().deserialize(Prefix + " " + NoPermission));
                            } else {
                                JavaPlugin.getPlugin(ChaosTraveler.class).reloadConfig();
                                try {
                                    JavaPlugin.getPlugin(ChaosTraveler.class).CheckRadiusValid();
                                } catch (RadiusValidException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    JavaPlugin.getPlugin(ChaosTraveler.class).CheckWorldValid();
                                } catch (WorldValidException e) {
                                    e.printStackTrace();
                                }
                                JavaPlugin.getPlugin(ChaosTraveler.class).reloadConfig();
                                player.sendMessage(MiniMessage.miniMessage().deserialize(Prefix + " <#34ff14>Конфиг успешно перезагружен"));
                            }
                        })
                )
                .register();
    }
}

