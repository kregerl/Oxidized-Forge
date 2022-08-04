package com.loucaskreger.oxidized.screen;

import com.loucaskreger.oxidized.recipe.CopperKilnRecipeBook;
import com.loucaskreger.oxidized.screen.container.CopperKilnContainer;
import net.minecraft.client.gui.screens.inventory.AbstractFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.BlastFurnaceScreen;
import net.minecraft.client.gui.screens.inventory.SmokerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.SmokerMenu;

public class CopperKilnScreen extends AbstractFurnaceScreen<CopperKilnContainer> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/container/furnace.png");

    public CopperKilnScreen(CopperKilnContainer container, Inventory inv, Component component) {
        super(container, new CopperKilnRecipeBook(), inv, component, TEXTURE);
    }

    @Override
    public RecipeBookComponent getRecipeBookComponent() {
        return super.getRecipeBookComponent();
    }
}
