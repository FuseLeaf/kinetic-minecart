package org.fuseleaf.kineticminecart.util;

public class Constant {

    private Constant() {}

    public static final class Core {

        public static final String MOD_ID = "kinetic-minecart";
    }

    public static final class Extension {

        public static final String AUTO_CONFIG_CLASS_NAME = "me.shedaniel.autoconfig.AutoConfig";
    }

    // TODO: zh_cn, zh_hk, zh_tw
    public static final class I18n {

        public static final String OPTION_CONFIRM = "kinetic-minecart.option.confirm";

        public static final String OPTION_RADIUS = "kinetic-minecart.option.radius";
        public static final String OPTION_COLLIDE = "kinetic-minecart.option.collide";

        public static final String OPTION_TIERED_DAMAGE = "kinetic-minecart.option.tiered_damage";
        public static final String OPTION_TIERED_DIRECTLY_KILL = "kinetic-minecart.option.directly_kill";

        public static final String TOAST_API_NOT_FOUND_TITLE = "kinetic-minecart.toast.api_not_found.title";
        public static final String TOAST_API_NOT_FOUND_DESC = "kinetic-minecart.toast.api_not_found.desc";

        public static final String TOAST_EXCLUSION_LIST_TITLE = "kinetic-minecart.toast.exclusion_list.title";
        public static final String TOAST_EXCLUSION_LIST_DESC_EMPTY = "kinetic-minecart.toast.exclusion_list.desc.empty";
        public static final String TOAST_EXCLUSION_LIST_DESC_DETECTED = "kinetic-minecart.toast.exclusion_list.desc.detected";
        public static final String TOAST_EXCLUSION_LIST_DESC_UNKNOWN = "kinetic-minecart.toast.exclusion_list.desc.unknown";

        public static final String SCREEN_ILLEGAL_OPERATION_TITLE = "kinetic-minecart.screen.illegal_operation.title";
        public static final String SCREEN_ILLEGAL_OPERATION_DESC = "kinetic-minecart.screen.illegal_operation.desc";

        public static final String CONFIG_TITLE = "kinetic-minecart.config.title";
        public static final String CONFIG_CATEGORY_GENERAL = "kinetic-minecart.config.category.general";
        public static final String CONFIG_CATEGORY_ADVANCED = "kinetic-minecart.config.category.advanced";
        public static final String CONFIG_CATEGORY_OVERRIDE = "kinetic-minecart.config.category.override";
        public static final String CONFIG_CATEGORY_MODE = "kinetic-minecart.config.category.mode";
        public static final String CONFIG_CATEGORY_DAMAGE = "kinetic-minecart.config.category.damage";
        public static final String CONFIG_CATEGORY_KNOCK = "kinetic-minecart.config.category.knock";
        public static final String CONFIG_CATEGORY_TYPE = "kinetic-minecart.config.category.type";

        public static final String CONFIG_ENABLE = "kinetic-minecart.config.enable";

        public static final String CONFIG_OVERRIDE_COLLISION = "kinetic-minecart.config.override_collision";
        public static final String CONFIG_OVERRIDE_RIDING = "kinetic-minecart.config.override_riding";

        public static final String CONFIG_MODE = "kinetic-minecart.config.mode";
        public static final String CONFIG_RADIUS = "kinetic-minecart.config.radius";
        public static final String CONFIG_RADIUS_TOOLTIP = "kinetic-minecart.config.radius.tooltip";

        public static final String CONFIG_DAMAGE = "kinetic-minecart.config.damage";
        public static final String CONFIG_DAMAGE_MODE = "kinetic-minecart.config.damage_mode";

        public static final String CONFIG_KNOCK = "kinetic-minecart.config.knock";

        public static final String CONFIG_EXCLUDE_PLAYER = "kinetic-minecart.config.exclude_player";
        public static final String CONFIG_EXCLUDE_PET = "kinetic-minecart.config.exclude_pet";
        public static final String CONFIG_EXCLUDE_PET_TOOLTIP = "kinetic-minecart.config.exclude_pet.tooltip";
        public static final String CONFIG_EXCLUDE_PASSENGER = "kinetic-minecart.config.exclude_passenger";
        public static final String CONFIG_EXCLUDE_NAMED_ENTITY = "kinetic-minecart.config.exclude_named_entity";
        public static final String CONFIG_EXCLUDE_ITEM_ENTITY = "kinetic-minecart.config.exclude_item_entity";

        public static final String CONFIG_ENABLE_EXCLUSION_LIST = "kinetic-minecart.config.enable_exclusion_list";
        public static final String CONFIG_EXCLUSION_LIST = "kinetic-minecart.config.exclusion_list";
        public static final String CONFIG_EXCLUSION_LIST_TOOLTIP = "kinetic-minecart.config.exclusion_list.tooltip";
    }
}
