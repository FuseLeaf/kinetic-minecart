package org.fuseleaf.kineticminecart.initializer;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.util.Delay;
import org.fuseleaf.kineticminecart.util.Log;
import org.fuseleaf.kineticminecart.util.Constant;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

public class Initializer {

    public static void init() {
        initConfig();
    }

    public static void onServerTick() {
        Delay.tick();
    }

    private static void initConfig() {
        try {
            Class.forName(
                Constant.Extension.AUTO_CONFIG_CLASS_NAME,
                false,
                Initializer.class.getClassLoader()
            );

            AutoConfig.register(ConfigData.class, GsonConfigSerializer::new);
            ConfigManager.setConfig(AutoConfig.getConfigHolder(ConfigData.class));
        } catch (ClassNotFoundException e) {
            Log.LOGGER.debug("AutoConfig not found; falling back to default configuration.");
        }
    }
}
