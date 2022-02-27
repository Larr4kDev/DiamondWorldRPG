package net.relfect.task.commands;

import net.relfect.task.RPG;
import net.relfect.task.boss.BossType;
import net.relfect.task.spawner.Spawner;
import net.relfect.task.spawner.SpawnerManager;
import net.relfect.task.util.BukkitUtil;
import net.relfect.task.util.JavaUtil;
import net.relfect.task.util.TimeUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnerCommands implements CommandExecutor {

    private final SpawnerManager spawnerManager;

    public SpawnerCommands(RPG plugin) {
        this.spawnerManager = SpawnerManager.get();
        plugin.getCommand("spawner").setExecutor(this);
        plugin.getCommand("spawners").setExecutor(new SpawnersCommand());
    }

    @Override
    public boolean onCommand(CommandSender s, Command cmd, String arg, String[] args) {
        if (s.hasPermission("admin")) {
            if (args.length == 0) {
                s.sendMessage(BukkitUtil.formatHelp("Спавнеры"));
                s.sendMessage("/spawner create <айди> <displayName> <type> <health> <damage> <interval>");
                s.sendMessage("/spawner <айди>");
                return true;
            } else {
                if ("create".equals(args[0].toLowerCase())) {
                    if (args.length == 7) {
                        String id = args[1].toLowerCase();

                        if (spawnerManager.getSpawners().containsKey(id)) {
                            s.sendMessage("§cТакой спавнер уже существует!");
                            return false;
                        }

                        String displayName = BukkitUtil.color(args[2]), typeId = args[3].toLowerCase();

                        BossType type = BossType.getTypeById(typeId);
                        if (type == null) {
                            s.sendMessage("§cТакого типа не существует!");
                            return false;
                        }

                        double health = JavaUtil.tryParseDouble(args[4]);
                        if (health < 0) health = 20;

                        double damage = JavaUtil.tryParseDouble(args[5]);
                        if (damage < 0) damage = 0;

                        long interval = TimeUtil.getTimeByArg(args[6]);
                        if (interval < 0) {
                            s.sendMessage("§cИнтервал не может быть нулевым!");
                            return false;
                        }

                        new Spawner(id, displayName, ((Player) s).getLocation(), type, health, damage, interval).register();

                        s.sendMessage("§fВы §aуспешно §fсоздали спавнер!");
                        s.sendMessage(BukkitUtil.createText("Наведите для §eинформации §fо спавнере", "§7ID: §f" + id + "\n§7Тип: §f" + type.getId() + "\n§7Имя: §f" + displayName + "\n§7Урон: §f" + damage + "\n§7ХП: §f" + health + "\n§7Интервал: §f" + TimeUtil.formatSeconds(interval / 1000), null));
                        s.sendMessage("Используйте §7/spawner " + id + " §fдля изменения.");
                    }
                    return false;
                } else {
                    Spawner spawner = spawnerManager.getSpawners().get(args[0].toLowerCase());

                    if (spawner == null) {
                        s.sendMessage("§cТакой спавнер не существует!");
                        return false;
                    }

                    spawner.getSpawnerMenu().open((Player) s);
                }
            }
        }
        return false;
    }

    private class SpawnersCommand implements CommandExecutor {

        @Override
        public boolean onCommand(CommandSender s, Command cmd, String arg, String[] args) {
            if (s.hasPermission("admin")) {
                spawnerManager.getMenu().open((Player)s);
            }
            return false;
        }

    }

}
