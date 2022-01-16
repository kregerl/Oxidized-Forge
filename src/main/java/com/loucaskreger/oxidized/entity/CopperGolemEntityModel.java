package com.loucaskreger.oxidized.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.util.Mth;

public class CopperGolemEntityModel extends EntityModel<CopperGolemEntity> {

    private final ModelPart right_leg;
    private final ModelPart left_leg;
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart left_arm;
    private final ModelPart right_arm;

    public CopperGolemEntityModel(ModelPart root) {
        this.right_leg = root.getChild("right_leg");
        this.left_leg = root.getChild("left_leg");
        this.body = root.getChild("body");
        this.left_arm = root.getChild("left_arm");
        this.right_arm = root.getChild("right_arm");
        this.head = root.getChild("head");
    }

    public static LayerDefinition getTexturedModelData() {
        MeshDefinition meshDefinition = new MeshDefinition();
        PartDefinition partDefinition = meshDefinition.getRoot();
        partDefinition.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(16, 0).addBox(-2.2F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(8, 15).addBox(0.2F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 8).addBox(-3.0F, -6.0F, -1.5F, 6.0F, 4.0F, 3.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(16, 15).addBox(3.0F, -6.0F, -0.5F, 1.0F, 5.0F, 1.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(18, 8).addBox(-4.0F, -6.0F, -0.5F, 1.0F, 5.0F, 1.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
        partDefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -10.0F, -2.0F, 6.0F, 4.0F, 4.0F).texOffs(0, 0).addBox(-0.5F, -7.6F,
                -3.0F, 1.0F, 2.0F, 1.0F).texOffs(6, 15).addBox(-0.5F, -11.0F, -0.5F, 1.0F, 1.0F, 1.0F).texOffs(0, 15).addBox(-1.0F, -13.0F,
                -1.0F, 2.0F, 2.0F, 2.0F), PartPose.offset(0.0F, 24.0F, 0.0F));
        return LayerDefinition.create(meshDefinition, 32, 32);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        left_leg.render(poseStack, buffer, packedLight, packedOverlay);
        right_leg.render(poseStack, buffer, packedLight, packedOverlay);
        body.render(poseStack, buffer, packedLight, packedOverlay);
        left_arm.render(poseStack, buffer, packedLight, packedOverlay);
        right_arm.render(poseStack, buffer, packedLight, packedOverlay);
        head.render(poseStack, buffer, packedLight, packedOverlay);
    }

    @Override
    public void setupAnim(CopperGolemEntity entity, float f, float g, float h, float i, float j) {
        this.right_leg.xRot = -1.5F * Mth.triangleWave(f, 13.0F) * g;
        this.left_leg.xRot = 1.5F * Mth.triangleWave(f, 13.0F) * g;
        this.right_leg.yRot = 0.0F;
        this.left_leg.yRot = 0.0F;
        if (entity.isPressingButtons()) {
            this.right_arm.z = -0.5F;
            this.left_arm.z = -0.5F;
            this.right_arm.xRot = 6.1F;
            this.left_arm.xRot = 6.1F;
        } else {
            this.right_arm.z = 0F;
            this.left_arm.z = 0F;
            this.right_arm.xRot = 0F;
            this.left_arm.xRot = 0F;
        }
    }


}
