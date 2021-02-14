package com.github.eikefab.libs.minecraft.inventory;

import com.github.eikefab.libs.minecraft.InventoryRegistry;
import lombok.Data;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

@Data
public class CustomInventory {

    private final InventoryRegistry registry;
    private final Inventory bukkitInventory;
    private final boolean cancellingClick;

    public CustomInventoryItem set(int slot, ItemStack stack) {
        getBukkitInventory().setItem(slot, stack);

        return new CustomInventoryItem(registry, this, stack);
    }

}
