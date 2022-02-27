package net.relfect.task.spawner;

import net.relfect.task.RPG;
import net.relfect.task.util.FlatFile;
import net.relfect.task.util.JavaUtil;

import java.util.ArrayList;

public class SpawnerFile extends FlatFile {

    public SpawnerFile(RPG plugin) {
        super("spawners", plugin);
    }

    @Override
    public void load() {
        SpawnerManager.get().getSpawners().clear();
        if (config.contains("spawners")) {
            JavaUtil.createList(config.get("spawners"), Spawner.class).forEach(Spawner::register);
        }
        RPG.getInstance().getLogger().info("Loaded " + SpawnerManager.get().getSpawners().size() + " spawners.");
    }

    @Override
    public void save() {
        config.set("spawners", new ArrayList<>(SpawnerManager.get().getSpawners().values()));
        super.save();
    }

}
