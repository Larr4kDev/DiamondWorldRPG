package net.relfect.task.config;

import net.relfect.task.RPG;
import net.relfect.task.util.ConfigFile;

import java.util.List;

public interface Lang {

    static final ConfigFile CONFIG = RPG.getInstance().getLang();

    static final List<String> BOSS_KILLED_HEADER = CONFIG.getStringList("BOSS_KILLED_HEADER");
    static final List<String> BOSS_KILLED_FOOTER = CONFIG.getStringList("BOSS_KILLED_FOOTER");
    static final String BOSS_KILLED_OTHER = CONFIG.getString("BOSS_KILLED_OTHER");
    static final String BOSS_TOP_FORMAT = CONFIG.getString("BOSS_TOP_FORMAT");

}
