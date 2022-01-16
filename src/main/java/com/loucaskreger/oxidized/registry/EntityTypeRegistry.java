package com.loucaskreger.oxidized.registry;

import com.loucaskreger.oxidized.Oxidized;
import com.loucaskreger.oxidized.entity.CopperGolemEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityTypeRegistry {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, Oxidized.MOD_ID);

    public static final RegistryObject<EntityType<CopperGolemEntity>> COPPER_GOLEM = ENTITY_TYPES.register("copper_golem",
            () -> EntityType.Builder.<CopperGolemEntity>of(CopperGolemEntity::new, MobCategory.MISC).fireImmune().setTrackingRange(8).sized(0.5F, 0.9F).build(new ResourceLocation(Oxidized.MOD_ID, "copper_golem").toString()));
}
