package net.jxtremeog.improvmentsmod.world.feature;

import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, ImprovmentsMod.MOD_ID);
    //Ores
    //Overworld
    public static final RegistryObject<PlacedFeature> QUARTZ_ORE_PLACED = PLACED_FEATURES.register("quartz_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.QUARTZ_ORE.getHolder().get(),
                    commonOrePlacement(5, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(50)))));
    //Nether
    public static final RegistryObject<PlacedFeature> NETHER_COAL_ORE_PLACED = PLACED_FEATURES.register("nether_coal_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_COAL_ORE.getHolder().get(),
                    commonOrePlacement(10, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));
    public static final RegistryObject<PlacedFeature> NETHER_IRON_ORE_PLACED = PLACED_FEATURES.register("nether_iron_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_IRON_ORE.getHolder().get(),
                    commonOrePlacement(7, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));
    public static final RegistryObject<PlacedFeature> NETHER_DIAMOND_ORE_PLACED = PLACED_FEATURES.register("nether_diamond_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_DIAMOND_ORE.getHolder().get(),
                    commonOrePlacement(2, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));
    public static final RegistryObject<PlacedFeature> NETHER_EMERALD_ORE_PLACED = PLACED_FEATURES.register("nether_emerald_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_EMERALD_ORE.getHolder().get(),
                    commonOrePlacement(2, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));
    public static final RegistryObject<PlacedFeature> NETHER_REDSTONE_ORE_PLACED = PLACED_FEATURES.register("nether_redstone_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_REDSTONE_ORE.getHolder().get(),
                    commonOrePlacement(5, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));
    public static final RegistryObject<PlacedFeature> NETHER_LAPIS_ORE_PLACED = PLACED_FEATURES.register("nether_lapis_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_LAPIS_ORE.getHolder().get(),
                    commonOrePlacement(5, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));
    public static final RegistryObject<PlacedFeature> NETHER_COPPER_ORE_PLACED = PLACED_FEATURES.register("nether_copper_ore_placed",
            () -> new PlacedFeature(ModConfiguredFeatures.NETHER_COPPER_ORE.getHolder().get(),
                    commonOrePlacement(7, // VeinsPerChunk
                            HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(0), VerticalAnchor.aboveBottom(128)))));





    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }


    public static void register(IEventBus eventBus){
        PLACED_FEATURES.register(eventBus);
    }
}
