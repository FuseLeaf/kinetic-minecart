package org.fuseleaf.kineticminecart.client.extension.config;

import org.fuseleaf.kineticminecart.client.config.ClientConfigManager;
import org.fuseleaf.kineticminecart.client.gui.IllegalOperationScreen;
import org.fuseleaf.kineticminecart.client.util.Toast;
import org.fuseleaf.kineticminecart.util.Constant;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class ConfigEntry {

    public static Screen get(Screen parent) {
        Minecraft mc = Minecraft.getInstance();

        boolean inWorld = mc.level != null && mc.player != null;
        boolean isMultiplayerWorld = inWorld && mc.getCurrentServer() != null;

        if (isMultiplayerWorld) {
            return IllegalOperationScreen.get(parent);
        }

        if (!ClientConfigManager.isConfigAvailable()) {
            Component title = Component.translatable(Constant.I18n.TOAST_API_NOT_FOUND_TITLE);
            Component desc = Component.translatable(Constant.I18n.TOAST_API_NOT_FOUND_DESC);
            Toast.toast(title, desc);
            return null;
        } else {
            return ConfigGuiBuilder.get().setParentScreen(parent).build();
        }
    }
}
