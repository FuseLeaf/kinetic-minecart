package org.fuseleaf.kineticminecart.initializer;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.util.Delay;
import org.fuseleaf.kineticminecart.util.Constant;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Initializer {

    public static void init() {
        Config.init();
        Events.init();
    }

    private static class Config {

        private static void init() {
            try {
                Class.forName(Constant.Extension.AUTO_CONFIG_CLASS_NAME);

                AutoConfig.register(ConfigData.class, GsonConfigSerializer::new);
                ConfigManager.setConfig(AutoConfig.getConfigHolder(ConfigData.class));
            } catch (ClassNotFoundException e) {}
        }
    }

    private static class Events {

        private static void init() {
            ServerTickEvents.END_SERVER_TICK.register(server -> Delay.register());
        }
    }
}
