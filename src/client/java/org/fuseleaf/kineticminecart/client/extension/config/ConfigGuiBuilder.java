package org.fuseleaf.kineticminecart.client.extension.config;

import java.util.ArrayList;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;

import org.jspecify.annotations.NonNull;

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

        BooleanListEntry enabledListEntry;

        {
            ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable("kinetic-minecart.config.category.general"));

            enabledListEntry = entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.enabled"), config.enabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.enabled = v)
                .build();

            generalCategory.addEntry(enabledListEntry);

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

                EnumListEntry<ConfigData.Enum.@NonNull ApplicationMode> modeListEntry = entryBuilder.startEnumSelector(Component.literal("kinetic-minecart.config.mode"), ConfigData.Enum.ApplicationMode.class, config.selectedApplicationMode)
                    .setDefaultValue(ConfigData.Enum.ApplicationMode.Collide)
                    .setSaveConsumer(v -> config.selectedApplicationMode = v)
                    .build();

                modeSubCategory.add(modeListEntry);
                modeSubCategory.add(
                    entryBuilder.startIntSlider(Component.literal("kinetic-minecart.config.radius"), 5, 3, 10)
                        .setDefaultValue(5)
                        .setSaveConsumer(v -> config.intRadius = v)
                        .setRequirement(() -> modeListEntry.getValue() == ConfigData.Enum.ApplicationMode.Radius)
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
                    entryBuilder.startEnumSelector(Component.literal("kinetic-minecart.config.damage_mode"), ConfigData.Enum.DamageMode.class, config.selectedDamageMode)
                        .setDefaultValue(ConfigData.Enum.DamageMode.TieredDamage)
                        .setSaveConsumer(v -> config.selectedDamageMode = v)
                        .setRequirement(Requirement.isTrue(enabledDamageListEntry))
                        .build()
                );

                generalCategory.addEntry(damageSubCategory.build());
            }

            {
                SubCategoryBuilder knockSubCategory = entryBuilder.startSubCategory(Component.literal("kinetic-minecart.config.category.knock"))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

                knockSubCategory.add(
                    entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.knock"), config.enabledKnock)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.enabledKnock = v)
                        .setRequirement(Requirement.isTrue(enabledListEntry))
                        .build()
                );
            }

            {
                SubCategoryBuilder typeSubCategory = entryBuilder.startSubCategory(Component.translatable("kinetic-minecart.config.category.type"))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

                typeSubCategory.add(
                    entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.exclude_player"), config.excludePlayer)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludePlayer = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.exclude_pet"), config.excludePet)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludePet = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.exclude_passenger"), config.excludePassenger)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.excludePassenger = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.exclude_named_entity"), config.excludeNamedEntity)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludeNamedEntity = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.exclude_item_entity"), config.excludeItemEntity)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludeItemEntity = v)
                        .build()
                );

                generalCategory.addEntry(typeSubCategory.build());
            }
        }

        {
            ConfigCategory advancedCategory = builder.getOrCreateCategory(Component.translatable("kinetic-minecart.config.category.advanced"));

            BooleanListEntry enabledExclusionListListEntry = entryBuilder.startBooleanToggle(Component.translatable("kinetic-minecart.config.enabled_exclusion_list"), config.enabledExclusionList)
                .setDefaultValue(false)
                .setSaveConsumer(v -> config.enabledExclusionList = v)
                .build();

            // TODO: They need to be put into SubCategory after solving https://github.com/shedaniel/cloth-config/issues/365
            advancedCategory.addEntry(enabledExclusionListListEntry);
            advancedCategory.addEntry(
                entryBuilder.startStrList(Component.translatable("kinetic-minecart.config.exclusion_list"), config.exclusionList)
                    .setDefaultValue(new ArrayList<>())
                    .setSaveConsumer(v -> config.exclusionList = v)
                    .setRequirement(Requirement.isTrue(enabledExclusionListListEntry))
                    .build()
            );
        }

        return builder;
    }
}
