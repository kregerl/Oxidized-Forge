package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.recipe.CopperKilnRecipe;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;

public class RecipeRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Oxidized.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CopperKilnRecipe>> COPPER_KILN_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("kiln_smelting", CopperKilnRecipe.CopperKilnRecipeSerializer::new);
//    public static final RecipeType<CopperKilnRecipe> COPPER_KILN_RECIPE_TYPE = registerType(new ResourceLocation(Oxidized.MOD_ID, "kiln_smelting"));
//
//    public static class ModRecipeType<T extends Recipe<?>> implements RecipeType<T> {
//        @Override
//        public String toString() {
//            return Registry.RECIPE_TYPE.getKey(this).toString();
//        }
//    }
//
//    private static <T extends RecipeType<?>> T registerType(ResourceLocation recipeType) {
//        return Registry.register(Registry.RECIPE_TYPE, recipeType, (T) new ModRecipeType<>());
//    }

    public static final RecipeType<CopperKilnRecipe> COPPER_KILN_RECIPE_TYPE = new RecipeType<CopperKilnRecipe>() {
        @Override
        public <C extends Container> Optional<CopperKilnRecipe> tryMatch(Recipe<C> recipe, Level world, C inv) {
            return recipe.matches(inv, world) ? Optional.of((CopperKilnRecipe) recipe) : Optional.empty();
        }
    };

}
