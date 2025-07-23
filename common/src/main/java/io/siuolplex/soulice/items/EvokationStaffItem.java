package io.siuolplex.soulice.items;

import io.siuolplex.soulice.SoulIce;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.EvokerFangs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
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

        if (!level.isClientSide) {
            if (!player.isCrouching() || !SoulIce.IS_TEST_BUILD) {
                player.getCooldowns().addCooldown(this, 20);
                Vec3 targetPos = getTargetedBlockPos(level, player);
                double minHeight = Math.min(targetPos.y(), player.getY());
                double maxHeight = Math.max(targetPos.y(), player.getY()) + 1.0;
                float lookAngle = (float) Mth.atan2(targetPos.z() - player.getZ(), targetPos.x() - player.getX());

                if (player.distanceToSqr(targetPos) < 9.0) {
                    for (int i = 0; i < 4; i++) {
                        float g = lookAngle + (float) i * 3.1415927F * 0.5f;
                        this.createFangs(player.getX() + (double) Mth.cos(g) * 1.5, player.getZ() + (double) Mth.sin(g) * 1.5, minHeight, maxHeight, g, 0, player);
                    }

                    for (int i = 0; i < 8; i++) {
                        float g = lookAngle + (float) i * 3.1415927F * 2.0F / 4.0F + (3.1415927F / 4);
                        this.createFangs(player.getX() + (double) Mth.cos(g) * 2.5, player.getZ() + (double) Mth.sin(g) * 2.5, minHeight, maxHeight, g, 3, player);
                    }
                } else {
                    for (int i = 0; i < 8; i++) {
                        double dist = 2 * (i + 1);
                        this.createFangs(player.getX() + (double) Mth.cos(lookAngle) * dist, player.getZ() + (double) Mth.sin(lookAngle) * dist, minHeight, maxHeight, lookAngle, i, player);
                    }
                }
            } else if (player.experienceLevel > 0 || player.getAbilities().instabuild) {
                player.getCooldowns().addCooldown(this, 200);
                if (!player.getAbilities().instabuild) {
                    player.experienceLevel -= 1;
                }

                for(int spawnAmount = 0; spawnAmount < 3; ++spawnAmount) {
                    BlockPos blockpos = player.blockPosition().offset(-2 + player.getRandom().nextInt(5), 1, -2 + player.getRandom().nextInt(5));
                    Vex vex = EntityType.VEX.create(player.level());
                    if (vex != null) {
                        vex.moveTo(blockpos, 0.0F, 0.0F);
                        vex.finalizeSpawn((ServerLevelAccessor) level, player.level().getCurrentDifficultyAt(blockpos), MobSpawnType.MOB_SUMMONED, null);
                        //vex.setOwner(player);
                        vex.setBoundOrigin(blockpos);
                        vex.setLimitedLife(20 * (30 + player.getRandom().nextInt(90)));
                        if (player.getTeam() != null) {
                            level.getScoreboard().addPlayerToTeam(vex.getScoreboardName(), player.getTeam());
                        }

                        ((ServerLevel) level).addFreshEntityWithPassengers(vex);
                        level.gameEvent(GameEvent.ENTITY_PLACE, blockpos, GameEvent.Context.of(player));
                    }
                }
                createVex(player.getX(), player.getY(), player.getZ(), player);
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

    private void createFangs(double x, double z, double minHeight, double maxHeight, float yRot, int warmupDelay, Player owningPlayer) {
        BlockPos blockPos = BlockPos.containing(x, maxHeight, z);
        boolean canSpawn = false;
        double colHeight = 0.0;

        while (blockPos.getY() >= Mth.floor(minHeight) - 1) {
            BlockPos bottomPos = blockPos.below();
            BlockState bottomState = owningPlayer.level().getBlockState(bottomPos);
            if (bottomState.isFaceSturdy(owningPlayer.level(), bottomPos, Direction.UP)) {
                if (!owningPlayer.level().isEmptyBlock(blockPos)) {
                    BlockState topPos = owningPlayer.level().getBlockState(blockPos);
                    VoxelShape collision = topPos.getCollisionShape(owningPlayer.level(), blockPos);
                    if (!collision.isEmpty()) {
                        colHeight = collision.max(Direction.Axis.Y);
                    }
                }

                canSpawn = true;
                break;
            }

            blockPos = bottomPos;
        }

        if (canSpawn) {
            owningPlayer.level().addFreshEntity(new EvokerFangs(owningPlayer.level(), x, (double)blockPos.getY() + colHeight, z, yRot, warmupDelay, owningPlayer));
        }
    }

    private void createVex(double x, double y, double z, Player owningPlayer) {

    }
}
