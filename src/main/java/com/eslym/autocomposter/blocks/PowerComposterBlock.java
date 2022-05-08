package com.eslym.autocomposter.blocks;

import com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity;
import com.eslym.autocomposter.menus.AutoComposterMenu;
import it.unimi.dsi.fastutil.objects.Object2FloatMap;
import it.unimi.dsi.fastutil.objects.Object2FloatMaps;
import it.unimi.dsi.fastutil.objects.Object2FloatOpenHashMap;
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
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Fluids;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PowerComposterBlock extends AutoComposterBlock {

    public static final String BLOCK_ID = "powercomposter";

    public static final Object2FloatMap<Fluid> BOOSTABLES = new Object2FloatOpenHashMap<>();

    public static void bootstrap(){
        BOOSTABLES.put(Fluids.WATER, 1.1f);
    }

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
                return new AutoComposterMenu(windowId, world, pos, inv, p);
            }
        };
    }
}
