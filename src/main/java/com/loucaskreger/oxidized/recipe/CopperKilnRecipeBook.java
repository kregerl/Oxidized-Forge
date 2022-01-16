package com.loucaskreger.oxidized.recipe;

import net.minecraft.client.gui.screens.recipebook.AbstractFurnaceRecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;

import java.util.Set;

public class CopperKilnRecipeBook extends AbstractFurnaceRecipeBookComponent {


    @Override
    protected Component getRecipeFilterName() {
        return new TranslatableComponent("gui.recipebook.toggleRecipes.kiln");
    }

    @Override
    protected Set<Item> getFuelItems() {
        return AbstractFurnaceBlockEntity.getFuel().keySet();
    }


}
