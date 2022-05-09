package com.eslym.autocomposter.screens;

import com.eslym.autocomposter.menus.AutoComposterMenu;
import com.eslym.autocomposter.menus.PowerComposterMenu;
import com.google.common.collect.Lists;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

import static com.eslym.autocomposter.AutoComposterMod.MODID;
import static com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity.ENERGY_CAPACITY;
import static com.eslym.autocomposter.blocks.entities.PowerComposterBlockEntity.WATER_CAPACITY;

public class PowerComposterScreen extends AutoComposterScreen {
    protected TranslatableComponent energyTooltip = new TranslatableComponent("tooltip.autocomposter.energy");
    protected TranslatableComponent waterTooltip = new TranslatableComponent("tooltip.autocomposter.water");

    public PowerComposterScreen(AutoComposterMenu menu, Inventory inv, Component name) {
        super(menu, inv, name);
        GUI = new ResourceLocation(MODID, "textures/gui/container/powercomposter.png");
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        super.render(matrixStack, mouseX, mouseY, partialTicks);
    }

    @Override
    protected void renderTooltip(@NotNull PoseStack matrixStack, int mouseX, int mouseY) {
        super.renderTooltip(matrixStack, mouseX, mouseY);
        if(menu instanceof PowerComposterMenu power){
            if (mouseY >= (startY + 26) && mouseY < (startY + 56)){
                if (mouseX >= (startX + 11) && mouseX < (startX + 21)){
                    TextComponent energyText = new TextComponent(String.format("%d / %d", power.getEnergyAmount(), ENERGY_CAPACITY));
                    renderTooltip(
                            matrixStack,
                            Lists.newArrayList(energyTooltip, energyText),
                            Optional.empty(),
                            mouseX, mouseY
                    );
                } else if (mouseX >= (startX + 25) && mouseX < (startX + 35)){
                    TextComponent waterText = new TextComponent(String.format("%d / %d", power.getWaterAmount(), WATER_CAPACITY));
                    renderTooltip(
                            matrixStack,
                            Lists.newArrayList(waterTooltip, waterText),
                            Optional.empty(),
                            mouseX, mouseY
                    );
                }
            }
        }
    }

    @Override
    protected void renderBg(@NotNull PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        super.renderBg(matrixStack, partialTicks, mouseX, mouseY);
        if(menu instanceof PowerComposterMenu power){
            renderBar(matrixStack, 11, 26, 0, 189, power.getEnergyAmount(), ENERGY_CAPACITY);
            renderBar(matrixStack, 25, 26, 10, 189, power.getWaterAmount(), WATER_CAPACITY);
        }
    }

    @Override
    protected void renderCompostLevel(@NotNull PoseStack matrixStack, int x, int y) {
        super.renderCompostLevel(matrixStack, 138, y);
    }

    protected void renderBar(@NotNull PoseStack matrixStack, int x, int y, int resX, int resY, int amount, int max){
        x += startX;
        y += startY;
        if(amount > 0){
            int height = (int) Math.ceil(30.0f * amount / max);
            int offset = 30 - height;
            this.blit(matrixStack, x, y + offset, resX, resY, 10, height);
        }
        this.blit(matrixStack, x, y, 20, 189, 10, 30);
    }
}
