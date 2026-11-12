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

    /* # Get Configuration */

    /* ## General */

    public static boolean isEnabled() {
        return !isConfigAvailable() || config.enabled;
    }

    public static boolean isOverrideCollision() {
        return !isConfigAvailable() || config.overrideCollision;
    }

    public static boolean isOverrideRiding() {
        return !isConfigAvailable() || config.overrideRiding;
    }

    public static ConfigData.Enum.ApplicationMode getSelectedApplicationMode() {
        return isConfigAvailable() ? config.selectedApplicationMode : null;
    }

    public static double getRadius() {
        return (double)config.intRadius / 10.0;
    }

    public static boolean isEnabledDamage() {
        return !isConfigAvailable() || config.enabledDamage;
    }

    public static ConfigData.Enum.DamageMode getSelectedDamageMode() {
        return isConfigAvailable() ? config.selectedDamageMode : null;
    }

    public static boolean isEnabledKnock() {
        return !isConfigAvailable() || config.enabledKnock;
    }

    /* ## Types */

    public static boolean isExcludePlayer() {
        return isConfigAvailable() && config.excludePlayer;
    }

    public static boolean isExcluePet() {
        return isConfigAvailable() && config.excludePet;
    }

    public static boolean isExcludePassenger() {
        return !isConfigAvailable() || config.excludePassenger;
    }

    public static boolean isExcludeNamedEntity() {
        return isConfigAvailable() && config.excludeNamedEntity;
    }

    public static boolean isExcludItemEntity() {
        return isConfigAvailable() && config.excludeItemEntity;
    }

    /* ## Advanced */

    public static boolean isEnabledExclusionList() {
        return isConfigAvailable() && config.enabledExclusionList;
    }

    public static List<String> getExclusionList() {
        return config.exclusionList;
    }
}
