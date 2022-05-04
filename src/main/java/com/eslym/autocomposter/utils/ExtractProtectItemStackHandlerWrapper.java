package com.eslym.autocomposter.utils;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ExtractProtectItemStackHandlerWrapper implements IItemHandler, IItemHandlerModifiable {
    private final ItemStackHandler source;
    private final List<Integer> protectedSlots;

    public ExtractProtectItemStackHandlerWrapper(ItemStackHandler source, Collection<Integer> protectedSlots){
        this.source = source;
        this.protectedSlots = new ArrayList<>(protectedSlots);
    }

    @Override
    public void setStackInSlot(int slot, @NotNull ItemStack stack) {
        source.setStackInSlot(slot, stack);
    }

    @Override
    public int getSlots() {
        return source.getSlots();
    }

    @NotNull
    @Override
    public ItemStack getStackInSlot(int slot) {
        return source.getStackInSlot(slot);
    }

    @NotNull
    @Override
    public ItemStack insertItem(int slot, @NotNull ItemStack stack, boolean simulate) {
        return source.insertItem(slot, stack, simulate);
    }

    @NotNull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        if(this.protectedSlots.contains(slot)){
            return ItemStack.EMPTY;
        }
        return source.extractItem(slot, amount, simulate);
    }

    @Override
    public int getSlotLimit(int slot) {
        return source.getSlotLimit(slot);
    }

    @Override
    public boolean isItemValid(int slot, @NotNull ItemStack stack) {
        return source.isItemValid(slot, stack);
    }
}
