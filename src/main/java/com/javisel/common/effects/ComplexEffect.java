package com.javisel.common.effects;

import com.javisel.common.attatchments.EntityData;
import com.javisel.common.combat.APDamageSource;
import com.javisel.common.registration.AttachmentTypeRegistration;
import com.javisel.utilities.Utilities;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ComplexEffect extends MobEffect {

    protected final Holder<MobEffect> holder;

    //TODO Color codes for all Effects
    public ComplexEffect(MobEffectCategory effectCate, int effectColour) {
        super(effectCate, effectColour);
        holder = Holder.direct(this);
    }

    @Override
    public boolean isInstantenous() {
        return false;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return true;
    }


    @Override
    public final boolean applyEffectTick(ServerLevel level, LivingEntity livingEntity, int durationIn) {
        super.applyEffectTick(level,livingEntity, durationIn);


        if (livingEntity.isDeadOrDying() || livingEntity.isRemoved()) {
            return false;
        }



        for (ComplexEffectInstance instance : getAllInstancesOnEntity(livingEntity)) {


            instance.duration--;


            if (instance.duration == 0) {

                instance.remove = true;
                removeComplexInstance(instance.instanceID, livingEntity);
            }

            if (instance.duration % instance.tickRate == 0) {

                applyTickableEffect(instance, livingEntity);
            }


        }


        return false;
    }


    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {

    }


    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {

        EntityData entityData = user.getData(AttachmentTypeRegistration.ENTITY_DATA.get());


        List<ComplexEffectInstance> instances;
        if (entityData.getMobEffectArrayListHashMap().containsKey(Holder.direct(this))) {


            instances = entityData.getMobEffectArrayListHashMap().get(Holder.direct(this));


        } else {
            instances = new ArrayList<>();
        }

        instances.add(instance);

        entityData.getMobEffectArrayListHashMap().put(Holder.direct(this), instances);


        if (user.hasEffect(Holder.direct(this))) {

            if (user.getEffect(Holder.direct(this)).getDuration() < instance.duration) {
                user.forceAddEffect(new MobEffectInstance(Holder.direct(this), (int) instance.duration, 0, false, this instanceof StatusEffect, this instanceof StatusEffect), null);

            }


        } else {

            user.forceAddEffect(new MobEffectInstance(Holder.direct(this), (int) instance.duration, 0, false, this instanceof StatusEffect, this instanceof StatusEffect), null);

        }
        recalculateInstances(user);


    }


    public void removeComplexInstance(UUID id, LivingEntity user) {

        EntityData entityData = user.getData(AttachmentTypeRegistration.ENTITY_DATA.get());


        List<ComplexEffectInstance> instances;
        if (entityData.getMobEffectArrayListHashMap().containsKey(this)) {


            instances = entityData.getMobEffectArrayListHashMap().get(this);


        } else {

            return;
        }


        for (ComplexEffectInstance comp : instances) {

            if (comp.instanceID.equals(id)) {


                comp.remove = true;

                instances.remove(comp);

                recalculateInstances(user);

            }


        }


        if (instances.isEmpty()) {

            user.removeEffect(Holder.direct(this));
        }


        recalculateInstances(user);


    }


    public boolean onpreHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


        return true;
    }

    public void onHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }

    public void onpostHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }


    public boolean onOwnerpreHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


        return true;
    }

    public void onOwnerHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }

    public void onOwnerpostHitEffect(LivingEntity attacker, LivingEntity blocker, APDamageSource damageSource) {


    }


    public List<ComplexEffectInstance> getAllInstancesOnEntity(LivingEntity entity) {

        List<ComplexEffectInstance> effectInstances = new ArrayList<>();


        EntityData entityData = entity.getData(AttachmentTypeRegistration.ENTITY_DATA.get());


        if (entityData.getMobEffectArrayListHashMap().containsKey(Holder.direct(this))) {

            effectInstances = entityData.getMobEffectArrayListHashMap().get(Holder.direct(this));

        }


        return effectInstances;


    }


    public void recalculateInstances(LivingEntity user) {


    }


    public void consumeEffect(LivingEntity holder) {

        EntityData entityData = holder.getData(AttachmentTypeRegistration.ENTITY_DATA.get());

        entityData.getMobEffectArrayListHashMap().remove(Holder.direct(this));

        holder.removeEffect(Holder.direct(this));


    }

    public ComplexEffectInstance getDefaultInstance() {


        return null;
    }


}
