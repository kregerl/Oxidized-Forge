package com.loucaskreger.oxidized.block;

import com.loucaskreger.oxidized.config.OxidizedConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class CopperPanBlock extends Block implements SimpleWaterloggedBlock {
    public static final IntegerProperty PANNED = BlockStateProperties.HATCH;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
    private static final VoxelShape SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D);

    public CopperPanBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(PANNED, 0).setValue(WATERLOGGED, false));
    }


    @Override
    public void m_7100_(BlockState state, Level world, BlockPos pos, Random random) {
        int i = state.getValue(PANNED);
        if (state.getFluidState().is(Fluids.WATER.defaultFluidState().getType()) && isSpecialBlockBelow(world, pos)) {
            if (i < 2) {
                world.setBlock(pos, state.setValue(PANNED, i + 1), 2);
            } else {
                world.playSound(null, pos, SoundEvents.FISHING_BOBBER_SPLASH, SoundSource.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                double d = (double) (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
                double e = (double) (world.random.nextFloat() * 0.7F) + 0.06000000238418579D + 0.6D;
                double g = (double) (world.random.nextFloat() * 0.7F) + 0.15000000596046448D;
                ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + d, (double) pos.getY() + e, (double) pos.getZ() + g, new ItemStack(getPannedItem(random)));
                itemEntity.setDefaultPickUpDelay();
                world.addFreshEntity(itemEntity);
            }
        }
    }
    public Item getPannedItem(Random random) {
        if (random.nextFloat() <= OxidizedConfig.ironNuggetChance.get()) {
            return Items.IRON_NUGGET;
        } else if (random.nextFloat() <= OxidizedConfig.goldNuggetChance.get()) {
            return Items.GOLD_NUGGET;
        } else if (random.nextFloat() <= OxidizedConfig.sandChance.get()) {
            return Items.SAND;
        } else if (random.nextFloat() <= OxidizedConfig.emeraldChance.get()) {
            return Items.EMERALD;
        } else if (random.nextFloat() <= OxidizedConfig.gravelChance.get()) {
            return Items.GRAVEL;
        } else
            return Items.CLAY_BALL;
    }


    public static boolean isSpecialBlockBelow(LevelAccessor world, BlockPos pos) {
        return isBlock(world, pos.below());
    }

    public static boolean isBlock(LevelAccessor world, BlockPos pos) {
        return BlockTags.SAND.m_6497_().stream().anyMatch(b -> world.getBlockState(pos).is(b)) || world.getBlockState(pos).is(Blocks.GRAVEL);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        FluidState fluidState = ctx.getLevel().getFluidState(ctx.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluidState.getType() == Fluids.WATER);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor world, BlockPos pos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            world.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(world));
        }
        return super.updateShape(state, direction, neighborState, world, pos, neighborPos);
    }


    @Override
    public RenderShape getRenderShape(BlockState state) {
        return RenderShape.MODEL;
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(PANNED);
        builder.add(WATERLOGGED);
        super.createBlockStateDefinition(builder);
    }

}
