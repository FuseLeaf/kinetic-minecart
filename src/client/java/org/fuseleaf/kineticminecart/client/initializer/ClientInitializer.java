package org.fuseleaf.kineticminecart.client.initializer;

import java.util.stream.Collectors;

import org.fuseleaf.kineticminecart.client.config.ClientConfigManager;
import org.fuseleaf.kineticminecart.client.util.Toast;
import org.fuseleaf.kineticminecart.entity.ExclusionList;
import org.fuseleaf.kineticminecart.util.Constant;
import org.fuseleaf.kineticminecart.util.Log;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;

public class ClientInitializer {

    public static void init() {
        initConfig();
    }

    private static void initConfig() {
        try {
            Class.forName(
                Constant.Extension.AUTO_CONFIG_CLASS_NAME,
                false,
                ClientInitializer.class.getClassLoader()
            );

            ClientConfigManager.getConfigHolder().registerSaveListener((configHolder, config) -> {
                if (config.enabledExclusionList) {

                    config.exclusionList = config.exclusionList.stream()
                        .map(ExclusionList::normalizeId)
                        .collect(Collectors.toList());

                    Component title = Component.translatable(Constant.I18n.TOAST_EXCLUSION_LIST_TITLE);
                    Component desc = ExclusionList.buildMessage(config.exclusionList);
                    Toast.toast(title, desc);
                }

                return InteractionResult.SUCCESS;
            });
        } catch (ClassNotFoundException e) {
            Log.LOGGER.debug("AutoConfig not found on the client; config entry will be disabled.");
        }
    }
}
