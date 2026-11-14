package org.fuseleaf.kineticminecart.client.extension.config;

import java.util.ArrayList;

import org.fuseleaf.kineticminecart.client.config.ClientConfigManager;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.util.Constant;

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
        ConfigData config = ClientConfigManager.getConfig();

        ConfigBuilder builder = ConfigBuilder.create()
            .setTitle(Component.translatable(Constant.I18n.CONFIG_TITLE))
            .setSavingRunnable(() -> ClientConfigManager.getConfigHolder().save());
        builder.setGlobalized(true);
        builder.setGlobalizedExpanded(false);
        builder.transparentBackground();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        BooleanListEntry enableToggle;

        {
            ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_GENERAL));

            enableToggle = entryBuilder
                .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_ENABLE), config.enable)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.enable = v)
                .build();

            generalCategory.addEntry(enableToggle);

            {
                SubCategoryBuilder applicationModeSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_APPLICATION_MODE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enableToggle));

                EnumListEntry<ConfigData.Enum.@NonNull ApplicationMode> applicationModeSelector = entryBuilder
                    .startEnumSelector(Component.translatable(Constant.I18n.CONFIG_APPLICATION_MODE), ConfigData.Enum.ApplicationMode.class, ClientConfigManager.getApplicationMode())
                    .setDefaultValue(ConfigData.Enum.ApplicationMode.COLLIDE)
                    .setSaveConsumer(v -> config.applicationMode = v)
                    .build();

                applicationModeSubCategory.add(applicationModeSelector);
                applicationModeSubCategory.add(
                    entryBuilder
                        .startIntSlider(Component.translatable(Constant.I18n.CONFIG_RADIUS), config.radius, 3, 10)
                        .setDefaultValue(5)
                        .setTooltip(new Component[] {
                            Component.translatable(Constant.I18n.CONFIG_RADIUS_TOOLTIP)
                        })
                        .setSaveConsumer(v -> config.radius = v)
                        .setRequirement(() -> applicationModeSelector.getValue() == ConfigData.Enum.ApplicationMode.RADIUS)
                        .build()
                );

                generalCategory.addEntry(applicationModeSubCategory.build());
            }

            {
                SubCategoryBuilder damageSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_DAMAGE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enableToggle));

                BooleanListEntry enableDamageToggle = entryBuilder
                    .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_DAMAGE), config.enableDamage)
                    .setDefaultValue(true)
                    .setSaveConsumer(v -> config.enableDamage = v)
                    .build();

                damageSubCategory.add(enableDamageToggle);
                damageSubCategory.add(
                    entryBuilder
                        .startEnumSelector(Component.translatable(Constant.I18n.CONFIG_DAMAGE_MODE), ConfigData.Enum.DamageMode.class, ClientConfigManager.getDamageMode())
                        .setDefaultValue(ConfigData.Enum.DamageMode.TIERED)
                        .setSaveConsumer(v -> config.damageMode = v)
                        .setRequirement(Requirement.isTrue(enableDamageToggle))
                        .build()
                );

                generalCategory.addEntry(damageSubCategory.build());
            }

            {
                SubCategoryBuilder knockbackSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_KNOCKBACK))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enableToggle));

                knockbackSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_KNOCKBACK), config.enableKnockback)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.enableKnockback = v)
                        .build()
                );

                generalCategory.addEntry(knockbackSubCategory.build());
            }

            {
                SubCategoryBuilder typeSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_TYPE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enableToggle));

                typeSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_EXCLUDE_PLAYER), config.excludePlayer)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludePlayer = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_EXCLUDE_PET), config.excludePet)
                        .setDefaultValue(false)
                        .setTooltip(new Component[] {
                            Component.translatable(Constant.I18n.CONFIG_EXCLUDE_PET_TOOLTIP)
                        })
                        .setSaveConsumer(v -> config.excludePet = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_EXCLUDE_PASSENGER), config.excludePassenger)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.excludePassenger = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_EXCLUDE_NAMED_ENTITY), config.excludeNamedEntity)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludeNamedEntity = v)
                        .build()
                );
                typeSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_EXCLUDE_ITEM_ENTITY), config.excludeItemEntity)
                        .setDefaultValue(false)
                        .setSaveConsumer(v -> config.excludeItemEntity = v)
                        .build()
                );

                generalCategory.addEntry(typeSubCategory.build());
            }
        }

        {
            ConfigCategory advancedCategory = builder.getOrCreateCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_ADVANCED));

            {
                SubCategoryBuilder overrideSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_OVERRIDE))
                    .setRequirement(Requirement.isTrue(enableToggle));

                overrideSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_OVERRIDE_RIDING), config.overrideRiding)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.overrideRiding = v)
                        .build()
                );

                advancedCategory.addEntry(overrideSubCategory.build());
            }

            BooleanListEntry enableExclusionListToggle = entryBuilder
                .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_ENABLE_EXCLUSION_LIST), config.enableExclusionList)
                .setDefaultValue(false)
                .setSaveConsumer(v -> config.enableExclusionList = v)
                .setRequirement(Requirement.isTrue(enableToggle))
                .build();

            // TODO: They need to be put into SubCategory after solving https://github.com/shedaniel/cloth-config/issues/365
            advancedCategory.addEntry(enableExclusionListToggle);
            advancedCategory.addEntry(
                entryBuilder
                    .startStrList(Component.translatable(Constant.I18n.CONFIG_EXCLUSION_LIST), ClientConfigManager.getExclusionList())
                    .setDefaultValue(new ArrayList<>())
                    .setTooltip(new Component[] {
                        Component.translatable(Constant.I18n.CONFIG_EXCLUSION_LIST_TOOLTIP)
                    })
                    .setSaveConsumer(v -> config.exclusionList = v)
                    .setRequirement(Requirement.all(
                        Requirement.isTrue(enableToggle),
                        Requirement.isTrue(enableExclusionListToggle))
                    )
                    .build()
            );
        }

        return builder;
    }
}
