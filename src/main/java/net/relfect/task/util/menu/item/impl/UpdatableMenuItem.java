package net.relfect.task.util.menu.item.impl;

import lombok.Getter;
import lombok.Setter;
import net.relfect.task.util.menu.Menu;
import net.relfect.task.util.menu.item.MenuItem;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@Setter
public class UpdatableMenuItem extends MenuItem {

    Updater runnable;

    public UpdatableMenuItem(ItemStack itemstack) {
        super(itemstack);
    }

    public UpdatableMenuItem(Material material) {
        super(material);
    }

    public interface Updater {
        void update(Player p, UpdatableMenuItem item, Menu menu, int slot);
    }

}
