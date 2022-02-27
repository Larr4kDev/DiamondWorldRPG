package net.relfect.task.util;

import net.relfect.task.RPG;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitTask;

public class TaskUtil {

    private static final Plugin PLUGIN = RPG.getInstance();
    private static final BukkitScheduler SCHEDULER = PLUGIN.getServer().getScheduler();

    public static BukkitTask async(Runnable run) {
        if(!PLUGIN.isEnabled()) {
            run.run();
            return null;
        }
        return SCHEDULER.runTaskAsynchronously(PLUGIN, run);
    }

    public static BukkitTask sync(Runnable run) {
        if(!PLUGIN.isEnabled()) {
            run.run();
            return null;
        }
        return SCHEDULER.runTask(PLUGIN, run);
    }

    public static BukkitTask later(Runnable run, int delay) {
        return SCHEDULER.runTaskLater(PLUGIN, run, delay);
    }

    public static BukkitTask timer(Runnable run, int delay, int repeat) {
        return SCHEDULER.runTaskTimer(PLUGIN, run, delay, repeat);
    }

    public static BukkitTask timerAsync(Runnable run, int delay, int repeat) {
        return SCHEDULER.runTaskTimer(PLUGIN, run, delay, repeat);
    }
}

