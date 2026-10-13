package com.jesusreiner365.energyshieldmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EnergyShieldEntityType {
    public static final EntityType<EnergyShieldEntity> INSTANCE = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("energyshieldmod", "energy_shield"),
            EntityType.Builder.<EnergyShieldEntity>create(EnergyShieldEntity::new, SpawnGroup.MISC)
                    .setDimensions(4.0f, 4.0f)
                    .maxTrackingRange(10)
                    .trackingTickInterval(20)
                    .build("energy_shield")
    );
}