package org.fuseleaf.kineticminecart.entity;

import java.util.List;

import org.fuseleaf.kineticminecart.util.Constant;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

public class ExclusionList {

    private ExclusionList() {}

    public static String normalizeId(String id) {
        if (id == null) {
            return id;
        }

        ResourceLocation rl = ResourceLocation.tryParse(id);

        if (rl == null) {
            return id;
        }

        if (rl.getNamespace().isEmpty()) {
            rl = ResourceLocation.fromNamespaceAndPath("minecraft", rl.getPath());
        }

        return rl.toString();   // minecraft:xxx
    }

    public static Component buildMessage(List<String> inputList) {

        if (inputList.isEmpty()) {
            return Component.translatable(Constant.I18n.TOAST_EXCLUSION_LIST_DESC_EMPTY);
        }

        MutableComponent sb = Component.empty();

        boolean detected = false;
        for (String id : inputList) {
            if (id == null) {
                return Component.empty();
            }

            ResourceLocation rl = ResourceLocation.tryParse(id);

            if (rl == null) {
                return Component.empty();
            }

            EntityType<?> type = BuiltInRegistries.ENTITY_TYPE.get(ResourceKey.create(Registries.ENTITY_TYPE, rl));

            if (type != null) {
                if (!detected) {
                    sb.append(Component.translatable(Constant.I18n.TOAST_EXCLUSION_LIST_DESC_DETECTED));
                    detected = true;
                }
            } else {
                sb.append(Component.translatable(Constant.I18n.TOAST_EXCLUSION_LIST_DESC_UNKNOWN, id));
            }
        }

        return sb;
    }
}
