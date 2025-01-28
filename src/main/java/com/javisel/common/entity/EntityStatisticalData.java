package com.javisel.common.entity;

import com.javisel.AeonsPast;
import com.javisel.common.registration.AttributeRegistration;
import com.javisel.common.registration.DataAttachmentRegistration;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

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


    public record DefenseStats(
            double max_health,
            double max_health_scaling,
            double health_regeneration,
            double health_regeneration_scaling,
            double armor,
            double armor_scaling,
            List<String> resistances,
            List<String> immunities,
            List<String> vulnerabilities
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
                        Codec.STRING.listOf().fieldOf("resistances")
                                .forGetter(DefenseStats::resistances),
                        Codec.STRING.listOf().fieldOf("immunities")
                                .forGetter(DefenseStats::immunities),
                        Codec.STRING.listOf().fieldOf("vulnerabilities")
                                .forGetter(DefenseStats::vulnerabilities)
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

    public void loadtoEntity(LivingEntity entity, int levelIn) {



        entity.getAttribute(AttributeRegistration.NPC_LEVEL).addPermanentModifier(new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stat"), levelIn, AttributeModifier.Operation.ADD_VALUE));

        addAttributeToEntity(entity, Attributes.ATTACK_DAMAGE, offenseStats().attack_damage, offenseStats().attack_damage_scaling);
        addAttributeToEntity(entity, Attributes.ATTACK_SPEED, -4 + offenseStats().attack_speed, -4+ offenseStats().attack_speed_scaling());

        addAttributeToEntity(entity, Attributes.MAX_HEALTH, -20 + defenseStats().max_health, defenseStats().max_health_scaling);

        entity.heal(entity.getMaxHealth());
        addAttributeToEntity(entity, AttributeRegistration.HEALTH_REGENERATION, defenseStats().health_regeneration, defenseStats().health_regeneration_scaling);
        addAttributeToEntity(entity, Attributes.ARMOR,  defenseStats().armor, defenseStats().armor_scaling);

        addAttributeToEntity(entity, Attributes.MOVEMENT_SPEED, movement_speed, movement_speed_scaling);
        addAttributeToEntity(entity, AttributeRegistration.NPC_EXPERIENCE, experience, experience_scaling);

        StringBuilder vulnerabilitiesbuilder = new StringBuilder();


        for (String string : defenseStats.vulnerabilities) {

            vulnerabilitiesbuilder.append(",").append(string);


        }

        String vulnerabilties = vulnerabilitiesbuilder.toString();
        if (vulnerabilties.length()>1) {
            vulnerabilties = vulnerabilties.substring(1);

            entity.setData(DataAttachmentRegistration.VULNERABILITIES, vulnerabilties);

            System.out.println("Vulnerabilities: " + vulnerabilties);

        }
        StringBuilder resistancesb = new StringBuilder();


        for (String string : defenseStats.resistances) {

            resistancesb.append(",").append(string);


        }

        String resistances = resistancesb.toString();
        if (resistances.length() > 1) {
            resistances = resistances.substring(1);

            entity.setData(DataAttachmentRegistration.RESISTANCES, resistances);

            System.out.println("Resistances: " + resistances);

        }
        StringBuilder immunitiesb = new StringBuilder();


        for (String string : defenseStats.immunities) {

            immunitiesb.append(",").append(string);


        }


        String immunities = immunitiesb.toString();
        if (immunities.length() > 1) {
            immunities = immunities.substring(1);

            entity.setData(DataAttachmentRegistration.IMMUNITIES, immunities);

            System.out.println("immunities: " + immunities);
        }




    }

   public void addAttributeToEntity(LivingEntity entity, Holder<Attribute> attribute, double baseValue, double scaleValue) {
        double appliedBase = baseValue;
        double appliedScale = scaleValue;
        double scalar = -1 + entity.getAttributeValue(AttributeRegistration.NPC_LEVEL.getDelegate());
        appliedBase+=(appliedScale * scalar);

        entity.getAttribute(attribute).addPermanentModifier(new AttributeModifier(ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"base_stat"), appliedBase, AttributeModifier.Operation.ADD_VALUE));


   }




}