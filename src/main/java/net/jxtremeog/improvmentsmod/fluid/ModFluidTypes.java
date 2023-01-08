package net.jxtremeog.improvmentsmod.fluid;
import com.mojang.math.Vector3f;
import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.Sound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.WaterFluid;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.SoundAction;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Properties;

import static net.minecraft.tags.FluidTags.WATER;
import static net.minecraft.world.level.pathfinder.BlockPathTypes.WATER_BORDER;

public class ModFluidTypes {
    public static final ResourceLocation WATER_STILL_RL = new ResourceLocation("block/water_still");
    public static final ResourceLocation WATER_FLOWING_RL = new ResourceLocation("block/water_flow");
    public static final ResourceLocation WATER_OVERLAY_RL = new ResourceLocation("block/water_overlay");

    public static final DeferredRegister<FluidType> FLUID_TYPES =
            DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, ImprovmentsMod.MOD_ID);

    //SPECIFIC FLUID
    public static final RegistryObject<FluidType> REGENERATION_FLUID_TYPE = register("regeneration_fluid",
            FluidType.Properties.create()
                    .canDrown(true).canExtinguish(true).canSwim(true).canPushEntity(true)
                    .lightLevel(0/*2*/).density(1000).temperature(300).viscosity(1000).motionScale(.008).fallDistanceModifier(0));

    private static RegistryObject<FluidType> register(String name, FluidType.Properties properties) {
        return FLUID_TYPES.register(name, () -> new BaseFluidType(WATER_STILL_RL, WATER_FLOWING_RL, WATER_OVERLAY_RL,
                0xA1d12acf /*SURFACE COLOR*/, new Vector3f(224f / 255f, 56f / 255f, 208f / 255f), properties));
    }



    public static void register(IEventBus eventBus) {
        FLUID_TYPES.register(eventBus);
    }
}
