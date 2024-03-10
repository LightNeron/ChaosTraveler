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
        return Menu;
    }

    @EventHandler
    public void RTPMenuListener(InventoryClickEvent event) {
        //Получаем item по которому нажали

        ItemStack clickedItem = event.getCurrentItem();
        Player player = (Player) event.getWhoClicked();
        //Проверяем название меню в формате Component
        if (event.getView().title().equals(MenuName)) {
                //Отменяем перенос предмета и тд
                event.setCancelled(true);
            //Проверяем по какому ItemStack нажали
            if (clickedItem != null && clickedItem.isSimilar(ItemStacks.NormalTeleport(player)) && WorldName != null && Bukkit.getWorld(WorldName) != null) {
                //Код для рандомной телепортации

                SecureRandom secureRandom = new SecureRandom();
                int getMin = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getInt("NormalTeleport.RadiusMin");
                int getMax = JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getInt("NormalTeleport.RadiusMax");
                double x = secureRandom.nextDouble(getMin) - secureRandom.nextDouble(getMax);
                double z = secureRandom.nextDouble(getMin) - secureRandom.nextDouble(getMax);
                double y = player.getWorld().getHighestBlockYAt((int) x, (int) z) + 1;

                Location NormalTeleportLocation = new Location(Bukkit.getWorld(WorldName), x, y, z, secureRandom.nextFloat(1), secureRandom.nextFloat(1));
                player.sendMessage(MiniMessage.miniMessage().deserialize("<#0dff41>Телепортация..."));
                player.teleport(NormalTeleportLocation);

                String Xformat = String.format("X  %.2f", x);
                String Yformat = String.format(" Y  %.2f", y);
                String Zformat = String.format(" Z  %.2f", z);

                player.sendMessage(MiniMessage.miniMessage().deserialize("<#0dff41>Вы были телепортированы на координаты: "
                + Xformat
                + Yformat
                + Zformat));

            }

        }
    }
}
