package net.asbyth.tweaker.util;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import java.util.ArrayList;
import java.util.List;

public class TickScheduler {

    public static final TickScheduler INSTANCE = new TickScheduler();
    private List<Task> items = new ArrayList<>();

    private TickScheduler() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    public void schedule(int ticksToWait, Runnable runnable) {
        items.add(new Task(ticksToWait, runnable));
    }

    @SubscribeEvent
    public void tickClient(TickEvent.ClientTickEvent event) {
        items.removeIf(Task::execute);
    }

    private static class Task {
        private int ticksLeft;
        private Runnable task;

        public Task(int ticksLeft, Runnable task) {
            this.ticksLeft = ticksLeft;
            this.task = task;
        }

        boolean execute() {
            if (ticksLeft <= 0) {
                task.run();
                return true;
            }

            ticksLeft--;
            return false;
        }
    }
}
