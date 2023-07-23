package io.siuolplex.soulice.items;

import io.siuolplex.soulice.SoulIce;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.entity.projectile.ThrownEnderpearl;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;

public class EvokationStaffItem extends Item {
    public EvokationStaffItem(Properties properties) {
        super(properties);
    }

    // Based on the Evoker summoning code
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        ItemStack playerItemInHand = player.getItemInHand(interactionHand);
        player.getCooldowns().addCooldown(this, 30);

        if (!level.isClientSide) {
            Vec3 targetPos = getTargetedBlockPos(level, player);
            double d = Math.min(targetPos.y(), player.getY());
            double e = Math.max(targetPos.y(), player.getY()) + 1.0;
            float f = (float) Mth.atan2(targetPos.z() - player.getZ(), targetPos.x() - player.getX());

            if (player.distanceToSqr(targetPos) < 9.0) {
                for(int i = 0; i < 4; i++) {
                    float g = f + (float)i * 3.1415927F * 0.5f;
                    this.createSpellEntity(player.getX() + (double)Mth.cos(g) * 1.5, player.getZ() + (double)Mth.sin(g) * 1.5, d, e, g, 0, player);
                }

                for(int i = 0; i < 8; i++) {
                    float g = f + (float)i * 3.1415927F * 2.0F / 4.0F + (3.1415927F/4);
                    this.createSpellEntity(player.getX() + (double)Mth.cos(g) * 2.5, player.getZ() + (double)Mth.sin(g) * 2.5, d, e, g, 3, player);
                }
            } else {
                for(int i = 0; i < 12; i++) {
                    double h = 1.25 * (double)(i + 3);
                    this.createSpellEntity(player.getX() + (double)Mth.cos(f) * h, player.getZ() + (double)Mth.sin(f) * h, d, e, f, i, player);
                }
            }
        }

        return InteractionResultHolder.sidedSuccess(playerItemInHand, level.isClientSide());
    }

    // playerRotationVector is flipped what the fuck
    public static Vec3 getTargetedBlockPos(Level level, Player player) {
        double rotX = Math.sin(Math.toRadians(player.getRotationVector().y));
        double rotY = Math.sin(Math.toRadians(player.getRotationVector().x));
        double rotZ = Math.cos(Math.toRadians(player.getRotationVector().y));
        BlockHitResult blockPos = level.clip(new ClipContext(player.getEyePosition(),
                new Vec3(
                        Math.abs(player.getEyePosition().x)/(player.getEyePosition().x)*Math.sqrt((player.getEyePosition().x - (5 * rotX)) * (player.getEyePosition().x - (5 * rotX))),
                        Math.abs(player.getEyePosition().y)/(player.getEyePosition().y)*Math.sqrt((player.getEyePosition().y - (5 * rotY)) * (player.getEyePosition().y - (5 * rotY))),
                        Math.abs(player.getEyePosition().z)/(player.getEyePosition().z)*Math.sqrt((player.getEyePosition().z + (5 * rotZ)) * (player.getEyePosition().z + (5 * rotZ)))),
                        ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, player));

        return blockPos.getLocation();
    }

    private void createSpellEntity(double x, double z, double f, double g, float h, int i, Player player) {
        BlockPos blockPos = BlockPos.containing(x, g, z);
        boolean bl = false;
        double j = 0.0;

        do {
            BlockPos blockPos2 = blockPos.below();
            BlockState blockState = player.level().getBlockState(blockPos2);
            if (blockState.isFaceSturdy(player.level(), blockPos2, Direction.UP)) {
                if (!player.level().isEmptyBlock(blockPos)) {
                    BlockState blockState2 = player.level().getBlockState(blockPos);
                    VoxelShape voxelShape = blockState2.getCollisionShape(player.level(), blockPos);
                    if (!voxelShape.isEmpty()) {
                        j = voxelShape.max(Direction.Axis.Y);
                    }
                }

                bl = true;
                break;
            }

            blockPos = blockPos2;
        } while(blockPos.getY() >= Mth.floor(f) - 1);

        if (bl) {
            player.level().addFreshEntity(new EvokerFangs(player.level(), x, (double)blockPos.getY() + j, z, h, i, player));
        }
    }
}
