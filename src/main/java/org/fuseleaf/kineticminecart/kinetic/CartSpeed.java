package org.fuseleaf.kineticminecart.kinetic;

import net.minecraft.world.entity.vehicle.AbstractMinecart;

public class CartSpeed {

    private static final double SPEED_THRESHOLD = 2.0;   // The maximum speed is about 8.0.

    public static double getSpeed(AbstractMinecart cart) {
        return cart.getDeltaMovement().length() * 4.0;
    }

    public static boolean hasReachedSpeedThreshold(AbstractMinecart cart) {
        return getSpeed(cart) >= SPEED_THRESHOLD;
    }
}
