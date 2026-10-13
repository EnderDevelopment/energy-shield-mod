package com.jesusreiner365.energyshieldmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.EntitySpawnS2CPacket;
import net.minecraft.world.World;

public class EnergyShieldEntity extends Entity {
    private LivingEntity owner;
    private int lifeTime;
    private static final int MAX_LIFETIME = 20 * 60; // 60 seconds

    public EnergyShieldEntity(EntityType<?> type, World world) {
        super(type, world);
        this.noClip = true;
        this.setNoGravity(true);
    }

    public EnergyShieldEntity(World world, LivingEntity owner) {
        this(EnergyShieldEntityType.INSTANCE, world);
        this.owner = owner;
        this.setPosition(owner.getX(), owner.getY(), owner.getZ());
        this.setYaw(owner.getYaw());
        this.setPitch(owner.getPitch());
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.getWorld().isClient) {
            if (this.owner == null || this.owner.isRemoved() || !this.owner.isAlive()) {
                this.discard();
                return;
            }

            this.setPosition(this.owner.getX(), this.owner.getY(), this.owner.getZ());
            this.setYaw(this.owner.getYaw());
            this.setPitch(this.owner.getPitch());

            this.lifeTime++;
            if (this.lifeTime >= MAX_LIFETIME) {
                this.discard();
            }
        }
    }

    @Override
    protected void initDataTracker() {
    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {
    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {
    }

    @Override
    public Packet<ClientPlayPacketListener> createSpawnPacket() {
        return new EntitySpawnS2CPacket(this);
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        return false;
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    @Override
    public boolean handleAttack(Entity attacker) {
        if (attacker instanceof LivingEntity) {
            LivingEntity livingAttacker = (LivingEntity) attacker;
            livingAttacker.setOnFireFor(5); // Set attacker on fire for 5 seconds
        }
        return true;
    }

    @Override
    public void onPlayerCollision(PlayerEntity player) {
        if (player != this.owner) {
            player.setOnFireFor(5); // Set player on fire for 5 seconds
        }
    }
}