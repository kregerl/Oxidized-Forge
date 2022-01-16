package com.loucaskreger.oxidized.item;

import com.loucaskreger.oxidized.config.OxidizedConfig;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.List;

public class CopperPulsarItem extends Item {

    static final String POWERED = "Powered";

    public CopperPulsarItem(Properties properties) {
        super(properties);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide() && isActive(stack)) {
            ServerPlayer player = (ServerPlayer) entity;

            List<ItemEntity> entityItems = player.level.getEntitiesOfClass(ItemEntity.class, player.getBoundingBox().inflate(OxidizedConfig.pulsarReach.get()), Entity::isAlive);
            for (ItemEntity entityItemNearby : entityItems) {
                entityItemNearby.playerTouch(player);
            }

            List<ExperienceOrb> entityXP = player.level.getEntitiesOfClass(ExperienceOrb.class, player.getBoundingBox().inflate(OxidizedConfig.pulsarReach.get()), Entity::isAlive);
            for (ExperienceOrb entityXPNearby : entityXP) {
                entityXPNearby.playerTouch(player);
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide() && !player.isCrouching()) {
            toggleMode(stack);
            player.displayClientMessage(new TextComponent("§6Pulsar is now: " + getMagnetMode(stack)), false);
        }

        return InteractionResultHolder.success(stack);
    }

    public boolean isActive(ItemStack pulsar) {
        return getMagnetMode(pulsar).getBoolean();
    }

    private void setMagnetMode(ItemStack pulsar, MagnetMode mode) {
        checkTag(pulsar);
        pulsar.getTag().putBoolean(POWERED, mode.getBoolean());
    }

    private MagnetMode getMagnetMode(ItemStack pulsar) {
        if (!pulsar.isEmpty()) {
            checkTag(pulsar);
            return pulsar.getTag().getBoolean(POWERED) ? MagnetMode.ACTIVE : MagnetMode.INACTIVE;
        }
        return MagnetMode.INACTIVE;
    }

    private void toggleMode(ItemStack magnet) {
        MagnetMode currentMode = getMagnetMode(magnet);

        if (currentMode.getBoolean()) {
            setMagnetMode(magnet, MagnetMode.INACTIVE);

            return;
        }

        setMagnetMode(magnet, MagnetMode.ACTIVE);
    }

    private void checkTag(ItemStack pulsar) {
        if (!pulsar.isEmpty()) {
            if (!pulsar.hasTag()) {
                pulsar.setTag(new CompoundTag());
            }
            CompoundTag nbt = pulsar.getTag();

            if (!nbt.contains(POWERED)) {
                nbt.putBoolean(POWERED, false);
            }
        }
    }


    public enum MagnetMode {
        ACTIVE(true), INACTIVE(false);

        final boolean state;

        MagnetMode(boolean state) {
            this.state = state;
        }

        public boolean getBoolean() {
            return state;
        }
    }
}
