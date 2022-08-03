package com.loucaskreger.oxidized;

import com.loucaskreger.oxidized.config.OxidizedConfig;
import com.loucaskreger.oxidized.entity.CopperGolemEntity;
import com.loucaskreger.oxidized.entity.CopperGolemEntityModel;
import com.loucaskreger.oxidized.entity.CopperGolemEntityRenderer;
import com.loucaskreger.oxidized.registry.*;
import com.loucaskreger.oxidized.screen.CopperKilnScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.Arrays;

import static com.loucaskreger.oxidized.entity.CopperGolemEntityModel.COPPER_GOLEM_LAYER;


@Mod(Oxidized.MOD_ID)
public class Oxidized {

    public static final String MOD_ID = "oxidized";
    public static final CreativeModeTab OXIDIZED_TAB = new CreativeModeTab("oxidized.item_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(Items.COPPER_INGOT);
        }
    };

    public Oxidized() {
        var bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setupCommon);
        bus.addListener(this::registerEntity);
        bus.addListener(this::registerEntityModel);
        bus.addListener(this::setupClient);
        BlockRegistry.BLOCKS.register(bus);
        ItemRegistry.ITEMS.register(bus);
        EntityTypeRegistry.ENTITY_TYPES.register(bus);
        BlockEntityRegistry.BLOCK_ENTITIES.register(bus);
        ContainerRegistry.CONTAINERS.register(bus);
        RecipeRegistry.RECIPE_SERIALIZERS.register(bus);
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, OxidizedConfig.SPEC, "oxidized-common.toml");
    }


    public void setupCommon(final FMLCommonSetupEvent event) {
        TierSortingRegistry.registerTier(ItemRegistry.ROSE_GOLD, new ResourceLocation("rose_gold_tier"), Arrays.asList(Tiers.WOOD, Tiers.STONE, Tiers.IRON, Tiers.GOLD), Arrays.asList(Tiers.DIAMOND, Tiers.NETHERITE));
        event.enqueueWork(CopperGolemEntity::createCopperGolemAttributes);
    }


    public void setupClient(final FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COPPER_RAIL.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COPPER_KILN.get(), RenderType.cutoutMipped());
        ItemBlockRenderTypes.setRenderLayer(BlockRegistry.COPPER_LANTERN.get(), RenderType.cutoutMipped());
        MenuScreens.register(ContainerRegistry.COPPER_KILN.get(), CopperKilnScreen::new);
    }

    public void registerEntity(final EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(EntityTypeRegistry.COPPER_GOLEM.get(), CopperGolemEntityRenderer::new);
    }

    public void registerEntityModel(final EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(COPPER_GOLEM_LAYER, CopperGolemEntityModel::getTexturedModelData);
    }

    public void registerRecipeSerializers(final RegistryEvent.Register<RecipeSerializer<?>> event) {
        Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Oxidized.MOD_ID, "kiln_smelting"), RecipeRegistry.COPPER_KILN_RECIPE_TYPE);
    }

}
