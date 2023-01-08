package net.jxtremeog.improvmentsmod.villager;

import com.google.common.collect.ImmutableSet;
import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.jxtremeog.improvmentsmod.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.InvocationTargetException;

public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, ImprovmentsMod.MOD_ID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, ImprovmentsMod.MOD_ID);

    public static final RegistryObject<PoiType> CAULDRON_POI = POI_TYPES.register("cauldron_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.NETHER_LAPIS_ORE.get()
                    .getStateDefinition().getPossibleStates()), 1, 1));

    public static final RegistryObject<VillagerProfession> CAULDRON_MASTER = VILLAGER_PROFESSIONS.register("cauldron_master",
            () -> new VillagerProfession("cauldron_master", x -> x.get() == CAULDRON_POI.get(),
                    x -> x.get() == CAULDRON_POI.get(), ImmutableSet.of(), ImmutableSet.of(),
                    SoundEvents.VILLAGER_WORK_ARMORER));


    public static void registerPOIS(){
        try {
            ObfuscationReflectionHelper.findMethod(PoiType.class,
                    "registerBlockStates", PoiType.class).invoke(null, CAULDRON_POI.get());
        } catch (InvocationTargetException | IllegalAccessException exception){
            exception.printStackTrace();
        }
    }

    public static void register(IEventBus eventBus){
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}
