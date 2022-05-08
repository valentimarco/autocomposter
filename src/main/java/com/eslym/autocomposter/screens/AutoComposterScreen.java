package com.eslym.autocomposter.screens;

import com.eslym.autocomposter.menus.AutoComposterMenu;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

import static com.eslym.autocomposter.AutoComposterMod.MODID;

public class AutoComposterScreen extends AbstractContainerScreen<AutoComposterMenu> {

    public static final ResourceLocation GUI = new ResourceLocation(MODID, "textures/gui/container/autocomposter.png");

    public AutoComposterScreen(AutoComposterMenu menu, Inventory inv, Component name) {
        super(menu, inv, name);
        this.passEvents = false;
        this.imageHeight = 165;
        this.inventoryLabelY = this.imageHeight - 94;
    }

    @Override
    public void render(@NotNull PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderTooltip(matrixStack, mouseX, mouseY);
    }

    protected void renderBg(@NotNull PoseStack matrixStack, float partialTicks, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, GUI);
        int x = (this.width - this.imageWidth) / 2;
        int y = (this.height - this.imageHeight) / 2;
        this.blit(matrixStack, x, y, 0, 0, this.imageWidth, this.imageHeight);
        this.renderCompostLevel(matrixStack, x + 128, y + 29);
    }

    protected void renderCompostLevel(@NotNull PoseStack matrixStack, int x, int y) {
        int level = menu.getCompostLevel();
        if (level == 8) {
            this.blit(matrixStack, x, y, 24, 165, 24, 24);
        } else if (level > 0) {
            int height = (int) (24.0f * level / 7);
            int offset = 24 - height;
            int resOffset = 165 + offset;
            this.blit(matrixStack, x, y + offset, 0, resOffset, 24, height);
        }
    }
}
