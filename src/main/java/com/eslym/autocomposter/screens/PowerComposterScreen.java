package com.eslym.autocomposter.screens;

import com.eslym.autocomposter.menus.AutoComposterMenu;
import com.eslym.autocomposter.menus.PowerComposterMenu;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static com.eslym.autocomposter.AutoComposterMod.MODID;
import static com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity.ENERGY_CAPACITY;
import static com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity.WATER_CAPACITY;

public class PowerComposterScreen extends AutoComposterScreen {
    protected TranslatableContents energyTooltip = 
            new TranslatableContents("tooltip.autocomposter.energy", null, TranslatableContents.NO_ARGS);
    protected TranslatableContents waterTooltip = 
            new TranslatableContents("tooltip.autocomposter.water", null, TranslatableContents.NO_ARGS);

    public PowerComposterScreen(AutoComposterMenu menu, Inventory inv, Component name) {
        super(menu, inv, name);
        GUI = new ResourceLocation(MODID, "textures/gui/container/powercomposter.png");
    }

    @Override
    public void render(@NotNull GuiGraphics matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    
    protected void renderTooltip(@NotNull GuiGraphics matrixStack, int mouseX, int mouseY) {
        super.renderTooltip(matrixStack, mouseX, mouseY);
//        if(menu instanceof PowerComposterMenu power){
//            if (mouseY >= (startY + 26) && mouseY < (startY + 56)){
//                if (mouseX >= (startX + 11) && mouseX < (startX + 21)){
//                    TextComponent energyText = new TextComponent(String.format("%d / %d", power.getEnergyAmount(), ENERGY_CAPACITY));
//                    renderTooltip(
//                            matrixStack,
//                            Lists.newArrayList(energyTooltip, energyText),
//                            Optional.empty(),
//                            mouseX, mouseY
//                    );
//                } else if (mouseX >= (startX + 25) && mouseX < (startX + 35)){
//                    TextComponent waterText = new TextComponent(String.format("%d / %d", power.getWaterAmount(), WATER_CAPACITY));
//                    renderTooltip(
//                            matrixStack,
//                            Lists.newArrayList(waterTooltip, waterText),
//                            Optional.empty(),
//                            mouseX, mouseY
//                    );
//                }
//            }
//        }
    }

    
    protected void renderBg(@NotNull GuiGraphics matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
//        if(menu instanceof PowerComposterMenu power){
//            renderBar(matrixStack, 11, 26, 0, 189, power.getEnergyAmount(), ENERGY_CAPACITY);
//            renderBar(matrixStack, 25, 26, 10, 189, power.getWaterAmount(), WATER_CAPACITY);
//        }
    }

    
//    protected void renderCompostLevel(@NotNull PoseStack matrixStack, int x, int y) {
//        super.renderCompostLevel(matrixStack, 138, y);
//    }
//
//    protected void renderBar(@NotNull GuiGraphics matrixStack, int x, int y, int resX, int resY, int amount, int max){
//        x += startX;
//        y += startY;
//        if(amount > 0){
//            int height = (int) Math.ceil(30.0f * amount / max);
//            int offset = 30 - height;
//            this.blit(matrixStack, x, y + offset, resX, resY, 10, height);
//        }
//        this.blit(matrixStack, x, y, 20, 189, 10, 30);
//    }
}
