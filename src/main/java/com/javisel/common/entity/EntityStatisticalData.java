package com.javisel.common.entity;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;

import java.util.List;
import java.util.UUID;

public record EntityStatisticalData(
        OffenseStats offenseStats,
        DefenseStats defenseStats,
        double movement_speed,
        double movement_speed_scaling,
        double experience,
        double experience_scaling,
        List<String> entity_traits
) {
    public static final UUID BASE_STAT_ID = UUID.fromString("c05b0654-01f1-43a4-a3a5-47b6fc08a479");
    public static final UUID LEVEL_STAT_ID = UUID.fromString("f591249f-7637-4fcb-98b7-064722ce3b2a");
    public static final String BASE_STRING = "base_stats";
    public static final String LEVEL_STRING = "level_stats";

    // Codec definition for serialization/deserialization
    public static final Codec<EntityStatisticalData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    OffenseStats.CODEC.fieldOf("offense_stats")
                            .forGetter(EntityStatisticalData::offenseStats),
                    DefenseStats.CODEC.fieldOf("defense_stats")
                            .forGetter(EntityStatisticalData::defenseStats),
                    Codec.DOUBLE.fieldOf("movement_speed")
                            .forGetter(EntityStatisticalData::movement_speed),
                    Codec.DOUBLE.fieldOf("movement_speed_scaling")
                            .forGetter(EntityStatisticalData::movement_speed_scaling),
                    Codec.DOUBLE.fieldOf("experience")
                            .forGetter(EntityStatisticalData::experience),
                    Codec.DOUBLE.fieldOf("experience_scaling")
                            .forGetter(EntityStatisticalData::experience_scaling),
                    Codec.STRING.listOf().fieldOf("entity_traits")
                            .forGetter(EntityStatisticalData::entity_traits)
            ).apply(instance, EntityStatisticalData::new)
    );

//    public void loadtoEntity(LivingEntity entity) {
//        if (entity.getAttribute(AttributeRegistration.WEAPON_POWER.get()).getModifier(BASE_STAT_ID) != null) {
//            return;
//        }
//
//        addAttributeToEntity(entity, AttributeRegistration.WEAPON_POWER.get(), attack_damage, attack_damage_scaling);
//        addAttributeToEntity(entity, Attributes.ATTACK_SPEED, 4 - attack_speed, 4 - attack_speed_scaling);
//        addAttributeToEntity(entity, AttributeRegistration.PHYSICAL_POWER.get(), physical_power, physical_power_scaling);
//        addAttributeToEntity(entity, AttributeRegistration.MAGICAL_POWER.get(), magical_power, magical_power_scaling);
//
//        addAttributeToEntity(entity, Attributes.MAX_HEALTH, max_health, max_health_scaling);
//
//        entity.heal(entity.getMaxHealth());
//        addAttributeToEntity(entity, AttributeRegistration.HEALTH_REGENERATION.get(), health_regeneration, health_regeneration);
//        addAttributeToEntity(entity, AttributeRegistration.ARMOR.get(), armor, armor_scaling);
//        addAttributeToEntity(entity, AttributeRegistration.MAGIC_RESISTANCE.get(), magic_resist, magic_resist_scaling);
//        addAttributeToEntity(entity, Attributes.MOVEMENT_SPEED, movement_speed, movement_speed_scaling);
//        addAttributeToEntity(entity, AttributeRegistration.EXPERIENCE.get(), base_experience, experience_scaling);
//
//        IMobData mobData = Utilities.getMobData((Mob) entity);
//
//        IEntityData entityData = Utilities.getEntityData(entity);
//        for (String key : entity_traits) {
//            EntityTrait trait = EntityTrait.getTraitByLocation(new ResourceLocation(key));
//            mobData.getEntityTraits().add(trait);
//        }
//
//        mobData.setExperienceReward((float) (base_experience + (experience_scaling * (1 - entityData.getLevel()))));
//    }
//
//    public void addAttributeToEntity(LivingEntity entity, Attribute attribute, double baseValue, double scaleValue) {
//        double appliedBase = baseValue;
//        double appliedScale = scaleValue;
//        if (entity instanceof Slime slime) {
//            appliedBase = baseValue * ((double) slime.getSize());
//            appliedScale = scaleValue * ((double) slime.getSize());
//        }
//
//        entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(BASE_STAT_ID, BASE_STRING, appliedBase, AttributeModifier.Operation.ADDITION));
//
//        int level = Utilities.getEntityData(entity).getLevel();
//        if (level != 1) {
//            entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(LEVEL_STAT_ID, LEVEL_STRING, appliedScale * (level - 1), AttributeModifier.Operation.ADDITION));
//        }
//    }

    public record DefenseStats(
            double max_health,
            double max_health_scaling,
            double health_regeneration,
            double health_regeneration_scaling,
            double armor,
            double armor_scaling,
            double magic_resist,
            double magic_resist_scaling
    ) {
        public static final Codec<DefenseStats> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.DOUBLE.fieldOf("max_health")
                                .forGetter(DefenseStats::max_health),
                        Codec.DOUBLE.fieldOf("max_health_scaling")
                                .forGetter(DefenseStats::max_health_scaling),
                        Codec.DOUBLE.fieldOf("health_regeneration")
                                .forGetter(DefenseStats::health_regeneration),
                        Codec.DOUBLE.fieldOf("health_regeneration_scaling")
                                .forGetter(DefenseStats::health_regeneration_scaling),
                        Codec.DOUBLE.fieldOf("armor")
                                .forGetter(DefenseStats::armor),
                        Codec.DOUBLE.fieldOf("armor_scaling")
                                .forGetter(DefenseStats::armor_scaling),
                        Codec.DOUBLE.fieldOf("magic_resist")
                                .forGetter(DefenseStats::magic_resist),
                        Codec.DOUBLE.fieldOf("magic_resist_scaling")
                                .forGetter(DefenseStats::magic_resist_scaling)
                ).apply(instance, DefenseStats::new)
        );
    }

    public record OffenseStats(
            double attack_damage,
            double attack_damage_scaling,
            double attack_speed,
            double attack_speed_scaling,
            double physical_power,
            double physical_power_scaling,
            double magic_power,
            double magic_power_scaling
    ) {
        public static final Codec<OffenseStats> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.DOUBLE.fieldOf("attack_damage")
                                .forGetter(OffenseStats::attack_damage),
                        Codec.DOUBLE.fieldOf("attack_damage_scaling")
                                .forGetter(OffenseStats::attack_damage_scaling),
                        Codec.DOUBLE.fieldOf("attack_speed")
                                .forGetter(OffenseStats::attack_speed),
                        Codec.DOUBLE.fieldOf("attack_speed_scaling")
                                .forGetter(OffenseStats::attack_speed_scaling),
                        Codec.DOUBLE.fieldOf("physical_power")
                                .forGetter(OffenseStats::physical_power),
                        Codec.DOUBLE.fieldOf("physical_power_scaling")
                                .forGetter(OffenseStats::physical_power_scaling),
                        Codec.DOUBLE.fieldOf("magic_power")
                                .forGetter(OffenseStats::magic_power),
                        Codec.DOUBLE.fieldOf("magic_power_scaling")
                                .forGetter(OffenseStats::magic_power_scaling)
                ).apply(instance, OffenseStats::new)
        );
    }
}