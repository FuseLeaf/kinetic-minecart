package org.fuseleaf.kineticminecart.mixin;

import org.fuseleaf.kineticminecart.kinetic.CartBehavior;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.entity.vehicle.Minecart;

@Mixin(Minecart.class)
public class MinecartMixin {

    @Inject(method = "getMinecartType", at = @At("TAIL"), cancellable = true)
    private void injectGetMinecartType(CallbackInfoReturnable<AbstractMinecart.Type> cir) {
        if (CartBehavior.isRidingDisabled((Minecart)(Object)this)) {
            cir.setReturnValue(AbstractMinecart.Type.COMMAND_BLOCK);    // This type cannot be ridden.
        }
    }
}
