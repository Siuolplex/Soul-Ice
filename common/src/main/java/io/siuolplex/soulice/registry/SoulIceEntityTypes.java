package io.siuolplex.soulice.registry;

import io.siuolplex.soulice.entity.GlutenBallEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.projectile.Snowball;

public class SoulIceEntityTypes<T extends Entity> {
    public static final EntityType<GlutenBallEntity> GLUTEN_BALL = register(new ResourceLocation("soul_ice", "gluten_ball"), EntityType.Builder.<GlutenBallEntity>of(GlutenBallEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).clientTrackingRange(4).updateInterval(10));

    public static <T extends Entity> EntityType<T> register(ResourceLocation identifier, EntityType.Builder<T> builder) {
        return Registry.register(BuiltInRegistries.ENTITY_TYPE, identifier, builder.build(identifier.getPath()));
    }

    public static void init() {}
}
