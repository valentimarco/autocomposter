package com.eslym.autocomposter;

import com.eslym.autocomposter.screens.AutoComposterScreen;
import com.eslym.autocomposter.screens.PowerComposterScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AutoComposterMod.MODID)
public class AutoComposterMod
{
    public static final String MODID = "autocomposter";
    public static final CreativeTab CREATIVE_TAB = new CreativeTab();

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public AutoComposterMod()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        context.getModEventBus().addListener(this::setup);
        context.getModEventBus().addListener(this::clientSetup);
        Registries.register(context.getModEventBus());
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(()->{
            MenuScreens.register(Registries.Menus.AUTO_COMPOSTER.get(), AutoComposterScreen::new);
            MenuScreens.register(Registries.Menus.POWER_COMPOSTER.get(), PowerComposterScreen::new);
        });
    }

    private void clientSetup(FMLClientSetupEvent event){
        ItemBlockRenderTypes.setRenderLayer(Registries.Blocks.AUTO_COMPOSTER.get(), RenderType.translucent());
        ItemBlockRenderTypes.setRenderLayer(Registries.Blocks.POWER_COMPOSTER.get(), RenderType.translucent());
    }

    public static class CreativeTab extends CreativeModeTab {

        public CreativeTab() {
            super(MODID);
        }

        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(Registries.Items.AUTO_COMPOSTER.get());
        }
    }
}
