package org.fuseleaf.kineticminecart.extension.config;

import java.util.ArrayList;
import java.util.List;

import org.fuseleaf.kineticminecart.util.Constant;

import org.jspecify.annotations.NonNull;

import me.shedaniel.autoconfig.annotation.Config;

import net.minecraft.network.chat.Component;

@Config(name = Constant.Core.MOD_ID)
public class ConfigData implements me.shedaniel.autoconfig.ConfigData {

    public boolean enabled = true;

    public boolean overrideCollision = true;
    public boolean overrideRiding = true;

    public Enum.@NonNull ApplicationMode selectedApplicationMode = Enum.ApplicationMode.Collide;
    public int intRadius = 5;

    public boolean enabledDamage = true;
    public Enum.@NonNull DamageMode selectedDamageMode = Enum.DamageMode.TieredDamage;

    public boolean enabledKnock = true;

    public boolean excludePlayer = false;
    public boolean excludePet = false;
    public boolean excludePassenger = true;
    public boolean excludeNamedEntity = false;
    public boolean excludeItemEntity = false;

    public boolean enabledExclusionList = false;
    public List<String> exclusionList = new ArrayList<>();

    public class Enum {

        public enum ApplicationMode {
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

        public enum DamageMode {
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
}
