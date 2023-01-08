package net.jxtremeog.improvmentsmod.world.feature;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.jxtremeog.improvmentsmod.block.ModBlocks;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, ImprovmentsMod.MOD_ID);

    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_QUARTZ_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, ModBlocks.QUARTZ_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, ModBlocks.DEEPSLATE_QUARTZ_ORE.get().defaultBlockState())));

    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_COAL_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_COAL_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_IRON_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_IRON_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_DIAMOND_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_DIAMOND_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_EMERALD_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_EMERALD_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_REDSTONE_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_REDSTONE_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_LAPIS_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_LAPIS_ORE.get().defaultBlockState())));
    public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_COPPER_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, ModBlocks.NETHER_COPPER_ORE.get().defaultBlockState())));

    //Quartz Overworld
    public static final RegistryObject<ConfiguredFeature<?, ?>> QUARTZ_ORE = CONFIGURED_FEATURES.register("quartz_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_QUARTZ_ORES.get(),8/*Vien Size*/)));

    //Nether Ores
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_COAL_ORE = CONFIGURED_FEATURES.register("nether_coal_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_COAL_ORES.get(),16)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_IRON_ORE = CONFIGURED_FEATURES.register("nether_iron_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_IRON_ORES.get(),12)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_DIAMOND_ORE = CONFIGURED_FEATURES.register("nether_diamond_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_DIAMOND_ORES.get(),6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_EMERALD_ORE = CONFIGURED_FEATURES.register("nether_emerald_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_EMERALD_ORES.get(),6)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_REDSTONE_ORE = CONFIGURED_FEATURES.register("nether_redstone_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_REDSTONE_ORES.get(),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_LAPIS_ORE = CONFIGURED_FEATURES.register("nether_lapis_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_LAPIS_ORES.get(),8)));
    public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_COPPER_ORE = CONFIGURED_FEATURES.register("nether_copper_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_COPPER_ORES.get(),12)));


    public static void register(IEventBus eventBus) {
        CONFIGURED_FEATURES.register(eventBus);
    }
}
