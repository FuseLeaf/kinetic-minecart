package org.fuseleaf.kineticminecart.client;

import org.fuseleaf.kineticminecart.client.extension.config.ConfigEntry;
import org.fuseleaf.kineticminecart.client.initializer.ClientInitializer;
import org.fuseleaf.kineticminecart.util.Constant;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ConfigScreenHandler.ConfigScreenFactory;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@Mod.EventBusSubscriber(modid = Constant.Core.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KineticMinecartClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ClientInitializer.init();

        ModLoadingContext.get().registerExtensionPoint(
            ConfigScreenFactory.class,
            () -> new ConfigScreenFactory((mod, parent) -> ConfigEntry.getModConfigScreenFactory(parent))
        );
    }
}
