package me.lightneron.chaostraveler.PlaceHolders;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.lightneron.chaostraveler.utils.CooldownManager;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.Duration;


public class CooldownPlaceholder extends PlaceholderExpansion {

    @Override
    public @NotNull String getIdentifier() {
        return "ChaosTraveler";
    }

    @Override
    public @NotNull String getAuthor() {
        return "LightNeron";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0";
    }

    @Override
    public boolean persist() {
        return true;
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        Duration timeleft = CooldownManager.getRemainingCooldownRTPMenuCommand(player.getUniqueId());
        if (params.equalsIgnoreCase("RTPCommandCooldown")) {
            return  String.valueOf(timeleft.toSeconds());
        }
        return null;
    }
}
