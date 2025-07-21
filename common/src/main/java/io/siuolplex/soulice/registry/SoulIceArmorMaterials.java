package io.siuolplex.soulice.registry;

import net.minecraft.Util;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class SoulIceArmorMaterials {
    public static final Holder<ArmorMaterial> UNHYDRATED_CACTUS_ARMOR_MATERIAL = register(
            "unhydrated_cactus",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 1);
                map.put(ArmorItem.Type.LEGGINGS, 2);
                map.put(ArmorItem.Type.CHESTPLATE, 3);
                map.put(ArmorItem.Type.HELMET, 1);
                map.put(ArmorItem.Type.BODY, 11);
        }), 6, SoundEvents.ARMOR_EQUIP_TURTLE, 0, 0, () -> Ingredient.of(Items.CACTUS), Util.make(new ArrayList<>(),
                    layers -> layers.add(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("soul_ice", "cactus")))));

    public static final Holder<ArmorMaterial> HYDRATED_CACTUS_ARMOR_MATERIAL = register(
            "unhydrated_cactus",
            Util.make(new EnumMap<>(ArmorItem.Type.class), map -> {
                map.put(ArmorItem.Type.BOOTS, 2);
                map.put(ArmorItem.Type.LEGGINGS, 4);
                map.put(ArmorItem.Type.CHESTPLATE, 6);
                map.put(ArmorItem.Type.HELMET, 2);
                map.put(ArmorItem.Type.BODY, 11);
            }), 6, SoundEvents.ARMOR_EQUIP_TURTLE, 1, 0, () -> Ingredient.of(Items.CACTUS), Util.make(new ArrayList<>(),
                    layers -> layers.add(new ArmorMaterial.Layer(ResourceLocation.fromNamespaceAndPath("soul_ice", "hydrated_cactus")))));


    private static Holder<ArmorMaterial> register(
            String name,
            EnumMap<ArmorItem.Type, Integer> defense,
            int enchantmentValue,
            Holder<SoundEvent> equipSound,
            float toughness,
            float knockbackResistance,
            Supplier<Ingredient> repairIngredient,
            List<ArmorMaterial.Layer> layers
    ) {
        EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

        for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
            enummap.put(armoritem$type, defense.get(armoritem$type));
        }

        return Registry.registerForHolder(
                BuiltInRegistries.ARMOR_MATERIAL,
                ResourceLocation.fromNamespaceAndPath("soul_ice", name),
                new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngredient, layers, toughness, knockbackResistance)
        );
    }

    public static void init() {

    }
}
