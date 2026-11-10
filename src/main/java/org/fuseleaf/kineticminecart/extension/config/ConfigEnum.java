package org.fuseleaf.kineticminecart.extension.config;

import org.fuseleaf.kineticminecart.util.Constant;

import org.jspecify.annotations.NonNull;

import net.minecraft.network.chat.Component;

public class ConfigEnum {

    public static enum ApplicationMode {
        Radius(Constant.I18n.OPTION_RADIUS),
        Collide(Constant.I18n.OPTION_COLLIDE);

        private final @NonNull String key;

        ApplicationMode(@NonNull String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return Component.translatable(key).getString();
        }
    }

    public static enum DamageMode {
        TieredDamage(Constant.I18n.OPTION_TIERED_DAMAGE),
        DirectlyKill(Constant.I18n.OPTION_TIERED_DIRECTLY_KILL);

        private final @NonNull String key;

        DamageMode(@NonNull String key) {
            this.key = key;
        }

        @Override
        public String toString() {
            return Component.translatable(key).getString();
        }
    }
}
