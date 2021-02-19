package com.github.eikefab.libs.minecraft.custominventory.item;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public final class ItemCreator {
    
    @Getter private final ItemStack itemStack;
    
    public static ItemCreator newItem(Material material) {
        return new ItemCreator(material);
    }
    
    public ItemCreator(ItemStack stack) {
        this.itemStack = stack;
    }
    
    public ItemCreator(Material type) {
        this(new ItemStack(type));
    }
    
    public ItemCreator(Material type, int amount) {
        this(new ItemStack(type, amount));
    }
    
    public ItemCreator(Material type, int amount, short durability) {
        this(new ItemStack(type, amount, durability));
    }

    public ItemCreator apply(Consumer<ItemStack> stack) {
        stack.accept(itemStack);

        return this;
    }

    public ItemCreator applyMeta(Consumer<ItemMeta> meta) {
        final ItemMeta itemMeta = itemStack.getItemMeta();
        
        meta.accept(itemMeta);
        
        itemStack.setItemMeta(itemMeta);
        
        return this;
    }
    
    private String colorize(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    
    public ItemCreator display(String title) {
        return applyMeta(meta -> meta.setDisplayName(colorize(title)));
    }
    
    public ItemCreator lore(String... lines) {
        return applyMeta(meta -> meta.setLore(Arrays.stream(lines).map(this::colorize).collect(Collectors.toList())));
    }
    
    public ItemCreator flags(ItemFlag... flag) {
        return applyMeta(meta -> meta.addItemFlags(flag));
    }

    public ItemCreator enchant(int level, Enchantment enchantment) {
        return applyMeta(meta -> meta.addEnchant(enchantment, level, true));
    }

    public ItemCreator durability(short durability) {
        return apply(stack -> stack.setDurability(durability));
    }

    public ItemCreator amount(int amount) {
        return apply(stack -> stack.setAmount(amount));
    }
    
}
