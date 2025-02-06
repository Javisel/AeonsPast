package com.javisel.common.effects.Debuffs;

import com.javisel.common.effects.ComplexEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class Burn extends ComplexEffect  {


    public Burn( ) {
        super(MobEffectCategory.HARMFUL, 0xFF0000);
    }

    /*
    @Override
    public void applyTickableEffect(ComplexEffectInstance instance, LivingEntity entity) {
        super.applyTickableEffect(instance, entity);

        Level level = entity.getLevel();

        if (!level.isClientSide) {

            ServerLevel serverLevel = (ServerLevel) level;

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


     */

}
