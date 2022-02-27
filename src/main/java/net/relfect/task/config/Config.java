package net.relfect.task.config;

import net.relfect.task.RPG;
import net.relfect.task.util.ConfigFile;

public interface Config {

    static final ConfigFile CONFIG = RPG.getInstance().getConfig();

}

