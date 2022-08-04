package com.loucaskreger.oxidized.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.registry.BlockRegistry;
import com.loucaskreger.oxidized.registry.RecipeRegistry;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class CopperKilnRecipe extends AbstractCookingRecipe {

    public CopperKilnRecipe(ResourceLocation loc, String group, Ingredient input, ItemStack output, float exp, int cookTime) {
        super(Type.INSTANCE, loc, group, input, output, exp, cookTime);
    }

    @Override
    public boolean matches(Container container, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return super.matches(container, level);
    }

    @Override
    public ItemStack getResultItem() {
        return new ItemStack(BlockRegistry.COPPER_KILN.get());
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return RecipeRegistry.COPPER_KILN_RECIPE_SERIALIZER.get();
    }

    public static class Type implements RecipeType<CopperKilnRecipe> {
        private Type() {
        }

        public static final Type INSTANCE = new Type();
        public static final ResourceLocation ID = new ResourceLocation(Oxidized.MOD_ID, "kiln_smelting");
    }

    public static class CopperKilnRecipeSerializer implements RecipeSerializer<CopperKilnRecipe> {

        private final IKilnRecipe factory;
        private final int defaultCookTime;

        public CopperKilnRecipeSerializer() {
            this.factory = CopperKilnRecipe::new;
            this.defaultCookTime = 100;
        }

        public CopperKilnRecipe fromJson(ResourceLocation loc, JsonObject json) {
            String s = GsonHelper.getAsString(json, "group", "");
            JsonElement jsonelement = (JsonElement) (GsonHelper.isArrayNode(json, "ingredient") ? GsonHelper.getAsJsonArray(json, "ingredient") : GsonHelper.getAsJsonObject(json, "ingredient"));
            Ingredient ingredient = Ingredient.fromJson(jsonelement);
            //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
            if (!json.has("result"))
                throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
            ItemStack itemstack;
            if (json.get("result").isJsonObject())
                itemstack = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            else {
                String s1 = GsonHelper.getAsString(json, "result");
                ResourceLocation resourcelocation = new ResourceLocation(s1);
                itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
                    return new IllegalStateException("Item: " + s1 + " does not exist");
                }));
            }
            float f = GsonHelper.getAsFloat(json, "experience", 0.0F);
            int i = GsonHelper.getAsInt(json, "cookingtime", this.defaultCookTime);
            return this.factory.create(loc, s, ingredient, itemstack, f, i);
        }

        public CopperKilnRecipe fromNetwork(ResourceLocation loc, FriendlyByteBuf buffer) {
            String s = buffer.readUtf();
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            float f = buffer.readFloat();
            int i = buffer.readVarInt();
            return this.factory.create(loc, s, ingredient, itemstack, f, i);
        }

        public void toNetwork(FriendlyByteBuf buffer, CopperKilnRecipe recipe) {
            buffer.writeUtf(recipe.group);
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeFloat(recipe.experience);
            buffer.writeVarInt(recipe.cookingTime);
        }

        public interface IKilnRecipe {
            CopperKilnRecipe create(ResourceLocation location, String group, Ingredient input, ItemStack output, float experience, int cookTime);
        }
    }
}
