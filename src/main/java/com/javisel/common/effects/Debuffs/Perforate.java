package com.javisel.common.effects.Debuffs;

import com.javisel.aeonspast.common.combat.damage.instances.DamageInstance;
import com.javisel.aeonspast.common.combat.DamageTypeEnum;
import com.javisel.aeonspast.common.effects.ComplexEffectInstance;
import com.javisel.aeonspast.common.effects.ComplexStatChangeEffect;
import com.javisel.aeonspast.common.effects.IDamageStatus;
import com.javisel.aeonspast.common.particles.WorldTextOptions;
import com.javisel.aeonspast.common.registration.AttributeRegistration;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class Perforate extends ComplexStatChangeEffect  implements IDamageStatus {


    private static  final UUID PERFORATE_ID = UUID.fromString("ce6b6a54-bdb2-45a3-8a94-1abedcc8000e");
    public Perforate() {
        super(AttributeRegistration.ARMOR.get(), PERFORATE_ID, MobEffectCategory.HARMFUL, 0x964B00, AttributeModifier.Operation.MULTIPLY_TOTAL);
    }


    @Override
    public DamageTypeEnum getDamageType() {
        return DamageTypeEnum.PUNCTURE;
    }


    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance) {







        ComplexEffectInstance instance = new ComplexEffectInstance(UUID.randomUUID(),attacker.getUUID(),-0.05f,20 * 5);






        return instance;
    }

    @Override
    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {
        super.addnewComplexInstance(instance, user);


        //TODO replace with proper VF/SFX
        if (!user.level.isClientSide) {

            ServerLevel serverLevel = (ServerLevel) user.level;

            if (instance.source!=null) {

                if (serverLevel.getPlayerByUUID(instance.source) !=null) {


                    Player player = serverLevel.getPlayerByUUID(instance.source);
                    WorldTextOptions textOptions = WorldTextOptions.getSpecialInstance("aeonspast.perforate.application");


                     double xpos = user.getX();
                    double ypos = user.getY() + user.getBbHeight() + 0.1;
                    double zpos = user.getZ();

                    double xd = 0;
                    double yd = 0;
                    double zd = 0;


                    serverLevel.sendParticles((ServerPlayer) player, textOptions, true, xpos, ypos, zpos, 1, xd, yd, zd, 0d);





                }


            }



        }




    }
}
