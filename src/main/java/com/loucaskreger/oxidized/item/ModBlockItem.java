package com.loucaskreger.oxidized.item;

import com.loucaskreger.oxidized.Oxidized;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.level.block.Block;

public class ModBlockItem extends BlockItem {

    public ModBlockItem(Block block, Properties properties) {
        super(block, properties.tab(Oxidized.OXIDIZED_TAB));
    }
}
