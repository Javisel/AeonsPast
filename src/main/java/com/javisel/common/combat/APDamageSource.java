package com.javisel.common.combat;

import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class APDamageSource extends DamageSource {

    public int criticals;

    public APDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity, @Nullable Vec3 damageSourcePosition) {
        super(type, directEntity, causingEntity, damageSourcePosition);
    }

    public APDamageSource(Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity) {
        super(type, directEntity, causingEntity);
    }

    public APDamageSource(Holder<DamageType> type, Vec3 damageSourcePosition) {
        super(type, damageSourcePosition);
    }

    public APDamageSource(Holder<DamageType> type, @Nullable Entity entity) {
        super(type, entity);
    }

    public APDamageSource(Holder<DamageType> type) {
        super(type);
    }




}
