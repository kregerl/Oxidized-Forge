package com.loucaskreger.oxidized.block;

import com.loucaskreger.oxidized.util.CopperStateMap;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoneycombItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Optional;
import java.util.Random;

public class OxidizableBlock extends Block implements WeatheringCopper {

    private final WeatheringCopper.WeatherState weatherState;

    public OxidizableBlock(Properties properties, WeatheringCopper.WeatherState weatherState) {
        super(properties);
        this.weatherState = weatherState;
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        var stack = player.getItemInHand(hand);
        if (stack.getItem() == Items.HONEYCOMB) {
            var block = CopperStateMap.getWaxed(state.getBlock());
            if (block.isPresent()) {
                world.setBlock(pos, block.map(b -> b.withPropertiesOf(state)).get(), 11);
                world.levelEvent(player, 3003, pos, 0);
                stack.shrink(1);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }


    @Override
    public Optional<BlockState> getNext(BlockState state) {
        return CopperStateMap.getIncrease(state.getBlock()).map((block) -> block.withPropertiesOf(state));
    }

    @Override
    public void m_7100_(BlockState state, Level serverWorld, BlockPos pos, Random random) {
        this.m_7100_(state, serverWorld, pos, random);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return CopperStateMap.getIncrease(state.getBlock()).isPresent();
    }

    @Override
    public WeatherState getAge() {
        return this.weatherState;
    }
}
