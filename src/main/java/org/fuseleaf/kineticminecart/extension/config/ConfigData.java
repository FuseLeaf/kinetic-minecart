package org.fuseleaf.kineticminecart.extension.config;

import java.util.ArrayList;
import java.util.List;

import org.fuseleaf.kineticminecart.util.Constant;

import org.jspecify.annotations.NonNull;

import me.shedaniel.autoconfig.annotation.Config;

import net.minecraft.network.chat.Component;

@Config(name = Constant.Core.MOD_ID)
public class ConfigData implements me.shedaniel.autoconfig.ConfigData {

    public boolean enable = true;

    public Enum.ApplicationMode applicationMode = Enum.ApplicationMode.COLLIDE;
    public int radius = 5;

    public boolean enableDamage = true;
    public Enum.DamageMode damageMode = Enum.DamageMode.TIERED;

    public boolean enableKnockback = true;

    public boolean excludePlayer = false;
    public boolean excludePet = false;
    public boolean excludePassenger = true;
    public boolean excludeNamedEntity = false;
    public boolean excludeItemEntity = false;

    public boolean overrideRiding = true;

    public boolean enableExclusionList = false;
    public List<String> exclusionList = new ArrayList<>();

    public class Enum {

        public enum ApplicationMode {
            RADIUS(Constant.I18n.OPTION_RADIUS),
            COLLIDE(Constant.I18n.OPTION_COLLIDE);

            private final @NonNull String key;

            ApplicationMode(@NonNull String key) {
                this.key = key;
            }

            @Override
            public String toString() {
                return Component.translatable(key).getString();
            }
        }

        public enum DamageMode {
            TIERED(Constant.I18n.OPTION_TIERED),
            KILL(Constant.I18n.OPTION_KILL);

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

    @Override
    public void validatePostLoad() {
        if (applicationMode == null) {
            applicationMode = Enum.ApplicationMode.COLLIDE;
        }

        if (radius < 3 || radius > 10) {
            radius = 5;
        }

        if (damageMode == null) {
            damageMode = Enum.DamageMode.TIERED;
        }

        if (exclusionList == null) {
            exclusionList = new ArrayList<>();
        }
    }
}
