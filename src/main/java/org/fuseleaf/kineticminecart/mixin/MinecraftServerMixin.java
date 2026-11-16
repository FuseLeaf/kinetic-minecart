package org.fuseleaf.kineticminecart.mixin;

import java.util.function.BooleanSupplier;

import org.fuseleaf.kineticminecart.initializer.Initializer;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.server.MinecraftServer;

@Mixin(MinecraftServer.class)
public class MinecraftServerMixin {

    @Inject(method = "tickServer", at = @At("TAIL"))
    private void injectTickServer(BooleanSupplier haveTime, CallbackInfo ci) {
        Initializer.endServerTick();
    }
}
