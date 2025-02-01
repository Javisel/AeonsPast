package com.javisel.common.effects.Debuffs;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.combat.damage.sources.APDamageSource;
import com.javisel.aeonspast.common.combat.damage.sources.APEntityDamageSource;
import com.javisel.aeonspast.common.effects.ComplexEffect;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.IDamageStatus;
import com.javisel.aeonspast.utilities.Utilities;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectCategory;
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

            if (proc == 0){
                return;
            } else if (proc==1) {

                entity.getMainHandItem().hurt(1,entity.getRandom(), entity instanceof Player ? (ServerPlayer)entity : null   );

            } else if (proc==2) {

                entity.getOffhandItem().hurt(1,entity.getRandom(), entity instanceof Player ? (ServerPlayer)entity : null   );

            } else if (proc==3) {

                for (ItemStack stack : entity.getArmorSlots()) {

                    if (entity.getRandom().nextInt(5) ==1) {

                       stack.hurt(1,entity.getRandom(), entity instanceof Player ? (ServerPlayer)entity : null   );
                    }

                }

            }


        }
    }

    @Override
    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.FIRE;
    }

    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance) {


        double power =  2.5 + .5 * Utilities.getEntityData(attacker).getLevel();




        ComplexEffectInstance instance = new ComplexEffectInstance(UUID.randomUUID(),attacker.getUUID(),power,(20 * 5) +1);


            instance.tickRate=10;



        return instance;
    }
}
