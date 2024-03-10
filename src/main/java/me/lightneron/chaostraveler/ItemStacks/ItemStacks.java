package me.lightneron.chaostraveler.ItemStacks;

import me.lightneron.chaostraveler.ChaosTraveler;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Objects;

public class ItemStacks {
    public static ItemStack NormalTeleport(Player player) {
        ArrayList<Component> Lore = new ArrayList<>();


        if (JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getBoolean("NormalTeleport.ShowRadiusInMenu")) {
            Lore.add(MiniMessage.miniMessage().deserialize("<#ffff55>Радиус:"));
            Lore.add(MiniMessage.miniMessage().deserialize(" <#ffed4f>Минимальный: <#4a65ff>" + JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getInt("NormalTeleport.RadiusMin")));
            Lore.add(MiniMessage.miniMessage().deserialize(" <#ffed4f>Максимальный: <#4a65ff>" + JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getInt("NormalTeleport.RadiusMax")));
        }

        if (player.hasPermission("ChaosTraveler.NormalTeleport")) {
            Lore.add(MiniMessage.miniMessage().deserialize("<#36fff2>Разрешено: " + "<#0dff00>Да"));
        } else {
            Lore.add(MiniMessage.miniMessage().deserialize("<#36fff2>Разрешено: " + "<#ff0d0d>Нет"));
        }


        ItemStack NormalTeleport = new ItemStack(Objects.requireNonNull(Material.getMaterial(Objects.requireNonNull(JavaPlugin.getPlugin(ChaosTraveler.class).getConfig().getString("NormalTeleport.Item")))));
        ItemMeta NormalTeleportMeta = NormalTeleport.getItemMeta();
        NormalTeleportMeta.displayName(MiniMessage.miniMessage().deserialize("<#55ffff>Обычная телепортация"));
        NormalTeleportMeta.lore(Lore);

        NormalTeleport.setItemMeta(NormalTeleportMeta);
        return NormalTeleport;
    }
}
