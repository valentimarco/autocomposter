package com.eslym.autocomposter;

import com.eslym.autocomposter.blocks.AutoComposter;
import com.eslym.autocomposter.blocks.AutoComposterBlockEntity;
import com.eslym.autocomposter.blocks.AutoComposterMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

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

        public static final RegistryObject<Item> AUTO_COMPOSTER = REGISTRIES.register(AutoComposter.BLOCK_ID, () -> new BlockItem(Blocks.AUTO_COMPOSTER.get(), new Item.Properties().tab(CreativeModeTab.TAB_DECORATIONS)));
    }
    public static final class Blocks {
        private static final DeferredRegister<Block> REGISTRIES = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

        public static final RegistryObject<Block> AUTO_COMPOSTER = REGISTRIES.register(AutoComposter.BLOCK_ID, AutoComposter::new);
    }

    @SuppressWarnings("ConstantConditions")
    public static final class BlockEntities {
        private static final DeferredRegister<BlockEntityType<?>> REGISTRIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, MODID);

        public static final RegistryObject<BlockEntityType<AutoComposterBlockEntity>> AUTO_COMPOSTER = REGISTRIES.register(AutoComposter.BLOCK_ID, () -> BlockEntityType.Builder.of(AutoComposterBlockEntity::new, Registries.Blocks.AUTO_COMPOSTER.get()).build(null));
    }

    public static final class Menus {
        private static final DeferredRegister<MenuType<?>> REGISTRIES = DeferredRegister.create(ForgeRegistries.CONTAINERS, MODID);

        public static final RegistryObject<MenuType<AutoComposterMenu>> AUTO_COMPOSTER = REGISTRIES.register(AutoComposter.BLOCK_ID,
                () -> IForgeMenuType.create((windowId, inv, data) -> {
                    BlockPos pos = data.readBlockPos();
                    Level world = inv.player.getCommandSenderWorld();
                    return new AutoComposterMenu(windowId, world, pos, inv, inv.player);
                }));
    }
}
