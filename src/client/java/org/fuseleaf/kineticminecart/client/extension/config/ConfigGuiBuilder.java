package org.fuseleaf.kineticminecart.client.extension.config;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.extension.config.ConfigEnum;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.api.Requirement;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.EnumListEntry;
import me.shedaniel.clothconfig2.impl.builders.SubCategoryBuilder;
import net.minecraft.network.chat.Component;

public class ConfigGuiBuilder {

    public static ConfigBuilder get() {
        ConfigData config = ConfigManager.getConfig();  // TODO: Could there be a security issue?

        ConfigBuilder builder = ConfigBuilder.create().setTitle(Component.translatable("kinetic-minecart.config.title"));
        builder.setGlobalized(true);
        builder.setGlobalizedExpanded(false);
        builder.transparentBackground();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable("kinetic-minecart.config.category.general"));

        BooleanListEntry enabledListEntry;

        {
            enabledListEntry = entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.enabled"), config.enabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.enabled = v)
                .build();

            generalCategory.addEntry(enabledListEntry);
        }

        {
            SubCategoryBuilder overrideSubCategory = entryBuilder.startSubCategory(Component.literal("kinetic-minecart.config.category.override"))
                .setExpanded(true)
                .setRequirement(Requirement.isTrue(enabledListEntry));

            overrideSubCategory.add(
                entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.override_collision"), config.overrideCollision)
                    .setDefaultValue(true)
                    .setSaveConsumer(v -> config.overrideCollision = v)
                    .build()
            );
            overrideSubCategory.add(
                entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.override_riding"), config.overrideRiding)
                    .setDefaultValue(true)
                    .setSaveConsumer(v -> config.overrideRiding = v)
                    .build()
            );

            generalCategory.addEntry(overrideSubCategory.build());
        }

        {
            SubCategoryBuilder modeSubCategory = entryBuilder.startSubCategory(Component.literal("kinetic-minecart.config.category.mode"))
                .setExpanded(true)
                .setRequirement(Requirement.isTrue(enabledListEntry));

            EnumListEntry<ConfigEnum.ApplicationMode> modeListEntry = entryBuilder.startEnumSelector(Component.literal("kinetic-minecart.config.mode"), ConfigEnum.ApplicationMode.class, config.selectedApplicationMode)
                .setDefaultValue(ConfigEnum.ApplicationMode.Collide)
                .setSaveConsumer(v -> config.selectedApplicationMode = v)
                .build();

            modeSubCategory.add(modeListEntry);
            modeSubCategory.add(
                entryBuilder.startIntSlider(Component.literal("kinetic-minecart.config.radius"), 5, 3, 10)
                    .setDefaultValue(5)
                    .setSaveConsumer(v -> config.intRadius = v)
                    .setRequirement(() -> modeListEntry.getValue() == ConfigEnum.ApplicationMode.Radius)
                    .build()
            );

            generalCategory.addEntry(modeSubCategory.build());
        }

        {
            SubCategoryBuilder damageSubCategory = entryBuilder.startSubCategory(Component.literal("kinetic-minecart.config.category.damage"))
                .setExpanded(true)
                .setRequirement(Requirement.isTrue(enabledListEntry));

            BooleanListEntry enabledDamageListEntry = entryBuilder.startBooleanToggle(Component.literal("kinetic-minecart.config.damage"), config.enabledDamage)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.enabledDamage = v)
                .build();

            damageSubCategory.add(enabledDamageListEntry);
            damageSubCategory.add(
                entryBuilder.startEnumSelector(Component.literal("kinetic-minecart.config.damage_mode"), ConfigEnum.DamageMode.class, config.selectedDamageMode)
                    .setDefaultValue(ConfigEnum.DamageMode.TieredDamage)
                    .setSaveConsumer(v -> config.selectedDamageMode = v)
                    .setRequirement(Requirement.isTrue(enabledDamageListEntry))
                    .build()
            );

            generalCategory.addEntry(damageSubCategory.build());
        }

        {
            generalCategory.addEntry(
                entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.knock"), config.enabledKnock)
                    .setDefaultValue(true)
                    .setSaveConsumer(v -> config.enabledKnock = v)
                    .setRequirement(Requirement.isTrue(enabledListEntry))
                    .build()
            );
        }

        {
            generalCategory.addEntry(
                entryBuilder.startBooleanToggle(Component.literal("kinetic-minecart.config.old_page"), config.oldPage)
                    .setDefaultValue(false)
                    .setSaveConsumer(v -> config.oldPage = v)
                    .build()
            );
        }

        return builder;
    }
}
