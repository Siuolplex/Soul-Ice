package io.siuolplex.soulice.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;
import java.util.UUID;

public class PersonalVex extends TamableAnimal implements TraceableEntity {
    public static final float FLAP_DEGREES_PER_TICK = 45.836624F;
    public static final int TICKS_PER_FLAP = Mth.ceil(3.9269907F);
    protected static final EntityDataAccessor<Byte> DATA_FLAGS_ID;
    private static final int FLAG_IS_CHARGING = 1;
    @Nullable
    Player owner;
    @Nullable
    private BlockPos boundOrigin;
    private boolean hasLimitedLife;
    private int limitedLifeTicks;

    public PersonalVex(EntityType<? extends PersonalVex> entityType, Level level) {
        super(entityType, level);
        this.moveControl = new PersonalVexMoveControl(this);
        this.xpReward = 3;
    }

    public boolean isFlapping() {
        return this.tickCount % TICKS_PER_FLAP == 0;
    }

    public void move(MoverType type, Vec3 pos) {
        super.move(type, pos);
        this.checkInsideBlocks();
    }

    public void tick() {
        this.noPhysics = true;
        super.tick();
        this.noPhysics = false;
        this.setNoGravity(true);
        if (this.hasLimitedLife && --this.limitedLifeTicks <= 0) {
            this.limitedLifeTicks = 20;
            this.hurt(this.damageSources().starve(), 1.0F);
        }
    }

    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(4, new PersonalVex.VexChargeAttackGoal());
        this.goalSelector.addGoal(8, new PersonalVexRandomMoveGoal());
        this.goalSelector.addGoal(9, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, new HurtByTargetGoal(this).setAlertOthers());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Monster.createMonsterAttributes().add(Attributes.MAX_HEALTH, 14.0F).add(Attributes.ATTACK_DAMAGE, 4.0F);
    }

    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_FLAGS_ID, (byte)0);
    }

    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("BoundX")) {
            this.boundOrigin = new BlockPos(compound.getInt("BoundX"), compound.getInt("BoundY"), compound.getInt("BoundZ"));
        }

        if (compound.contains("LifeTicks")) {
            this.setLimitedLife(compound.getInt("LifeTicks"));
        }

    }

    @Override
    public boolean isFood(@NotNull ItemStack itemStack) {
        return false; // Lmfao L you dont get PersonalVex
    }

    public void restoreFrom(@NotNull Entity entity) {
        super.restoreFrom(entity);
        if (entity instanceof PersonalVex personalVex) {
            this.owner = personalVex.getOwner();
        }

    }

    public void addAdditionalSaveData(@NotNull CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        if (this.boundOrigin != null) {
            tag.putInt("BoundX", this.boundOrigin.getX());
            tag.putInt("BoundY", this.boundOrigin.getY());
            tag.putInt("BoundZ", this.boundOrigin.getZ());
        }

        if (this.hasLimitedLife) {
            tag.putInt("LifeTicks", this.limitedLifeTicks);
        }

    }

    @Override
    public @Nullable UUID getOwnerUUID() {
        return owner.getUUID();
    }

    @Nullable
    public Player getOwner() {
        return this.owner;
    }

    @Nullable
    public BlockPos getBoundOrigin() {
        return this.boundOrigin;
    }

    public void setBoundOrigin(@Nullable BlockPos boundOrigin) {
        this.boundOrigin = boundOrigin;
    }

    private boolean getVexFlag(int mask) {
        int i = this.entityData.get(DATA_FLAGS_ID);
        return (i & mask) != 0;
    }

    private void setVexFlag(int mask, boolean value) {
        int i = this.entityData.get(DATA_FLAGS_ID);
        if (value) {
            i |= mask;
        } else {
            i &= ~mask;
        }

        this.entityData.set(DATA_FLAGS_ID, (byte)(i & 255));
    }

    public boolean isCharging() {
        return this.getVexFlag(1);
    }

    public void setIsCharging(boolean charging) {
        this.setVexFlag(1, charging);
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public void setLimitedLife(int limitedLifeTicks) {
        this.hasLimitedLife = true;
        this.limitedLifeTicks = limitedLifeTicks;
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.VEX_AMBIENT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.VEX_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSource) {
        return SoundEvents.VEX_HURT;
    }

    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        RandomSource randomsource = level.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, difficulty);
        this.populateDefaultEquipmentEnchantments(level, randomsource, difficulty);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public @org.jetbrains.annotations.Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }

    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance difficulty) {
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(Items.IRON_SWORD));
        this.setDropChance(EquipmentSlot.MAINHAND, 0.0F);
    }

    static {
        DATA_FLAGS_ID = SynchedEntityData.defineId(PersonalVex.class, EntityDataSerializers.BYTE);
    }

    class VexChargeAttackGoal extends Goal {
        public VexChargeAttackGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            LivingEntity livingentity = PersonalVex.this.getTarget();
            return livingentity != null && livingentity.isAlive() && !PersonalVex.this.getMoveControl().hasWanted() && PersonalVex.this.random.nextInt(reducedTickDelay(7)) == 0 && PersonalVex.this.distanceToSqr(livingentity) > (double) 4.0F;
        }

        public boolean canContinueToUse() {
            return PersonalVex.this.getMoveControl().hasWanted() && PersonalVex.this.isCharging() && PersonalVex.this.getTarget() != null && PersonalVex.this.getTarget().isAlive();
        }

        public void start() {
            LivingEntity livingentity = PersonalVex.this.getTarget();
            if (livingentity != null) {
                Vec3 vec3 = livingentity.getEyePosition();
                PersonalVex.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0F);
            }

            PersonalVex.this.setIsCharging(true);
            PersonalVex.this.playSound(SoundEvents.VEX_CHARGE, 1.0F, 1.0F);
        }

        public void stop() {
            PersonalVex.this.setIsCharging(false);
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        public void tick() {
            LivingEntity livingentity = PersonalVex.this.getTarget();
            if (livingentity != null) {
                if (PersonalVex.this.getBoundingBox().intersects(livingentity.getBoundingBox())) {
                    PersonalVex.this.doHurtTarget(livingentity);
                    PersonalVex.this.setIsCharging(false);
                } else {
                    double d0 = PersonalVex.this.distanceToSqr(livingentity);
                    if (d0 < (double)9.0F) {
                        Vec3 vec3 = livingentity.getEyePosition();
                        PersonalVex.this.moveControl.setWantedPosition(vec3.x, vec3.y, vec3.z, 1.0F);
                    }
                }
            }

        }
    }

    class PersonalVexMoveControl extends MoveControl {
        public PersonalVexMoveControl(PersonalVex personalVex) {
            super(personalVex);
        }

        public void tick() {
            if (this.operation == Operation.MOVE_TO) {
                Vec3 vec3 = new Vec3(this.wantedX - PersonalVex.this.getX(), this.wantedY - PersonalVex.this.getY(), this.wantedZ - PersonalVex.this.getZ());
                double d0 = vec3.length();
                if (d0 < PersonalVex.this.getBoundingBox().getSize()) {
                    this.operation = Operation.WAIT;
                    PersonalVex.this.setDeltaMovement(PersonalVex.this.getDeltaMovement().scale(0.5F));
                } else {
                    PersonalVex.this.setDeltaMovement(PersonalVex.this.getDeltaMovement().add(vec3.scale(this.speedModifier * 0.05 / d0)));
                    if (PersonalVex.this.getTarget() == null) {
                        Vec3 vec31 = PersonalVex.this.getDeltaMovement();
                        PersonalVex.this.setYRot(-((float)Mth.atan2(vec31.x, vec31.z)) * (180F / (float)Math.PI));
                        PersonalVex.this.yBodyRot = PersonalVex.this.getYRot();
                    } else {
                        double d2 = PersonalVex.this.getTarget().getX() - PersonalVex.this.getX();
                        double d1 = PersonalVex.this.getTarget().getZ() - PersonalVex.this.getZ();
                        PersonalVex.this.setYRot(-((float)Mth.atan2(d2, d1)) * (180F / (float)Math.PI));
                        PersonalVex.this.yBodyRot = PersonalVex.this.getYRot();
                    }
                }
            }

        }
    }

    class PersonalVexRandomMoveGoal extends Goal {
        public PersonalVexRandomMoveGoal() {
            this.setFlags(EnumSet.of(Flag.MOVE));
        }

        public boolean canUse() {
            return !PersonalVex.this.getMoveControl().hasWanted() && PersonalVex.this.random.nextInt(reducedTickDelay(7)) == 0;
        }

        public boolean canContinueToUse() {
            return false;
        }

        public void tick() {
            BlockPos blockpos = PersonalVex.this.getBoundOrigin();
            if (blockpos == null) {
                blockpos = PersonalVex.this.blockPosition();
            }

            for(int i = 0; i < 3; ++i) {
                BlockPos blockpos1 = blockpos.offset(PersonalVex.this.random.nextInt(15) - 7, PersonalVex.this.random.nextInt(11) - 5, PersonalVex.this.random.nextInt(15) - 7);
                if (PersonalVex.this.level().isEmptyBlock(blockpos1)) {
                    PersonalVex.this.moveControl.setWantedPosition((double)blockpos1.getX() + (double)0.5F, (double)blockpos1.getY() + (double)0.5F, (double)blockpos1.getZ() + (double)0.5F, 0.25F);
                    if (PersonalVex.this.getTarget() == null) {
                        PersonalVex.this.getLookControl().setLookAt((double)blockpos1.getX() + (double)0.5F, (double)blockpos1.getY() + (double)0.5F, (double)blockpos1.getZ() + (double)0.5F, 180.0F, 20.0F);
                    }
                    break;
                }
            }

        }
    }
}
