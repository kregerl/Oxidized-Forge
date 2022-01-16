package com.loucaskreger.oxidized.util;

import com.loucaskreger.oxidized.registry.BlockRegistry;
import net.minecraft.world.level.block.Block;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CopperStateMap {

    private static final Map<Block, Block> INCREASES = new HashMap<>();

    private static final Map<Block, Block> DECREASES = new HashMap<>();

    private static final Map<Block, Block> WAXED = new HashMap<>();

    private static final Map<Block, Block> UNWAXED = new HashMap<>();

    public static void put(Block block, @Nullable Block oxidized, Block waxed) {
        if (oxidized != null) {
            INCREASES.put(block, oxidized);
            DECREASES.put(oxidized, block);
        }
        WAXED.put(block, waxed);
        UNWAXED.put(waxed, block);
    }

    public static Optional<Block> getIncrease(Block block) {
        return Optional.ofNullable(INCREASES.get(block));
    }

    public static Optional<Block> getDecrease(Block block) {
        return Optional.ofNullable(DECREASES.get(block));
    }

    public static Optional<Block> getWaxed(Block block) {
        return Optional.ofNullable(WAXED.get(block));
    }

    public static Optional<Block> getUnwaxed(Block block) {
        return Optional.ofNullable(UNWAXED.get(block));
    }

    public static Block getOriginalStage(Block block) {
        var result = block;
        for (var b = getDecrease(block); b.isPresent(); b = getDecrease(b.get())) {
            result = b.get();
        }
        return result;
    }

    static {
        put(BlockRegistry.VERTICAL_CUT_COPPER.get(), BlockRegistry.VERTICAL_EXPOSED_CUT_COPPER.get(), BlockRegistry.WAXED_VERTICAL_CUT_COPPER.get());
        put(BlockRegistry.VERTICAL_EXPOSED_CUT_COPPER.get(), BlockRegistry.VERTICAL_WEATHERED_CUT_COPPER.get(), BlockRegistry.WAXED_VERTICAL_EXPOSED_CUT_COPPER.get());
        put(BlockRegistry.VERTICAL_WEATHERED_CUT_COPPER.get(), BlockRegistry.VERTICAL_OXIDIZED_CUT_COPPER.get(), BlockRegistry.WAXED_VERTICAL_WEATHERED_CUT_COPPER.get());
        put(BlockRegistry.VERTICAL_OXIDIZED_CUT_COPPER.get(), null, BlockRegistry.WAXED_VERTICAL_OXIDIZED_CUT_COPPER.get());
    }
}
