package com.eslym.autocomposter.menus;

import com.eslym.autocomposter.Registries;
import com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.IItemHandler;
import org.jetbrains.annotations.NotNull;

public class PowerComposterMenu extends AutoComposterMenu {

    public PowerComposterMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super(Registries.Menus.POWER_COMPOSTER.get(), windowId, world, pos, playerInventory, player);
    }

    @Override
    protected void init(Level world, BlockPos pos, Inventory playerInventory, Player player) {
        super.init(world, pos, playerInventory, player);
        addDataSlots((PowerComposterBlockEntity) this.blockEntity);
    }

    @Override
    public void addComposterSlots(IItemHandler handler, int x, int y) {
        super.addComposterSlots(handler, 41, 20);
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(world, pos), player, Registries.Blocks.POWER_COMPOSTER.get());
    }

    public int getEnergyAmount(){
        return ((PowerComposterBlockEntity)blockEntity).get(0);
    }

    public int getWaterAmount(){
        return ((PowerComposterBlockEntity)blockEntity).get(1);
    }
}
