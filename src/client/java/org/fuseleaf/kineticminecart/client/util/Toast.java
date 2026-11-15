package org.fuseleaf.kineticminecart.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.toasts.SystemToast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;

public class Toast {

    private Toast() {}

    public static void toast(Component title, Component description) {
        if (title == null) {
            return;
        }

        ToastComponent toastComponent = Minecraft.getInstance().getToasts();
        SystemToast.addOrUpdate(
            toastComponent,
            SystemToast.SystemToastId.NARRATOR_TOGGLE,
            title,
            description
        );
    }
}
