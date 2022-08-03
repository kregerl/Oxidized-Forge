package com.loucaskreger.oxidized.screen.container;

import com.loucaskreger.oxidized.registry.ContainerRegistry;
import com.loucaskreger.oxidized.registry.RecipeRegistry;
import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractFurnaceMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.RecipeBookType;

public class CopperKilnContainer extends AbstractFurnaceMenu {

    public CopperKilnContainer(int syncId, Inventory playerInventory) {
        super(ContainerRegistry.COPPER_KILN.get(), RecipeRegistry.COPPER_KILN_RECIPE_TYPE, RecipeBookType.FURNACE, syncId, playerInventory);
    }

    public CopperKilnContainer(int syncId, Inventory playerInventory, Container inventory, ContainerData dataAccess) {
        super(ContainerRegistry.COPPER_KILN.get(), RecipeRegistry.COPPER_KILN_RECIPE_TYPE, RecipeBookType.FURNACE, syncId, playerInventory, inventory, dataAccess);
    }
}
