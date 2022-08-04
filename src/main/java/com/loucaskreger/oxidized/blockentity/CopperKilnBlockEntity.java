package com.loucaskreger.oxidized.blockentity;

import com.loucaskreger.oxidized.recipe.CopperKilnRecipe;
import com.loucaskreger.oxidized.registry.BlockEntityRegistry;
import com.loucaskreger.oxidized.registry.RecipeRegistry;
import com.loucaskreger.oxidized.screen.container.CopperKilnContainer;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CopperKilnBlockEntity extends AbstractFurnaceBlockEntity {

    public CopperKilnBlockEntity(BlockPos pos, BlockState state) {
        super(BlockEntityRegistry.COPPER_KILN.get(), pos, state, CopperKilnRecipe.Type.INSTANCE);
    }

    @Override
    protected Component getDefaultName() {
        return Component.translatable("container.oxidized.copper_kiln");
    }

    @Override
    protected AbstractContainerMenu createMenu(int id, Inventory inv) {
        return new CopperKilnContainer(id, inv, this, this.dataAccess);
    }

    @Override
    protected int getBurnDuration(ItemStack stack) {
        return super.getBurnDuration(stack) / 2;
    }

}
