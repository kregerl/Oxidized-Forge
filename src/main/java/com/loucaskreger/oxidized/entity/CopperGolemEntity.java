package com.loucaskreger.oxidized.entity;

import com.loucaskreger.oxidized.entity.goal.MoveToLightningRodGoal;
import com.loucaskreger.oxidized.entity.goal.UseCopperButtonGoal;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeMap;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.AbstractGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.WeatheringCopper;
import org.jetbrains.annotations.Nullable;

public class CopperGolemEntity extends AbstractGolem {

    private static final EntityDataAccessor<Boolean> IS_PRESSING_BUTTONS = SynchedEntityData.defineId(CopperGolemEntity.class, EntityDataSerializers.BOOLEAN);
    private WeatheringCopper.WeatherState oxidizationState;
    private int oxidizationTick;

    public CopperGolemEntity(EntityType<CopperGolemEntity> entityType, Level level) {
        super(entityType, level);
        this.oxidizationState = null;
        this.oxidizationTick = 0;
    }

    @Override
    public AttributeMap getAttributes() {
        return new AttributeMap(createCopperGolemAttributes().build());
    }

    public static AttributeSupplier.Builder createCopperGolemAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 15.0D).add(Attributes.MOVEMENT_SPEED, 0.27D).add(Attributes.KNOCKBACK_RESISTANCE, 0.75D);
    }

    @Override
    protected int decreaseAirSupply(int air) {
        return air;
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new MoveToLightningRodGoal(this, 0.6D));
        this.goalSelector.addGoal(2, new UseCopperButtonGoal(this, 0.8D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 0.6F));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }


    public void setPressingButtons(boolean pressingButtons) {
        this.entityData.set(IS_PRESSING_BUTTONS, pressingButtons);
    }


    public boolean isPressingButtons() {
        return this.entityData.get(IS_PRESSING_BUTTONS);
    }

    public WeatheringCopper.WeatherState getOxidizationState() {
        return oxidizationState;
    }

    public boolean isStatue() {
        return oxidizationState.equals(WeatheringCopper.WeatherState.OXIDIZED);
    }


    @Override
    public void tick() {
        super.tick();
        this.oxidizationTick();
        if (this.oxidizationState != WeatheringCopper.WeatherState.OXIDIZED) {
            this.oxidizationTick++;
        }
        this.setNoAi(this.isStatue());
    }

    protected void oxidizationTick() {
        if (oxidizationTick == 1200000) {
            this.oxidizationState = WeatheringCopper.WeatherState.OXIDIZED;
        } else if (oxidizationTick == 800000) {
            this.oxidizationState = WeatheringCopper.WeatherState.WEATHERED;
        } else if (oxidizationTick == 400000) {
            this.oxidizationState = WeatheringCopper.WeatherState.EXPOSED;
        } else if (this.oxidizationTick < 400000) {
            this.oxidizationState = WeatheringCopper.WeatherState.UNAFFECTED;
        }
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(IS_PRESSING_BUTTONS, false);
    }


    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() instanceof AxeItem && this.oxidizationState != WeatheringCopper.WeatherState.UNAFFECTED) {
            this.degradeLevel();
            this.level.playSound(player, player.getOnPos(), SoundEvents.AXE_SCRAPE, SoundSource.BLOCKS, 1.0F, 1.0F);
            this.level.levelEvent(player, 3005, player.getOnPos(), 0);
            stack.hurt(1, this.random, (ServerPlayer) player);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }


    public void degradeLevel() {
        if (this.oxidizationState == WeatheringCopper.WeatherState.OXIDIZED) {
            this.oxidizationState = WeatheringCopper.WeatherState.WEATHERED;
        } else if (this.oxidizationState == WeatheringCopper.WeatherState.WEATHERED) {
            this.oxidizationState = WeatheringCopper.WeatherState.EXPOSED;
        } else if (this.oxidizationState == WeatheringCopper.WeatherState.EXPOSED) {
            this.oxidizationState = WeatheringCopper.WeatherState.UNAFFECTED;
        }
    }

    @Override
    public void thunderHit(ServerLevel level, LightningBolt bolt) {
        super.thunderHit(level, bolt);
        this.oxidizationTick = 0;
        this.oxidizationState = WeatheringCopper.WeatherState.UNAFFECTED;
        ParticleUtils.spawnParticlesAlongAxis(this.getMotionDirection().getAxis(), level, this.getOnPos(), 0.125D, ParticleTypes.ELECTRIC_SPARK, UniformInt.of(1, 2));
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        boolean bl = super.hurt(source, damage);
        if (source.getEntity() instanceof LightningBolt) {
            return false;
        } else
            return bl;
    }

    @Override
    public void push(Entity entity) {
        if (entity instanceof Player) {
            super.push(entity);
        }
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.IRON_GOLEM_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.IRON_GOLEM_DEATH;
    }

}
