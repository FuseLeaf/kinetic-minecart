package org.fuseleaf.kineticminecart.util;

import java.util.Objects;
import java.util.PriorityQueue;

public final class Delay {

    private Delay() {}

    private static long currentTick;

    private record ScheduledTask(long executeAt, Runnable task) {}

    private static final PriorityQueue<ScheduledTask> TASKS = new PriorityQueue<>(
        (first, second) -> Long.compare(first.executeAt(), second.executeAt())
    );

    public static void tick() {
        currentTick++;

        while (!TASKS.isEmpty() && TASKS.peek().executeAt() <= currentTick) {

            ScheduledTask scheduledTask = TASKS.poll();

            if (scheduledTask != null) {
                try {
                    scheduledTask.task().run();
                } catch (Exception e) {
                    Log.LOGGER.error("Failed to execute scheduled task.", e);
                }
            }
        }
    }

    public static void schedule(int ticks, Runnable task) {
        Objects.requireNonNull(task, "task");

        if (ticks <= 0) {
            throw new IllegalArgumentException("ticks must be greater than zero");
        }

        TASKS.offer(new ScheduledTask(currentTick + ticks, task));
    }
}
