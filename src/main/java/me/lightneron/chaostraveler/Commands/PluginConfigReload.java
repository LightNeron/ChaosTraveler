package me.lightneron.chaostraveler.Commands;

import dev.jorel.commandapi.CommandAPICommand;
import me.lightneron.chaostraveler.ChaosTraveler;
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
                                JavaPlugin.getPlugin(ChaosTraveler.class).CheckRadiusValid();
                                JavaPlugin.getPlugin(ChaosTraveler.class).reloadConfig();
                                player.sendMessage(MiniMessage.miniMessage().deserialize(Prefix + " <#34ff14>Конфиг успешно перезагружен"));
                            }
                        })
                )
                .register();
    }
}

