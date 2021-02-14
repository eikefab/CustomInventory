package com.github.eikefab.minecraft;

import com.github.eikefab.libs.minecraft.InventoryRegistry;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class InventoryExamplePlugin extends JavaPlugin {

    @Getter private InventoryRegistry inventoryRegistry;

    @Override
    public void onEnable() {
        inventoryRegistry = InventoryRegistry.of(this);

        getCommand("inventory").setExecutor(new InventoryCommand(this));
    }

}
