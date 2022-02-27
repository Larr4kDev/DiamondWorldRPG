package net.relfect.task.spawner;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import net.relfect.task.RPG;
import net.relfect.task.boss.Boss;
import net.relfect.task.boss.BossType;
import net.relfect.task.listener.BossListener;
import net.relfect.task.spawner.menu.SpawnersMenu;
import net.relfect.task.util.TaskUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SpawnerManager {
    Map<String, Spawner> spawners = Maps.newHashMap();
    List<Boss> aliveBosses = Lists.newArrayList();
    SpawnerFile data;
    SpawnersMenu menu;

    public SpawnerManager(RPG plugin) {
        instance = this;
        Bukkit.getPluginManager().registerEvents(new BossListener(), plugin);
        Bukkit.getScheduler().runTaskTimer(plugin, () -> spawners.values().forEach(Spawner::update),0, 20);
        data = new SpawnerFile(plugin);
        menu = new SpawnersMenu();
        TaskUtil.async(data::load);
    }

    public Boss getBossByEntity(Entity entity) {
        return aliveBosses.stream().filter(boss -> boss.getEntity().getEntityId() == entity.getEntityId()).findFirst().orElse(null);
    }

    public Boss spawnBoss(Spawner spawner) {
        spawner.clearHologram();
        Boss boss = null;
        try {
            boss = spawner.getType().getClazz().getConstructor(Spawner.class, BossType.class).newInstance(spawner, spawner.getType());
            boss.spawn(spawner.getSpawnLocation());
        } catch(Exception e) {
            e.printStackTrace();
        }
        return boss;
    }

    static SpawnerManager instance;

    public static SpawnerManager get() {
        return instance;
    }

}

