package com.eslym.autocomposter.blocks;

import com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity;
import com.eslym.autocomposter.menus.PowerComposterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PowerComposterBlock extends AutoComposterBlock {

    public static final String BLOCK_ID = "powercomposter";

    @Override
    public @Nullable BlockEntity newBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
        return new PowerComposterBlockEntity(pos, state);
    }

    @Override
    protected MenuProvider createMenu(Level world, BlockPos pos) {
        return new MenuProvider() {
            @Override
            public @NotNull Component getDisplayName() {
                return new TranslatableComponent("block.autocomposter.powercomposter");
            }

            @Override
            public AbstractContainerMenu createMenu(int windowId, @NotNull Inventory inv, @NotNull Player p) {
                return new PowerComposterMenu(windowId, world, pos, inv, p);
            }
        };
    }
}
