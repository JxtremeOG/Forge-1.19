package net.jxtremeog.improvmentsmod.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;
import net.minecraftforge.fluids.FluidType;


public class FluidOverlay {
    private static final ResourceLocation FLUID_ICON = new ResourceLocation(ImprovmentsMod.MOD_ID,
            "textures/misc/overlay_test.png");
    public static final IGuiOverlay HUD_FLUID = ((gui, poseStack, partialTick, screenWidth, screenHeight) -> {
       int x = screenWidth;
       int y = screenHeight;
       //Check fluid before applying overlay
        if((Minecraft.getInstance().player.getEyeInFluidType()+"").equals("improvmentsmod:regeneration_fluid")){
            RenderSystem.setShader(GameRenderer::getPositionColorTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            RenderSystem.setShaderTexture(0, FLUID_ICON);
            GuiComponent.blit(poseStack, 0, 0, 0, 0, x, y, 12, 12);


        }
        FluidType tempfluid = Minecraft.getInstance().player.getEyeInFluidType();
        //Test Current Fluid ID
        //System.out.println(Minecraft.getInstance().player.getEyeInFluidType());
    });
}
