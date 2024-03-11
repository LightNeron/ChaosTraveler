package me.lightneron.chaostraveler.Commands;


import dev.jorel.commandapi.CommandAPICommand;
import me.clip.placeholderapi.PlaceholderAPI;
import me.lightneron.chaostraveler.ChaosTraveler;
import me.lightneron.chaostraveler.Listeners.RTPMenu;
import me.lightneron.chaostraveler.utils.CooldownManager;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Duration;

public class RTPMenuCommand {
    public void MenuCommand() {
        new CommandAPICommand("rtp")
                .executesPlayer((player, commandArguments) -> {
                     Duration timeleft = CooldownManager.getRemainingCooldownRTPMenuCommand(player.getUniqueId());
                     String Prefix = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("prefix");
                     String NoPermission = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("NoPermission");
                     String CooldownMessage = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("CooldownMessage");

                    if (timeleft.isZero() || timeleft.isNegative()) {
                        if (player.hasPermission("ChaosTraveler.RTPMenu")) {
                            player.openInventory(new RTPMenu().RTPMenuHandler(player));
                        } else {
                            player.sendMessage(MiniMessage.miniMessage().deserialize(Prefix + " " + NoPermission));
                        }
                        CooldownManager.setCooldownRTPMenuCommand(player.getUniqueId(), Duration.ofSeconds(5));
                    } else {
                        player.sendMessage(MiniMessage.miniMessage().deserialize(PlaceholderAPI.setPlaceholders(player, Prefix + " " + CooldownMessage)));
                    }




                })
                .register();
    }
}
