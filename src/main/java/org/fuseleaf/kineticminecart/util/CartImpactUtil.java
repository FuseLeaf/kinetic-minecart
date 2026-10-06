package org.fuseleaf.kineticminecart.util;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;

public class CartImpactUtil {

    private CartImpactUtil() {}

    private static final Map<Integer, Long> LAST_HIT_TICKS = new ConcurrentHashMap<>();   // lastHitTicks 存储 entityId -> lastHitGameTime（server tick time）

    public static void tryApplyTieredDamage(Entity target, float speed) {
        Level world = target.level();

        // 冷却检查
        long now = world.getGameTime();
        int tid = target.getId();
        Long last = LAST_HIT_TICKS.get(tid);
        if (last != null && (now - last) < 10L) {
            return; // 每实体冷却时间（tick），避免每 tick 重复伤害
        }
        LAST_HIT_TICKS.put(tid, now);

        float damage;

		
		
        if (target.isPassenger()) {
            if (speed > 80) {
				damage = (float)(Math.pow(5, 0.5) + 2 * Math.pow(8, 0.7) + 2 * Math.pow(16, 0.9) + Math.pow(24, 1.1) + Math.pow(speed - 80, 0.5));
			} else if (speed <= 80 && speed > 72) {
				damage = (float)(Math.pow(5, 0.5) + Math.pow(8, 0.7) + 2 * Math.pow(16, 0.9) + Math.pow(24, 1.1) + Math.pow(speed - 72, 0.7));
			} else if (speed <= 72 && speed > 56) {
				damage = (float)(Math.pow(5, 0.5) + Math.pow(8, 0.7) + Math.pow(16, 0.9) + Math.pow(24, 1.1) + Math.pow(speed - 56, 0.9));
			} else if (speed <= 56 && speed > 32) {
				damage = (float)(Math.pow(5, 0.5) + Math.pow(8, 0.7) + Math.pow(16, 0.9) + Math.pow(speed - 32, 1.1));
			} else if (speed <= 32 && speed > 16) {
				damage = (float)(Math.pow(5, 0.5) + Math.pow(8, 0.7) + Math.pow(speed - 16, 0.9));
			} else if (speed <= 16 && speed > 8) {
				damage = (float)(Math.pow(5, 0.5) + Math.pow(speed - 8, 0.7));
			} else if (speed <= 8 && speed >= 3) {
				damage = (float)Math.pow(speed - 3, 0.5);
			} else {
				return;
			}   // 目标为乘客时计算伤害（模拟载具抵挡了部分伤害）
        }

        // 正常情况计算伤害
        if (speed > 72) {
			damage = (float)(Math.pow(5, 0.9) + 2 * Math.pow(8, 1.1) + 2 * Math.pow(16, 1.3) + Math.pow(16, 1.5) + Math.pow(speed - 72, 0.9));
		} else if (speed <= 72 && speed > 64) {
			damage = (float)(Math.pow(5, 0.9) + Math.pow(8, 1.1) + 2 * Math.pow(16, 1.3) + Math.pow(16, 1.5) + Math.pow(speed - 64, 1.1));
		} else if (speed <= 64 && speed > 48) {
			damage = (float)(Math.pow(5, 0.9) + Math.pow(8, 1.1) + Math.pow(16, 1.3) + Math.pow(16, 1.5) + Math.pow(speed - 48, 1.3));
		} else if (speed <= 48 && speed > 32) {
            damage = (float)(Math.pow(5, 0.9) + Math.pow(8, 1.1) + Math.pow(16, 1.3) + Math.pow(speed - 32, 1.5));
        } else if (speed <= 32 && speed > 16) {
			damage = (float)(Math.pow(5, 0.9) + Math.pow(8, 1.1) + Math.pow(speed - 16, 1.3));
        } else if (speed <= 16 && speed > 8) {
			damage = (float)(Math.pow(5, 0.9) + Math.pow(speed - 8, 1.1));
        } else if (speed <= 8 && speed >= 3) {
            damage = (float)Math.pow(speed - 3, 0.9);
        } else {
            return;
        }

        target.hurtServer((ServerLevel)world, world.damageSources().flyIntoWall(), damage);  // 处刑
    }

    public static void tryKill(Entity target, float speed) {
        if (speed <= 2) {
            return;
        }
        ServerLevel level = (ServerLevel) target.level();
        if (target instanceof LivingEntity livingEntity) {
            livingEntity.hurtServer(level, level.damageSources().flyIntoWall(), Float.MAX_VALUE);
        } else {
            target.kill(level);
        }
    }
}
