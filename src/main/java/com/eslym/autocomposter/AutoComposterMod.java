package com.eslym.autocomposter;

import com.eslym.autocomposter.blocks.AutoComposterScreen;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(AutoComposterMod.MODID)
public class AutoComposterMod
{
    public static final String MODID = "autocomposter";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public AutoComposterMod()
    {
        // Register the setup method for modloading
        FMLJavaModLoadingContext context = FMLJavaModLoadingContext.get();
        context.getModEventBus().addListener(this::setup);
        Registries.register(context.getModEventBus());

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
        event.enqueueWork(()->{
            MenuScreens.register(Registries.Menus.AUTO_COMPOSTER.get(), AutoComposterScreen::new);
        });
    }
}
