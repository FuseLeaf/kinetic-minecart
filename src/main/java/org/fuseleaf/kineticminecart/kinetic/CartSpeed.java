package org.fuseleaf.kineticminecart.kinetic;

import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;

public class CartSpeed {

    private static final double DELTA_SPEED_THRESHOLD = 2.0;   // The maximum delta speed is about 8.

    public static double getDeltaSpeedPerSecond(AbstractMinecart cart) {
        return cart.getDeltaMovement().length() * 4.0;
    }

    public static boolean isSpeedThresholdReached(AbstractMinecart cart) {
        return getDeltaSpeedPerSecond(cart) >= DELTA_SPEED_THRESHOLD;
    }
}
