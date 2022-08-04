package com.loucaskreger.oxidized;

import com.loucaskreger.oxidized.registry.EntityTypeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WeatheringCopper;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod.EventBusSubscriber(modid = Oxidized.MOD_ID)
public class EventSubscriber {

    private static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public static void onPlayerInteract(final PlayerInteractEvent.RightClickBlock event) {
        var level = event.getLevel();
        if (!level.isClientSide()) {
            if (event.getItemStack().getItem() == Items.LIGHTNING_ROD) {
                var blockPos = event.getPos();
                if (level.getBlockState(blockPos).getBlock() == Blocks.CARVED_PUMPKIN && isValidCopperBlock(level, blockPos.below())) {
                    var copperGolem = EntityTypeRegistry.COPPER_GOLEM.get().create(level);
                    if (copperGolem != null) {
                        copperGolem.moveTo((double) blockPos.getX() + 0.5D, (double) blockPos.getY() + 0.05D, (double) blockPos.getZ() + 0.5D, 0.0F, 0.0F);
                        level.setBlockAndUpdate(blockPos, Blocks.AIR.defaultBlockState());
                        level.setBlockAndUpdate(blockPos.below(), Blocks.AIR.defaultBlockState());
                        level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, Blocks.COPPER_BLOCK.defaultBlockState()), blockPos.getX() + ((double) level.random.nextFloat() - 0.5D) * (double) 1.4F, blockPos.getY() + 0.1D, blockPos.getZ() + ((double) level.random.nextFloat() - 0.5D) * (double) 1.4F, 4.0D * ((double) level.random.nextFloat() - 0.5D), 0.5D, ((double) level.random.nextFloat() - 0.5D) * 4.0D);
                        level.addFreshEntity(copperGolem);
                    } else {
                        LOGGER.warn("Cannot summon copper golem, was null.");
                    }
                }
            }
        }
    }

    public static boolean isValidCopperBlock(Level level, BlockPos pos) {
        var block = level.getBlockState(pos).getBlock();
        if (block instanceof WeatheringCopper copperBlock) {
            return copperBlock.getAge() == WeatheringCopper.WeatherState.UNAFFECTED;
        }
        return false;
    }
}
