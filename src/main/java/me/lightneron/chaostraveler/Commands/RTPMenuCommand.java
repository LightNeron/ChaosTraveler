package me.lightneron.chaostraveler.Commands;


import dev.jorel.commandapi.CommandAPICommand;
import me.lightneron.chaostraveler.ChaosTraveler;
import me.lightneron.chaostraveler.Listeners.RTPMenu;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.plugin.java.JavaPlugin;

public class RTPMenuCommand {
    public void MenuCommand() {
        new CommandAPICommand("rtp")
                .executesPlayer((player, commandArguments) -> {
                    RTPMenu rtpMenu = new RTPMenu();
                    String Prefix = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("prefix");
                    String NoPermission = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("NoPermission");
                    if (player.hasPermission("ChaosTraveler.RTPMenu")) {
                        player.openInventory(rtpMenu.RTPMenuHandler(player));
                    } else {
                        player.sendMessage(MiniMessage.miniMessage().deserialize(Prefix + " " + NoPermission));
                    }
                })
                .register();
    }
}
