package me.lightneron.chaostraveler.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {
    private static final Map<UUID, Instant> RTPMenuCommand = new HashMap<>();

    // Set cooldown
    public static void setCooldownRTPMenuCommand(UUID key, Duration duration) {
        RTPMenuCommand.put(key, Instant.now().plus(duration));
    }

    // Check if cooldown has expired
    public boolean hasCooldownRTPMenuCommand(UUID key) {
        Instant cooldown = RTPMenuCommand.get(key);
        return cooldown != null && Instant.now().isBefore(cooldown);
    }

    // Remove cooldown
    public Instant removeCooldownRTPMenuCommand(UUID key) {
        return RTPMenuCommand.remove(key);
    }

    // Get remaining cooldown time
    public static Duration getRemainingCooldownRTPMenuCommand(UUID key) {
        Instant cooldown = RTPMenuCommand.get(key);
        Instant now = Instant.now();
        if (cooldown != null && now.isBefore(cooldown)) {
            return Duration.between(now, cooldown);
        } else {
            return Duration.ZERO;
        }
    }
}
