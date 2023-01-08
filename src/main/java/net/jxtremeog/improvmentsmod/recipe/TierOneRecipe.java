package net.jxtremeog.improvmentsmod.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.minecraft.core.NonNullList;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;

public class TierOneRecipe implements Recipe<CraftingContainer> {
    private final ResourceLocation id;
    private final ItemStack output;
    private final NonNullList<Ingredient> recipeItems;

    public TierOneRecipe(ResourceLocation id, ItemStack output,
                                    NonNullList<Ingredient> recipeItems) {
        this.id = id;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(CraftingContainer pContainer, Level pLevel) {

        if(pLevel.isClientSide()){
            return false;
        }
        System.out.println(recipeItems.get(0).test(pContainer.getItem(4)));
        return recipeItems.get(0).test(pContainer.getItem(4));
    }

    @Override
    public ItemStack assemble(CraftingContainer pContainer) {
        return output;
    }

    @Override
    public boolean canCraftInDimensions(int pWidth, int pHeight) {
        return true;
    }

    @Override
    public ItemStack getResultItem() {
        return output.copy();
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.WORKBENCH_ONE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.WORKBENCH_ONE;
    }

    public static class Type implements RecipeType<TierOneRecipe> {
        private Type() { }
        public static final Type WORKBENCH_ONE = new Type();
        public static final String ID = "workbench_one";
    }

    public static class Serializer implements RecipeSerializer<TierOneRecipe> {
        public static final Serializer WORKBENCH_ONE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(ImprovmentsMod.MOD_ID, "workbench_one");

        @Override
        public TierOneRecipe fromJson(ResourceLocation pRecipeId, JsonObject pSerializedRecipe) {
            ItemStack output = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(pSerializedRecipe, "output"));

            JsonArray ingredients = GsonHelper.getAsJsonArray(pSerializedRecipe, "ingredients");
            NonNullList<Ingredient> inputs = NonNullList.withSize(/*AMOUNT OF INGREDIENTS*/1, Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromJson(ingredients.get(i)));
            }

            return new TierOneRecipe(pRecipeId, output, inputs);
        }

        @Override
        public @Nullable TierOneRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
            NonNullList<Ingredient> inputs = NonNullList.withSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromNetwork(buf));
            }

            ItemStack output = buf.readItem();
            return new TierOneRecipe(id, output, inputs);
        }
        @Override
        public void toNetwork(FriendlyByteBuf buf, TierOneRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ing : recipe.getIngredients()) {
                ing.toNetwork(buf);
            }
            buf.writeItemStack(recipe.getResultItem(), false);
        }
    }
    }
