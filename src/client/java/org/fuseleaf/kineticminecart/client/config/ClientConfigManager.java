package org.fuseleaf.kineticminecart.client.config;

import java.util.List;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.util.Constant;

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

    public static ConfigHolder<ConfigData> getConfigHolder() {
        return ConfigManager.getConfigHolder();
    }

    public static ConfigData.Enum.ApplicationMode getApplicationMode() {
        return ConfigManager.getApplicationMode();
    }

    public static ConfigData.Enum.DamageMode getDamageMode() {
        return ConfigManager.getDamageMode();
    }

    public static List<String> getExclusionList() {
        return ConfigManager.getExclusionList();
    }
}
