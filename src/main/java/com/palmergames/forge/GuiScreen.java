package com.palmergames.forge;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.MenuAccess;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ChestMenu;

public class GuiScreen extends AbstractContainerScreen<GuiChestMenu> implements MenuAccess<GuiChestMenu> {
	private static final ResourceLocation CONTAINER_BACKGROUND = new ResourceLocation("textures/gui/container/generic_54.png");
	private final int containerRows;

	@Override
	public void mouseMoved(double p_94758_, double p_94759_) {
		super.mouseMoved(p_94758_, p_94759_);
	}

	@Override
	public boolean mouseClicked(double p_97748_, double p_97749_, int p_97750_) {
		return super.mouseClicked(p_97748_, p_97749_, p_97750_);
	}

	public GuiScreen(GuiChestMenu p_98409_, Inventory p_98410_, Component p_98411_) {
		super(p_98409_, p_98410_, p_98411_);
		this.passEvents = false;
		int i = 222;
		int j = 114;
		this.containerRows = p_98409_.getRowCount();
		this.imageHeight = 114 + this.containerRows * 18;
		this.inventoryLabelY = this.imageHeight - 94;
	}

	public void render(PoseStack p_98418_, int p_98419_, int p_98420_, float p_98421_) {
		this.renderBackground(p_98418_);
		super.render(p_98418_, p_98419_, p_98420_, p_98421_);
		this.renderTooltip(p_98418_, p_98419_, p_98420_);
	}

	protected void renderBg(PoseStack p_98413_, float p_98414_, int p_98415_, int p_98416_) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.setShaderTexture(0, CONTAINER_BACKGROUND);
		int i = (this.width - this.imageWidth) / 2;
		int j = (this.height - this.imageHeight) / 2;
		this.blit(p_98413_, i, j, 0, 0, this.imageWidth, this.containerRows * 18 + 17);
		this.blit(p_98413_, i, j + this.containerRows * 18 + 17, 0, 126, this.imageWidth, 96);
	}
}
