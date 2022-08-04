package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.blockentity.CopperKilnBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.Keys.BLOCK_ENTITY_TYPES, Oxidized.MOD_ID);

    public static final RegistryObject<BlockEntityType<CopperKilnBlockEntity>> COPPER_KILN = BLOCK_ENTITIES.register("copper_kiln",
            () -> BlockEntityType.Builder.of(CopperKilnBlockEntity::new, BlockRegistry.COPPER_KILN.get()).build(null));
}
