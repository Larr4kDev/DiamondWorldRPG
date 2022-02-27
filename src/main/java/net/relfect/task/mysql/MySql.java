package net.relfect.task.mysql;

import lombok.Getter;
import net.relfect.task.RPG;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;

@Getter
public class MySql {

    private static MySql instance;
    private Connection connection;

    public MySql() {
        instance = this;
    }

    public static MySql get() {
        return instance;
    }

    public void connect() {
        try {
            File file = new File(RPG.getInstance().getDataFolder(), "bosses.db");
            if (!file.exists()) {
                file.createNewFile();
            }
            connection = DriverManager.getConnection("jdbc:sqlite:" + file);

            update("CREATE TABLE IF NOT EXISTS bosses(ID VARCHAR(255), DATE VARCHAR(255), TOP VARCHAR(255), PRIMARY KEY (ID))");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(String sql) {
        try {
            connection.prepareStatement(sql).executeUpdate();
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
