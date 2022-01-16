package com.loucaskreger.oxidized.block;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.level.block.ButtonBlock;

public class CopperButton extends ButtonBlock {

    public CopperButton(Properties properties) {
        super(false, properties);
    }

    @Override
    protected SoundEvent getSound(boolean powered) {
        return powered ? SoundEvents.STONE_BUTTON_CLICK_ON : SoundEvents.STONE_BUTTON_CLICK_OFF;
    }
}
