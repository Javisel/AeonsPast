package com.javisel.common.effects.Debuffs;

import com.javisel.common.combat.APDamageSource;
import com.javisel.common.combat.ComplexDamageTypes;
import com.javisel.common.effects.ComplexEffect;
import com.javisel.common.effects.ComplexEffectInstance;
import com.javisel.common.effects.IDamageStatus;
import com.javisel.common.registration.AttachmentTypeRegistration;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.UUID;

public class Burn extends ComplexEffect implements IDamageStatus {


    public Burn( ) {
        super(MobEffectCategory.HARMFUL, 0xFF0000);
    }

    @Override
    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {
        super.applyTickableEffect(instance, entity);

        Level level = entity.getLevel();

        if (!level.isClientSide) {

            ServerLevel serverLevel = (ServerLevel) level;
            DamageInstance dmg = DamageInstance.getGenericProcInstance(DamageTypeEnum.BURN,instance.power);

            DamageSource source  = instance.source !=null ? new APEntityDamageSource("burn",dmg,serverLevel.getEntity(instance.source)) : new APDamageSource("burn",dmg);

            entity.hurt(source, (float) instance.power);

            int proc = entity.getRandom().nextInt(4);

            switch (proc) {
                case 0 -> {
                }
                case 1 -> entity.getMainHandItem().hurtAndBreak(1, entity, EquipmentSlot.MAINHAND);
                case 2 ->
                        entity.getOffhandItem().hurt(1, entity.getRandom(), entity instanceof Player ? (ServerPlayer) entity : null);
                case 3 -> {

                    for (ItemStack stack : entity.getArmorSlots()) {

                        if (entity.getRandom().nextInt(5) == 1) {

                            stack.hurt(1, entity.getRandom(), entity instanceof Player ? (ServerPlayer) entity : null);
                        }
                    }
                }
            }
        }
    }

    @Override
    public ComplexDamageTypes getDamageType() {
        return ComplexDamageTypes.FIRE;
    }

    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance) {


        double power =  2.5 + .5 * attacker.getData(AttachmentTypeRegistration.ENTITY_DATA.get()).getLevel();




        ComplexEffectInstance instance = ComplexEffectInstance.of(UUID.randomUUID(),attacker.getUUID(),power,(20 * 5) +1);


            instance.tickRate=10;



        return instance;
    }
}
