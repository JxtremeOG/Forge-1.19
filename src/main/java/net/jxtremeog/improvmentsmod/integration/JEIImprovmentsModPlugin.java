package net.jxtremeog.improvmentsmod.integration;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.jxtremeog.improvmentsmod.recipe.TempRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;
import java.util.Objects;

@JeiPlugin
public class JEIImprovmentsModPlugin implements IModPlugin {

    public static RecipeType<TempRecipe> TEMP_TYPE =
            new RecipeType<>(TempRecipeCategory.UID, TempRecipe.class);
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(ImprovmentsMod.MOD_ID,"jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new
                TempRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager rm = Objects.requireNonNull(Minecraft.getInstance().level).getRecipeManager();
        List<TempRecipe> recipesInfusing = rm.getAllRecipesFor(TempRecipe.Type.INSTANCE);
        registration.addRecipes(TEMP_TYPE, recipesInfusing);
    }
}
