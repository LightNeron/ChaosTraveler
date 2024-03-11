package me.lightneron.chaostraveler.Listeners;

import me.lightneron.chaostraveler.ChaosTraveler;
import me.lightneron.chaostraveler.ItemStacks.ItemStacks;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.security.SecureRandom;

public class RTPMenu implements Listener {
    Component MenuName = MiniMessage.miniMessage().deserialize("<#78ff93>Меню рандомной телепортации");
    String WorldName = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("NormalTeleport.WorldName");
    public Inventory RTPMenuHandler(Player player) {

        Inventory Menu = Bukkit.createInventory(null, 9, MenuName);
        Menu.setItem(0, ItemStacks.NormalTeleport(player));
        if (player.hasPermission("ChaosTraveler.reload")) {
            Menu.setItem(8, ItemStacks.ReloadConfig());
        }
        return Menu;
    }

    @EventHandler
    public void RTPMenuListener(InventoryClickEvent event) {
        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        if (event.getView().title().equals(MenuName)) {
                event.setCancelled(true);
            if (clickedItem != null && clickedItem.isSimilar(ItemStacks.NormalTeleport(player)) && WorldName != null && Bukkit.getWorld(WorldName) != null && player.hasPermission("ChaosTraveler.NormalTeleport")) {
                SecureRandom secureRandom = new SecureRandom();
                int getMin = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getInt("NormalTeleport.RadiusMin");
                int getMax = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getInt("NormalTeleport.RadiusMax");
                double x = secureRandom.nextInt(getMax - getMin + 1) + getMin;
                double z = secureRandom.nextInt(getMax - getMin + 1) + getMin;
                double y = player.getWorld().getHighestBlockYAt((int) x, (int) z) + 1;

                Location NormalTeleportLocation = new Location(Bukkit.getWorld(WorldName), x, y, z, secureRandom.nextFloat(1), secureRandom.nextFloat(1));
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#0dff41>Телепортация..."));
                player.teleport(NormalTeleportLocation);
                player.closeInventory();
                String Xformat = String.format("X  %.2f", x);
                String Yformat = String.format(" Y  %.2f", y);
                String Zformat = String.format(" Z  %.2f", z);

                player.sendMessage(MiniMessage.miniMessage().deserialize("<#0dff41>Вы были телепортированы на координаты: "
                + Xformat
                + Yformat
                + Zformat));
            } else {
                String Prefix = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("prefix");
                String NoPermission = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("NoPermission");
                player.sendMessage(MiniMessage.miniMessage().deserialize(Prefix + " " + NoPermission));
            }
            if (clickedItem != null && clickedItem.isSimilar(ItemStacks.ReloadConfig())) {
                event.setCancelled(true);
                player.performCommand("rtp reload");
            }
        }
    }
}
