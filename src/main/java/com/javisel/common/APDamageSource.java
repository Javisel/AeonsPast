package com.javisel.common;

import com.javisel.common.combat.DamageTypes;
import net.minecraft.core.Holder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class APDamageSource extends DamageSource {

    DamageTypes apDamageType;
    public APDamageSource(DamageTypes damageType, Holder<DamageType> type, @Nullable Entity directEntity, @Nullable Entity causingEntity, @Nullable Vec3 damageSourcePosition) {
        super(type, directEntity, causingEntity, damageSourcePosition);
        apDamageType=damageType;


    }



}
