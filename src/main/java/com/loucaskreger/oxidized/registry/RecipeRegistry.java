package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.recipe.CopperKilnRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class RecipeRegistry {

    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Oxidized.MOD_ID);

    public static final RegistryObject<RecipeSerializer<CopperKilnRecipe>> COPPER_KILN_RECIPE_SERIALIZER = RECIPE_SERIALIZERS.register("kiln_smelting", CopperKilnRecipe.CopperKilnRecipeSerializer::new);
}
