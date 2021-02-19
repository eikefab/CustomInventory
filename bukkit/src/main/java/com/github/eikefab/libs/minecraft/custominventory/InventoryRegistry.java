package com.github.eikefab.libs.minecraft.custominventory;

import com.github.eikefab.libs.minecraft.custominventory.inventory.CustomInventory;
import lombok.Data;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.function.Consumer;

@Data(staticConstructor = "of")
public final class InventoryRegistry {

    private final Plugin plugin;

    public void addItem(CustomInventory inventory, ItemStack stack, Consumer<InventoryClickEvent> consumer) {
        Bukkit.getPluginManager().registerEvents(new Listener() {
            @EventHandler
            private void handle(InventoryClickEvent event) {
                if (!event.getInventory().getTitle().equals(inventory.getBukkitInventory().getTitle())) return;

                if (event.getCurrentItem() == null) return;
                if (event.getCurrentItem().getType() == Material.AIR) return;

                event.setCancelled(inventory.isCancellingClick());

                if (stack.isSimilar(event.getCurrentItem())) consumer.accept(event);
            }
        }, plugin);
    }

    public CustomInventory create(InventoryBuilder builder) {
        return new CustomInventory(
                this,
                Bukkit.createInventory(builder.owner(), builder.size(), builder.title()),
                builder.cancel()
        );
    }

}
