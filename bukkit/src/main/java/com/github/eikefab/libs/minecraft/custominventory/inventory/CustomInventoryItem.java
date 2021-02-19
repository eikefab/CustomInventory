package com.github.eikefab.libs.minecraft.custominventory.inventory;

import com.github.eikefab.libs.minecraft.custominventory.InventoryRegistry;
import lombok.Data;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Data
public class CustomInventoryItem {

    private final InventoryRegistry registry;

    private final CustomInventory inventory;
    private final ItemStack itemStack;

    public void thenClick(Consumer<InventoryClickEvent> consumer) {
        registry.addItem(inventory, itemStack, consumer);
    }

}
