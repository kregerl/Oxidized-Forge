package com.loucaskreger.oxidized.entity;

import com.loucaskreger.oxidized.Oxidized;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.WeatheringCopper;

import static com.loucaskreger.oxidized.entity.CopperGolemEntityModel.COPPER_GOLEM_LAYER;

public class CopperGolemEntityRenderer extends MobRenderer<CopperGolemEntity, CopperGolemEntityModel> {
    private static final ResourceLocation UNAFFECTED = new ResourceLocation("oxidized", "textures/entity/copper_golem.png");
    private static final ResourceLocation EXPOSED = new ResourceLocation("oxidized", "textures/entity/exposed_copper_golem.png");
    private static final ResourceLocation WEATHERED = new ResourceLocation("oxidized", "textures/entity/weathered_copper_golem.png");
    private static final ResourceLocation OXIDIZED = new ResourceLocation("oxidized", "textures/entity/oxidized_copper_golem.png");


    public CopperGolemEntityRenderer(EntityRendererProvider.Context context) {
        super(context, new CopperGolemEntityModel(context.getModelSet().bakeLayer(COPPER_GOLEM_LAYER)), 0.25F);
    }

    @Override
    public ResourceLocation getTextureLocation(CopperGolemEntity entity) {
        if (entity.getOxidizationState() == WeatheringCopper.WeatherState.EXPOSED) {
            return EXPOSED;
        } else if (entity.getOxidizationState() == WeatheringCopper.WeatherState.WEATHERED) {
            return WEATHERED;
        } else if (entity.getOxidizationState() == WeatheringCopper.WeatherState.OXIDIZED) {
            return OXIDIZED;
        } else {
            return UNAFFECTED;
        }
    }
}
