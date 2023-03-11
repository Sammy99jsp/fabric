package dev.sammy99jsp.fabric.util.inventory;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;

public interface ModInventory extends Inventory {

    DefaultedList<ItemStack> getItems();

    @Override
    default boolean canPlayerUse(PlayerEntity player) {
        return true;
    }

    @Override
    default ItemStack getStack(int slot) {
        return this.getItems().get(slot);
    }

    @Override
    default boolean isEmpty() {
        return this.getItems()
            .stream()
            .allMatch(ItemStack::isEmpty);
    }

    @Override
    default ItemStack removeStack(int slot) {
        ItemStack tmp = Inventories.removeStack(this.getItems(), slot);
        this.markDirty();
        return tmp;
    }

    @Override
    default ItemStack removeStack(int slot, int count) {
        ItemStack result = Inventories.splitStack(getItems(), slot, count);
        if (!result.isEmpty()) {
            markDirty();
        }
        return result;
    }

    @Override
    default void setStack(int slot, ItemStack stack) {
        this.getItems()
            .set(slot, stack);
        
        if (stack.getCount() > stack.getMaxCount()) {
            stack.setCount(stack.getMaxCount());
        }
        this.markDirty();
    }

    @Override
    default int size() {
        return this.getItems()
            .size();
    }

    @Override
    default void clear() {
        this.getItems().clear();
        this.markDirty();
    }

    @Override
    default void markDirty() {}
    
}
