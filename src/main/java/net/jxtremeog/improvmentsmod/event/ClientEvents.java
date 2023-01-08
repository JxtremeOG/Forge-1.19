package net.jxtremeog.improvmentsmod.event;

import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.jxtremeog.improvmentsmod.client.FluidOverlay;
import net.jxtremeog.improvmentsmod.util.KeyBinding;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


public class ClientEvents {
    @Mod.EventBusSubscriber(modid = ImprovmentsMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {
        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {
            if(KeyBinding.TESTING_KEY.consumeClick()) {
                if(!((Minecraft.getInstance().player.getEyeInFluidType()+"").equals("minecraft:empty"))){
                    System.out.println("Bruh" + Minecraft.getInstance().player.getBlockStateOn());
                }
                //Minecraft.getInstance().player.sendSystemMessage(Component.literal("Pressed a Key!"));
            }
        }
    }
    @Mod.EventBusSubscriber(modid = ImprovmentsMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents{
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.TESTING_KEY);
        }
        @SubscribeEvent
        public static void registerGuiOverlays(RegisterGuiOverlaysEvent event){
            event.registerAbove(new ResourceLocation("vignette"),"fluid", FluidOverlay.HUD_FLUID);
        }
    }
}
