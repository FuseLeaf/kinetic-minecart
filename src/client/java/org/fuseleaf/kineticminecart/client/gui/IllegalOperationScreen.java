package org.fuseleaf.kineticminecart.client.gui;

import org.fuseleaf.kineticminecart.util.Constant;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.ConfirmScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class IllegalOperationScreen {

    public static ConfirmScreen get(Screen parent) {
        return new ConfirmScreen(
            result -> Minecraft.getInstance().gui.setScreen(parent),  // Return to the previous menu.
            Component.translatable(Constant.I18n.ILLEGAL_OPERATION_TITLE),
            Component.translatable(Constant.I18n.ILLEGAL_OPERATION_DESC),
            Component.translatable(Constant.I18n.OPTION_CONFIRM),
            Component.translatable(Constant.I18n.OPTION_CONFIRM)
        );
    }
}
