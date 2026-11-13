package org.fuseleaf.kineticminecart.kinetic;

import java.util.List;

import org.fuseleaf.kineticminecart.config.ConfigManager;
import org.fuseleaf.kineticminecart.entity.TargetFilter;
import org.fuseleaf.kineticminecart.extension.config.ConfigData;
import org.fuseleaf.kineticminecart.util.Delay;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.minecart.AbstractMinecart;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class KineticManager {

    public static void tick(AbstractMinecart cart) {
        if (cart == null || !ConfigManager.isEnabled()) {
            return;
        }

        Level world = cart.level();
        double speed = CartSpeed.getSpeed(cart);
        Vec3 mv = cart.getDeltaMovement();

        if (world.isClientSide() || !CartSpeed.hasReachedSpeedThreshold(cart)) {
            return;
        }

        switch (ConfigManager.getApplicationMode()) {
            case null:
            case ConfigData.Enum.ApplicationMode.COLLIDE:
                List<Entity> list1 = world.getEntities(
                    cart,
                    cart.getBoundingBox(),
                    e -> (
                        e != cart
                        && e.isAlive()
                        && !TargetFilter.isExclued(cart, e)
                        && cart.getBoundingBox().intersects(e.getBoundingBox())
                    )
                );

                for (Entity target : list1) {

                    if (ConfigManager.isKnockbackEnabled()) {
                        CartKnockback.knock(cart, target, mv, speed);
                    }
                    if (ConfigManager.isDamageEnabled()) {
                        Delay.schedule(1, () -> CartDamage.damage(target, speed));
                    }
                }
                break;

            case ConfigData.Enum.ApplicationMode.RADIUS:
                List<Entity> list2 = world.getEntities(
                    cart,
                    cart.getBoundingBox().inflate(ConfigManager.getRadius()),
                    e -> (
                        e != cart
                        && e.isAlive()
                        && !TargetFilter.isExclued(cart, e)
                        && e.getVehicle() != cart   // Exclude the passenger of this vehicle.
                    )
                );

                for (Entity target : list2) {

                    if (ConfigManager.isKnockbackEnabled()) {
                        CartKnockback.knock(cart, target, mv, speed);
                    }
                    if (ConfigManager.isDamageEnabled()) {
                        Delay.schedule(1, () -> CartDamage.damage(target, speed));
                    }
                }
                break;

            default:
                break;
        }

        cart.setDeltaMovement(mv);
    }
}
