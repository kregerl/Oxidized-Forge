package com.loucaskreger.oxidized.entity.goal;

import com.loucaskreger.oxidized.entity.CopperGolemEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;

public class MoveToLightningRodGoal extends MoveToBlockGoal {

    private final CopperGolemEntity golem;

    public MoveToLightningRodGoal(CopperGolemEntity entity, double speed) {
        super(entity, speed, 10);
        this.golem = entity;
    }

    @Override
    public boolean canUse() {
        return this.golem.level.isThundering() && super.canUse();
    }

    @Override
    protected boolean isValidTarget(LevelReader levelView, BlockPos pos) {
        return levelView.getBlockState(pos).is(Blocks.LIGHTNING_ROD);
    }
}
