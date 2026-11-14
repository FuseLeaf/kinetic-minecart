package org.fuseleaf.kineticminecart.client;

import org.fuseleaf.kineticminecart.client.initializer.ClientInitializer;
import org.fuseleaf.kineticminecart.util.Constant;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.Mod;

@Mod(value = Constant.Core.MOD_ID, dist = Dist.CLIENT)
public class KineticMinecartClient {

    public KineticMinecartClient() {
        ClientInitializer.init();
    }
}
