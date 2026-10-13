package com.jesusreiner365.energyshieldmod.client;

import com.jesusreiner365.energyshieldmod.entity.EnergyShieldEntityType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class EnergyShieldClientMod implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.register(EnergyShieldEntityType.INSTANCE, EnergyShieldRenderer::new);
    }
}