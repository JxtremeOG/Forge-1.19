package net.jxtremeog.improvmentsmod.recipe;

import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ImprovmentsMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<TempRecipe>> TEMP_SERIALIZER =
            SERIALIZERS.register("testing", () -> TempRecipe.Serializer.INSTANCE);
    public static final RegistryObject<RecipeSerializer<TierOneRecipe>> WORKBENCH_ONE_SERIALIZER =
            SERIALIZERS.register("workbench_one", () -> TierOneRecipe.Serializer.WORKBENCH_ONE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
