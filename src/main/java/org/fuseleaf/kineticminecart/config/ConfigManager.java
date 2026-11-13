package org.fuseleaf.kineticminecart.config;

import java.util.List;

import org.fuseleaf.kineticminecart.extension.config.ConfigData;

import org.jspecify.annotations.NonNull;

import me.shedaniel.autoconfig.ConfigHolder;

public class ConfigManager {

    private static ConfigData config;

    private static ConfigHolder<@NonNull ConfigData> holder;

    public static void setConfig(ConfigHolder<@NonNull ConfigData> configHolder) {
        if (configHolder != null) {
            config = configHolder.getConfig();
            holder = configHolder;
        }
    }

    public static ConfigData getConfig() {
        return config;
    }

    public static boolean isConfigAvailable() {
        return config != null;
    }

    public static ConfigHolder<@NonNull ConfigData> getConfigHolder() {
        return holder;
    }

    public static boolean isEnabled() {
        return !isConfigAvailable() || config.enable;
    }

    public static boolean isCollisionOverrideEnabled() {
        return !isConfigAvailable() || config.overrideCollision;
    }

    public static boolean isRidingOverrideEnabled() {
        return !isConfigAvailable() || config.overrideRiding;
    }

    public static ConfigData.Enum.ApplicationMode getApplicationMode() {
        return isConfigAvailable() ? config.applicationMode : ConfigData.Enum.ApplicationMode.COLLIDE;
    }

    public static double getRadius() {
        return isConfigAvailable() ? (double)config.radius / 10.0 : 0.5;
    }

    public static boolean isDamageEnabled() {
        return !isConfigAvailable() || config.enableDamage;
    }

    public static ConfigData.Enum.DamageMode getDamageMode() {
        return isConfigAvailable() ? config.damageMode : ConfigData.Enum.DamageMode.TIERED;
    }

    public static boolean isKnockbackEnabled() {
        return !isConfigAvailable() || config.enableKnockback;
    }

    public static boolean isPlayerExcluded() {
        return isConfigAvailable() && config.excludePlayer;
    }

    public static boolean isPetExcluded() {
        return isConfigAvailable() && config.excludePet;
    }

    public static boolean isPassengerExcluded() {
        return !isConfigAvailable() || config.excludePassenger;
    }

    public static boolean isNamedEntityExcluded() {
        return isConfigAvailable() && config.excludeNamedEntity;
    }

    public static boolean isItemEntityExcluded() {
        return isConfigAvailable() && config.excludeItemEntity;
    }

    public static boolean isExclusionListEnabled() {
        return isConfigAvailable() && config.enableExclusionList;
    }

    public static List<String> getExclusionList() {
        return isConfigAvailable() ? config.exclusionList : List.of();
    }
}
