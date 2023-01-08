package net.jxtremeog.improvmentsmod.block.entity;

import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.jxtremeog.improvmentsmod.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, ImprovmentsMod.MOD_ID);

    public static final RegistryObject<BlockEntityType<TempBlockEntity>> TEMP =
            BLOCK_ENTITIES.register("temp", () ->
                    BlockEntityType.Builder.of(TempBlockEntity::new,
                            ModBlocks.TEMP.get()).build(null));
    public static final RegistryObject<BlockEntityType<TierOneBlockEntity>> TIERONE =
            BLOCK_ENTITIES.register("tier_one", () ->
                    BlockEntityType.Builder.of(TierOneBlockEntity::new,
                            ModBlocks.TIERONE.get()).build(null));
    public static final RegistryObject<BlockEntityType<TierTwoBlockEntity>> TIERTWO =
            BLOCK_ENTITIES.register("tier_two", () ->
                    BlockEntityType.Builder.of(TierTwoBlockEntity::new,
                            ModBlocks.TIERTWO.get()).build(null));
    public static final RegistryObject<BlockEntityType<CraftingBlockEntity>> CRAFTING_BLOCK =
            BLOCK_ENTITIES.register("crafting_block", () ->
                    BlockEntityType.Builder.of(CraftingBlockEntity::new,
                            ModBlocks.TIERONE.get()).build(null));

    public static void register(IEventBus eventBus){
        BLOCK_ENTITIES.register(eventBus);
    }
}

