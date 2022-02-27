package net.relfect.task.boss.event;

import lombok.Getter;
import net.relfect.task.boss.Boss;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntitySpawnEvent;

@Getter
public class BossSpawnEvent extends EntitySpawnEvent {

    private static final HandlerList handlers = new HandlerList();
    private final Boss boss;

    public BossSpawnEvent(Boss boss) {
        super(boss.getEntity());
        this.boss = boss;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
