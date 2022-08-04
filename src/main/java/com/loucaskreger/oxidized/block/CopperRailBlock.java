package com.loucaskreger.oxidized.block;

import com.loucaskreger.oxidized.config.OxidizedConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.PoweredRailBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.*;

public class CopperRailBlock extends PoweredRailBlock {

    public CopperRailBlock(Properties properties) {
        super(properties);
    }

    @Override
    public boolean isActivatorRail() {
        return false;
    }

    @Override
    protected boolean findPoweredRailSignal(Level level, BlockPos pos, BlockState state, boolean bl, int distance) {
        if (distance >= OxidizedConfig.copperRailRange.get()) {
            return false;
        } else {
            int i = pos.getX();
            int j = pos.getY();
            int k = pos.getZ();
            boolean flag = true;
            RailShape railshape = state.getValue(SHAPE);
            switch(railshape) {
                case NORTH_SOUTH:
                    if (bl) {
                        ++k;
                    } else {
                        --k;
                    }
                    break;
                case EAST_WEST:
                    if (bl) {
                        --i;
                    } else {
                        ++i;
                    }
                    break;
                case ASCENDING_EAST:
                    if (bl) {
                        --i;
                    } else {
                        ++i;
                        ++j;
                        flag = false;
                    }

                    railshape = RailShape.EAST_WEST;
                    break;
                case ASCENDING_WEST:
                    if (bl) {
                        --i;
                        ++j;
                        flag = false;
                    } else {
                        ++i;
                    }

                    railshape = RailShape.EAST_WEST;
                    break;
                case ASCENDING_NORTH:
                    if (bl) {
                        ++k;
                    } else {
                        --k;
                        ++j;
                        flag = false;
                    }

                    railshape = RailShape.NORTH_SOUTH;
                    break;
                case ASCENDING_SOUTH:
                    if (bl) {
                        ++k;
                        ++j;
                        flag = false;
                    } else {
                        --k;
                    }

                    railshape = RailShape.NORTH_SOUTH;
            }

            if (this.isSameRailWithPower(level, new BlockPos(i, j, k), bl, distance, railshape)) {
                return true;
            } else {
                return flag && this.isSameRailWithPower(level, new BlockPos(i, j - 1, k), bl, distance, railshape);
            }
        }
    }
}
