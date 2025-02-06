package com.javisel.common.combat;

import com.javisel.common.entity.projectile.ProjectileData;
import com.javisel.common.registration.AttributeRegistration;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class CombatEngine {



    public static int criticals(int chance) {

        int result = 0;

        Random random = new Random();

        int roll = random.nextInt(101);

        if (chance >=roll) {

            result++;
        }

        if (chance > 100) {

            result += criticals(chance-100);
        }

        return result;
    }


    public static  int rollDirectCrit(LivingEntity roller, LivingEntity victim) {

        int chance = (int) roller.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE.getDelegate());
        chance+= (int) victim.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE_INTAKE.getDelegate());

        return criticals(chance);
    }

    public static int rollIndirectCrit(ProjectileData data, LivingEntity victim) {

        int chance = (int) data.critical_chance();
        chance+= (int) victim.getAttributeValue(AttributeRegistration.CRITICAL_CHANCE_INTAKE.getDelegate());


        return criticals(chance);

    }

    public static boolean rollDirectStatus(LivingEntity roller, LivingEntity victim, ComplexDamageTypes type) {


        int chance = (int) roller.getAttributeValue(AttributeRegistration.STATUS_CHANCE.getDelegate());
        chance+= (int) victim.getAttributeValue(AttributeRegistration.STATUS_CHANCE_INTAKE.getDelegate());
        chance+= type.getStatus_intake () != null ? (int) victim.getAttributeValue(type.getStatus_intake()) : 0;
        Random random = new Random();

        int roll = random.nextInt(101);
        return roll <=chance;


    }
    public static boolean rollIndirectStatus(ProjectileData data, LivingEntity victim, ComplexDamageTypes type) {

        int chance = (int) data.status_chance();
        chance+= (int) victim.getAttributeValue(AttributeRegistration.STATUS_CHANCE_INTAKE.getDelegate());
        chance+= (int) victim.getAttributeValue(type.getStatus_intake());
        Random random = new Random();

        int roll = random.nextInt(101);
        return roll <=chance;


    }
}
