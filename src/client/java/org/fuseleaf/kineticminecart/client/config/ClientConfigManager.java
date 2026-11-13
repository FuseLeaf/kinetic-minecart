package org.fuseleaf.kineticminecart.client.config;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.util.Constant;

import org.jspecify.annotations.NonNull;

import me.shedaniel.autoconfig.ConfigHolder;

public class ClientConfigManager {

    public static boolean isConfigAvailable() {
        try {
            Class.forName(Constant.Extension.AUTO_CONFIG_CLASS_NAME);

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    public static ConfigData getConfig() {
        return ConfigManager.getConfig();
    }

    public static ConfigHolder<@NonNull ConfigData> getConfigHolder() {
        return ConfigManager.getConfigHolder();
    }
}
