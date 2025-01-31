package com.javisel.common.combat;

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







}
