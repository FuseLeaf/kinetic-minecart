package org.fuseleaf.kineticminecart;

import org.fuseleaf.kineticminecart.initializer.Initializer;

import net.fabricmc.api.ModInitializer;

public class KineticMinecart implements ModInitializer {

    public static final String MOD_ID = "kinetic-minecart";

    @Override
    public void onInitialize() {
        Initializer.init();
    }
}
