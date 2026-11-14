package org.fuseleaf.kineticminecart;

import org.fuseleaf.kineticminecart.initializer.Initializer;
import org.fuseleaf.kineticminecart.util.Constant;

import net.neoforged.fml.common.Mod;

@Mod(Constant.Core.MOD_ID)
public class KineticMinecart {

    public KineticMinecart() {
        Initializer.init();
    }
}
