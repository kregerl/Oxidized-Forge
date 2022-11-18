package com.loucaskreger.oxidized.entity.goal;

import com.loucaskreger.oxidized.block.CopperButton;
import com.loucaskreger.oxidized.config.OxidizedConfig;
import com.loucaskreger.oxidized.entity.CopperGolemEntity;
import com.loucaskreger.oxidized.registry.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;

public class UseCopperButtonGoal extends MoveToBlockGoal {

    private final CopperGolemEntity golem;

    public UseCopperButtonGoal(CopperGolemEntity entity, double speed) {
        super(entity, speed, 8);
        this.golem = entity;
    }

    @Override
    public void stop() {
        super.stop();
        this.golem.setPressingButtons(false);
    }

    @Override
    protected BlockPos getMoveToTarget() {
        return this.blockPos;
    }

    @Override
    public void tick() {
        if (this.isReachedTarget()) {
            this.golem.setPressingButtons(true);
            if (Mth.m_14072_(golem.getCommandSenderWorld().random, 1, OxidizedConfig.copperGolemButtonProbability.get()) == 2) {
                BlockState state = this.golem.level.getBlockState(this.blockPos);
                ((CopperButton) state.getBlock()).press(state, this.golem.level, this.getMoveToTarget());
            }
        }
        super.tick();
    }

    @Override
    protected boolean isValidTarget(LevelReader levelView, BlockPos pos) {
        return levelView.getBlockState(pos).is(BlockRegistry.COPPER_BUTTON.get());
    }

    @Override
    public double acceptedDistance() {
        return 0.75D;
    }
}
