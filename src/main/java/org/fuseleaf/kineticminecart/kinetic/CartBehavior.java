package org.fuseleaf.kineticminecart.kinetic;

import org.fuseleaf.kineticminecart.config.ConfigManager;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.entity.vehicle.minecart.Minecart;

public class CartBehavior {

    public static boolean isCollisionDisabledWith(AbstractMinecart cart, Entity target) {
        return (
            ConfigManager.isEnabled()
            && ConfigManager.isOverrideCollision()
            && CartSpeed.hasReachedSpeedThreshold(cart)
            && !(target instanceof AbstractMinecart)
        );
    }

    public static boolean isRidingDisabled(Minecart cart) {
        return (
            ConfigManager.isEnabled()
            && ConfigManager.isOverrideRiding()
            && CartSpeed.hasReachedSpeedThreshold(cart)
        );
    }
}
