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

        if (speed < 3) {
            return;
        }

        // 普通伤害算法 D(s) = a·(s - s₀) + H·[tanh((s - m)/w) - tanh((s₀ - m)/w)]，其中 s 为速度。。
        // 在本模组参数下，该函数的图像为无上界 S 形曲线。
        // s₀ = 3：伤害起算速度，D(s₀) = 0。
        // a = 0.6： D(s) 的渐近斜率。a 增大则全速度域伤害上移，决定 S 形段之后伤害的增长率；a > 0 保证 D(s) 无上界。
        // H = 63：决定 S 形段的总增幅。H 增大则拐点附近斜率增大，中高速伤害整体上移；H → 0 时 D(s) 退化为线性函数。H 不影响渐近斜率。
        // m = 42： D(s) 的拐点。m 增大则陡增区间向高速平移，低中速伤害降低；m 减小则相反。
        // w = 20：决定S形区间的跨度。w 增大则过渡平缓，峰值斜率降低； w 减小则伤害增长集中于 m 附近的较窄区间。
        double normalDamage = 0.6 * (speed - 3) + 63 * (Math.tanh((speed - 42) / 20.0) - Math.tanh((3 - 42) / 20.0));

        if (target.isPassenger()) {
            // 乘客伤害算法 Dp(s) = D(s)·(1 - E(s))。其中防护系数 E(s) = e₀ / (1 + exp((s - m_f)/w_f))。
            // e₀ = 0.65：防护系数上限，低速段 Dp(s) ≈ D(s)·(1 - e₀)。e₀ 增大则低中速乘客伤害降低；须满足 0 ≤ e₀ ≤ 1，否则 Dp(s) < 0。
            // m_f = 85：防护失效中心，E(m_f) = e₀/2。m_f 增大则防护有效区间向高速延伸，Dp(s) 收敛至 D(s) 的速度相应推后。
            // w_f = 15：防护失效宽度，决定 E(s) 的衰减速率。w_f 增大则衰减更平缓，Dp(s) 收敛至 D(s) 的速度推后。反之则 E(s) 在 m_f 附近骤降，Dp(s) 陡增。
            double shield = 0.65 / (1 + Math.exp((speed - 85) / 15.0));
            damage = (float)(normalDamage * (1 - shield));
        } else {
            damage = (float)normalDamage;
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
