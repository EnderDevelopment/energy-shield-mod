package com.jesusreiner365.energyshieldmod.client.model;

import com.jesusreiner365.energyshieldmod.entity.EnergyShieldEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.util.math.MatrixStack;

public class EnergyShieldModel extends EntityModel<EnergyShieldEntity> {
    private final ModelPart shield;

    public EnergyShieldModel(ModelPart root) {
        this.shield = root.getChild("shield");
    }

    @Override
    public void setAngles(EnergyShieldEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        // Set the angles of the model parts based on the entity's state
    }

    @Override
    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        shield.render(matrices, vertices, light, overlay, red, green, blue, alpha);
    }
}