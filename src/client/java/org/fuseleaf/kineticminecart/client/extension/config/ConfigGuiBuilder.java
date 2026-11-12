package org.fuseleaf.kineticminecart.client.extension.config;

import java.util.ArrayList;

import org.fuseleaf.kineticminecart.config.ConfigManager;
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
        ConfigData config = ConfigManager.getConfig();  // TODO: Could there be a security issue?

        ConfigBuilder builder = ConfigBuilder.create().setTitle(Component.translatable(Constant.I18n.CONFIG_TITLE));
        builder.setGlobalized(true);
        builder.setGlobalizedExpanded(false);
        builder.transparentBackground();

        ConfigEntryBuilder entryBuilder = builder.entryBuilder();

        BooleanListEntry enabledListEntry;

        {
            ConfigCategory generalCategory = builder.getOrCreateCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_GENERAL));

            enabledListEntry = entryBuilder
                .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_ENABLE), config.enabled)
                .setDefaultValue(true)
                .setSaveConsumer(v -> config.enabled = v)
                .build();

            generalCategory.addEntry(enabledListEntry);

            {
                SubCategoryBuilder overrideSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_OVERRIDE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

                overrideSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_OVERRIDE_COLLISION), config.overrideCollision)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.overrideCollision = v)
                        .build()
                );
                overrideSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_OVERRIDE_RIDING), config.overrideRiding)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.overrideRiding = v)
                        .build()
                );

                generalCategory.addEntry(overrideSubCategory.build());
            }

            {
                SubCategoryBuilder modeSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_MODE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

                EnumListEntry<ConfigData.Enum.@NonNull ApplicationMode> modeListEntry = entryBuilder
                    .startEnumSelector(Component.translatable(Constant.I18n.CONFIG_MODE), ConfigData.Enum.ApplicationMode.class, config.selectedApplicationMode)
                    .setDefaultValue(ConfigData.Enum.ApplicationMode.Collide)
                    .setSaveConsumer(v -> config.selectedApplicationMode = v)
                    .build();

                modeSubCategory.add(modeListEntry);
                modeSubCategory.add(
                    entryBuilder
                        .startIntSlider(Component.translatable(Constant.I18n.CONFIG_RADIUS), 5, 3, 10)
                        .setDefaultValue(5)
                        .setTooltip(new Component[] {
                            Component.translatable(Constant.I18n.CONFIG_RADIUS_TOOLTIP)
                        })
                        .setSaveConsumer(v -> config.intRadius = v)
                        .setRequirement(() -> modeListEntry.getValue() == ConfigData.Enum.ApplicationMode.Radius)
                        .build()
                );

                generalCategory.addEntry(modeSubCategory.build());
            }

            {
                SubCategoryBuilder damageSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_DAMAGE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

                BooleanListEntry enabledDamageListEntry = entryBuilder
                    .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_DAMAGE), config.enabledDamage)
                    .setDefaultValue(true)
                    .setSaveConsumer(v -> config.enabledDamage = v)
                    .build();

                damageSubCategory.add(enabledDamageListEntry);
                damageSubCategory.add(
                    entryBuilder
                        .startEnumSelector(Component.translatable(Constant.I18n.CONFIG_DAMAGE_MODE), ConfigData.Enum.DamageMode.class, config.selectedDamageMode)
                        .setDefaultValue(ConfigData.Enum.DamageMode.TieredDamage)
                        .setSaveConsumer(v -> config.selectedDamageMode = v)
                        .setRequirement(Requirement.isTrue(enabledDamageListEntry))
                        .build()
                );

                generalCategory.addEntry(damageSubCategory.build());
            }

            {
                SubCategoryBuilder knockSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_KNOCK))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

                knockSubCategory.add(
                    entryBuilder
                        .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_KNOCK), config.enabledKnock)
                        .setDefaultValue(true)
                        .setSaveConsumer(v -> config.enabledKnock = v)
                        .setRequirement(Requirement.isTrue(enabledListEntry))
                        .build()
                );

                generalCategory.addEntry(knockSubCategory.build());
            }

            {
                SubCategoryBuilder typeSubCategory = entryBuilder
                    .startSubCategory(Component.translatable(Constant.I18n.CONFIG_CATEGORY_TYPE))
                    .setExpanded(true)
                    .setRequirement(Requirement.isTrue(enabledListEntry));

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

            BooleanListEntry enabledExclusionListListEntry = entryBuilder
                .startBooleanToggle(Component.translatable(Constant.I18n.CONFIG_ENABLE_EXCLUSION_LIST), config.enabledExclusionList)
                .setDefaultValue(false)
                .setSaveConsumer(v -> config.enabledExclusionList = v)
                .build();

            // TODO: They need to be put into SubCategory after solving https://github.com/shedaniel/cloth-config/issues/365
            advancedCategory.addEntry(enabledExclusionListListEntry);
            advancedCategory.addEntry(
                entryBuilder
                    .startStrList(Component.translatable(Constant.I18n.CONFIG_EXCLUSION_LIST), config.exclusionList)
                    .setDefaultValue(new ArrayList<>())
                    .setTooltip(new Component[] {
                        Component.translatable(Constant.I18n.CONFIG_EXCLUSION_LIST_TOOLTIP)
                    })
                    .setSaveConsumer(v -> config.exclusionList = v)
                    .setRequirement(Requirement.isTrue(enabledExclusionListListEntry))
                    .build()
            );
        }

        return builder;
    }
}
