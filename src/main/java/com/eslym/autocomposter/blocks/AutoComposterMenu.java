package com.eslym.autocomposter.blocks;

import com.eslym.autocomposter.Registries;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

public class AutoComposterMenu extends AbstractContainerMenu {
    protected final Level world;
    protected final BlockPos pos;

    protected final Inventory playerInv;

    protected final Player player;

    protected final AutoComposterBlockEntity blockEntity;

    public AutoComposterMenu(int windowId, Level world, BlockPos pos, Inventory playerInventory, Player player){
        super(Registries.Menus.AUTO_COMPOSTER.get(), windowId);
        this.world = world;
        this.pos = pos;
        this.playerInv = playerInventory;
        this.player = player;
        this.blockEntity = (AutoComposterBlockEntity) world.getBlockEntity(pos);

        if(this.blockEntity != null){
            IItemHandler handler = blockEntity.getItemHandler();

            for(int j = 0; j < 5; ++j) {
                this.addSlot(new SlotItemHandler(handler, j, 21 + j * 18, 20));
            }

            for(int j = 0; j < 5; ++j) {
                this.addSlot(new SlotItemHandler(handler, j + 5, 21 + j * 18, 46));
            }

            for(int l = 0; l < 3; ++l) {
                for(int k = 0; k < 9; ++k) {
                    this.addSlot(new Slot(playerInventory, k + l * 9 + 9, 8 + k * 18, l * 18 + 83));
                }
            }

            for(int i = 0; i < 9; ++i) {
                this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 141));
            }
        }
    }

    @Override
    public @NotNull ItemStack quickMoveStack(@NotNull Player player, int index) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stack = slot.getItem();
            result = stack.copy();
            if (index < 10) {
                if (!this.moveItemStackTo(stack, 10, this.slots.size(), true)){
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(stack, 0, 5, false)){
                return ItemStack.EMPTY;
            }

            if (stack.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return result;
    }

    @Override
    public boolean stillValid(@NotNull Player player) {
        return stillValid(ContainerLevelAccess.create(world, pos), player, Registries.Blocks.AUTO_COMPOSTER.get());
    }

    public int getCompostLevel(){
        return blockEntity.getBlockState().getValue(BlockStateProperties.LEVEL_COMPOSTER);
    }
}
