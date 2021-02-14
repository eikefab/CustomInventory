package com.github.eikefab.minecraft;

import com.github.eikefab.libs.minecraft.InventoryBuilder;
import com.github.eikefab.libs.minecraft.inventory.CustomInventory;
import com.github.eikefab.libs.minecraft.item.ItemCreator;
import lombok.Data;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@Data
public class InventoryCommand implements CommandExecutor {

    private final InventoryExamplePlugin plugin;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("You should be a player!");

            return true;
        }

        final Player player = (Player) sender;

        final CustomInventory inventory = plugin.getInventoryRegistry().create(
                InventoryBuilder.newBuilder()
                .size(3 * 9)
                .title("Hello, that's a example!")
        );

        inventory.set(13, ItemCreator.newItem(Material.BOOK_AND_QUILL)
                                     .display("&aTest!")
                                     .lore("&7Click here for send you a message!")
                                     .getItemStack()
        ).thenClick((clickEvent) -> {
            final Player clickPlayer = (Player) clickEvent.getWhoClicked();

            if (clickPlayer == player) {
                player.sendMessage(ChatColor.LIGHT_PURPLE + "Hello World!");
            }

            player.closeInventory();
        });

        player.openInventory(inventory.getBukkitInventory());

        return false;
    }
}
