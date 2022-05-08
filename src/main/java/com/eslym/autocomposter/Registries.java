package com.eslym.autocomposter;

import com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity;
import com.eslym.autocomposter.blocks.AutoComposterBlock;
import com.eslym.autocomposter.blocks.entities.AutoComposterBlockEntity;
import com.eslym.autocomposter.blocks.PowerComposterBlock;
import com.eslym.autocomposter.menus.AutoComposterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

import static com.eslym.autocomposter.AutoComposterMod.MODID;

public final class Registries {

    public static void register(IEventBus eventBus){
        Blocks.REGISTRIES.register(eventBus);
        Items.REGISTRIES.register(eventBus);
        BlockEntities.REGISTRIES.register(eventBus);
        Menus.REGISTRIES.register(eventBus);
    }

    public static final class Items {
        private static final DeferredRegister<Item> REGISTRIES = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);

        private static Supplier<BlockItem> buildBlockItem(RegistryObject<Block> blockRegistry){
            return () -> new BlockItem(blockRegistry.get(), new Item.Properties().tab(AutoComposterMod.CREATIVE_TAB));
        }

        public static final RegistryObject<Item> AUTO_COMPOSTER = REGISTRIES.register(
                AutoComposterBlock.BLOCK_ID, buildBlockItem(Blocks.AUTO_COMPOSTER)
        );

        public static final RegistryObject<Item> POWER_COMPOSTER = REGISTRIES.register(
                PowerComposterBlock.BLOCK_ID, buildBlockItem(Blocks.POWER_COMPOSTER)
        );
    }
    public static final class Blocks {
        private static final DeferredRegister<Block> REGISTRIES = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

        public static final RegistryObject<Block> AUTO_COMPOSTER = REGISTRIES.register(AutoComposterBlock.BLOCK_ID, AutoComposterBlock::new);
        public static final RegistryObject<Block> POWER_COMPOSTER = REGISTRIES.register(PowerComposterBlock.BLOCK_ID, PowerComposterBlock::new);
    }

    @SuppressWarnings("ConstantConditions")
    public static final class BlockEntities {
        private static final DeferredRegister<BlockEntityType<?>> REGISTRIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);

        private static <T extends BlockEntity> Supplier<BlockEntityType<T>> buildType(BlockEntityType.BlockEntitySupplier<T> supplier, RegistryObject<Block> blockRegistry){
            return () -> BlockEntityType.Builder.of(supplier, blockRegistry.get()).build(null);
        }

        public static final RegistryObject<BlockEntityType<AutoComposterBlockEntity>> AUTO_COMPOSTER =
                REGISTRIES.register(AutoComposterBlock.BLOCK_ID, buildType(AutoComposterBlockEntity::new, Blocks.AUTO_COMPOSTER));

        public static final RegistryObject<BlockEntityType<PowerComposterBlockEntity>> POWER_COMPOSTER =
                REGISTRIES.register(PowerComposterBlock.BLOCK_ID, buildType(PowerComposterBlockEntity::new, Blocks.POWER_COMPOSTER));
    }

    public static final class Menus {
        private static final DeferredRegister<MenuType<?>> REGISTRIES = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

        private static <T extends AbstractContainerMenu> Supplier<MenuType<T>> buildMenuType(IMenuFactory<T> constructor){
            return () -> IForgeMenuType.create((windowId, inv, data) -> {
                BlockPos pos = data.readBlockPos();
                Level world = inv.player.getCommandSenderWorld();
                return constructor.construct(windowId, world, pos, inv, inv.player);
            });
        }

        public static final RegistryObject<MenuType<AutoComposterMenu>> AUTO_COMPOSTER = REGISTRIES
                .register(AutoComposterBlock.BLOCK_ID, buildMenuType(AutoComposterMenu::new));

    }
}

interface IMenuFactory<T extends AbstractContainerMenu> {
    T construct(int windowId, Level world, BlockPos pos, Inventory inv, Player player);
}
