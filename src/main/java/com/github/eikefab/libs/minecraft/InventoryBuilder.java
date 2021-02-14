package com.github.eikefab.libs.minecraft;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.bukkit.inventory.InventoryHolder;

@Accessors(fluent = true)
@Getter
@Setter
public final class InventoryBuilder {

    public static InventoryBuilder newBuilder() {
        return new InventoryBuilder();
    }

    private String title = "Custom Inventory";
    private int size = 9;
    private InventoryHolder owner = null;
    private boolean cancel = true;

}
