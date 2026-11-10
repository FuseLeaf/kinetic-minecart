package org.fuseleaf.kineticminecart.client.initializer;

import java.util.stream.Collectors;

import org.fuseleaf.kineticminecart.client.util.Toast;
import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.entity.ExclusionList;
import org.fuseleaf.kineticminecart.util.Constant;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

public class ClientInitializer {

    public static void init() {
        Config.init();
    }

    private static class Config {

        private static void init() {
            try {
                Class.forName(Constant.Extension.AUTO_CONFIG_CLASS_NAME);

                ConfigManager.getConfigHolder().registerSaveListener((configHolder, config) -> {
                    if (ConfigManager.isEnabledExclusionList()) {

                        config.exclusionList = config.exclusionList.stream()
                            .map(ExclusionList::normalizeId)
                            .collect(Collectors.toList());

                        Component title = Component.translatable(Constant.I18n.EXCLUSION_LIST_TITLE);
                        Component desc = ExclusionList.buildMessage(ConfigManager.getExclusionList());
                        Toast.toast(title, desc);
                    }

                    return InteractionResult.SUCCESS;
                });
            } catch (ClassNotFoundException e) {}
        }
    }
}
