package org.fuseleaf.kineticminecart.client.config;

import org.fuseleaf.kineticminecart.util.Constant;

public class ClientConfigManager {

    public static boolean isConfigAvailable() {
        try {
            Class.forName(Constant.Extension.AUTO_CONFIG_CLASS_NAME);

            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }
}
