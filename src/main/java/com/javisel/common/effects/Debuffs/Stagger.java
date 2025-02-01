package com.javisel.common.effects.Debuffs;

import com.javisel.AeonsPast;
import com.javisel.common.combat.ComplexDamageTypes;
import com.javisel.common.effects.ComplexEffectInstance;
import com.javisel.common.effects.ComplexStatChangeEffect;
import com.javisel.common.effects.IDamageStatus;
import com.javisel.common.particles.WorldTextParticleOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

import java.util.UUID;

public class Stagger extends ComplexStatChangeEffect implements IDamageStatus {

    private static final ResourceLocation STAGGER_ID = ResourceLocation.fromNamespaceAndPath(AeonsPast.MODID,"stagger");
    public Stagger( ) {
        super(Attributes.MOVEMENT_SPEED, STAGGER_ID, MobEffectCategory.HARMFUL, 0, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
    }

    @Override
    public ComplexDamageTypes getDamageType() {
        return ComplexDamageTypes.IMPACT;
    }


    @Override
    public ComplexEffectInstance getDefaultDamageInstance(LivingEntity attacker, LivingEntity victim, DamageInstance procInstance) {
        ComplexEffectInstance instance = ComplexEffectInstance.of(UUID.randomUUID(),attacker.getUUID(),-1f,20 * 5);
        return instance;
    }


    @Override
    public void addnewComplexInstance(ComplexEffectInstance instance, LivingEntity user) {
        super.addnewComplexInstance(instance, user);


        //TODO replace with proper VF/SFX
        if (!user.level().isClientSide) {

            ServerLevel serverLevel = (ServerLevel) user.level();

            if (instance.source!=null) {

                if (serverLevel.getPlayerByUUID(instance.source) !=null) {


                    Player player = serverLevel.getPlayerByUUID(instance.source);
                    WorldTextParticleOptions textOptions = WorldTextOptions.getSpecialInstance("aeonspast.stagger.application");


                     double xpos = user.getX();
                    double ypos = user.getY() + user.getBbHeight() + 0.1;
                    double zpos = user.getZ();

                    double xd = 0;
                    double yd = 0;
                    double zd = 0;


                    serverLevel.sendParticles((ServerPlayer) player, textOptions,false, true, xpos, ypos, zpos, 1, xd, yd, zd, 0d);





                }


            }



        }




    }
}
