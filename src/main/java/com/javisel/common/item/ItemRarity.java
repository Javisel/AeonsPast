package com.javisel.common.item;

import net.minecraft.ChatFormatting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public enum ItemRarity {

    TRASH(0, "aeonspast:trash", ChatFormatting.DARK_GRAY, 5, .4, 0.5),
    POOR(1, "aeonspast:poor", ChatFormatting.GRAY, 25, .6, 0.75),
    COMMON(2, "aeonspast:common", ChatFormatting.WHITE, 50, .76, 1),
    RARE(3, "aeonspast:rare", ChatFormatting.DARK_BLUE, 10, 1.1, 1.2),
    FABLED(4, "aeonspast:fabled", ChatFormatting.DARK_PURPLE, 8, 1.21, 1.3),
    LEGENDARY(5, "aeonspast:legendary", ChatFormatting.AQUA, 5, 1.31, 1.4),
    MYTHIC(6, "aeonspast:mythic", ChatFormatting.GOLD, 1, 1.41, 1.5);

    public static final ItemRarity[] ITEM_RARITIES = Arrays.stream(values()).sorted(Comparator.comparingInt(ItemRarity::getId)).toArray(ItemRarity[]::new);


    private final String unlocalizedName;

    private final int id;
    private final ChatFormatting chatFormat;
    private final double weight;
    private final double minValue;
    private final double maxValue;

    ItemRarity(int id, String unlocalizedName, ChatFormatting chatFormat, double weight, double minValue, double maxValue) {
        this.id = id;
        this.unlocalizedName = unlocalizedName;
        this.chatFormat = chatFormat;
        this.weight = weight;
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static ItemRarity getById(int id) {

        return ITEM_RARITIES[id];
    }

    public static ItemRarity byUnlocalizedName(String name) {


        for (ItemRarity rarity : ITEM_RARITIES) {

            if (rarity.unlocalizedName.equalsIgnoreCase(name)) {
                return rarity;
            }


        }


        return null;

    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public int getId() {
        return id;
    }

    public ChatFormatting getChatFormat() {
        return chatFormat;
    }

    public double getMinValue() {
        return minValue;
    }

    public double getMaxValue() {
        return maxValue;
    }

    public static ItemRarity getbyPercentChance(int chance) {
         if (chance<=1) {
             return MYTHIC;
         }
         else if (chance <=5) {
             return new Random().nextBoolean() ? LEGENDARY : TRASH;

         }
         else if (chance <=8) {

             return FABLED;
         }
         else if (chance<=10) {

             return RARE;

         }
         else if (chance <=21) {
             return POOR;
         }

         else {
             return COMMON;
         }

    }

    public static int getidByMultiplier(double multiplier) {

        for (ItemRarity rarity : ITEM_RARITIES) {

            if (multiplier>= rarity.getMinValue() && multiplier<=rarity.getMaxValue()) {

                return rarity.id;
            }


        }
        return 0;
    }

    public static ItemRarity getByID(int id) {
        for (ItemRarity rarity : ITEM_RARITIES) {

           if (rarity.id==id) {
               return rarity;
           }


        }

        return null;
    }

    public static ItemRarity getByString(String string) {

        for (ItemRarity rarity : ITEM_RARITIES) {

            if (rarity.unlocalizedName.equalsIgnoreCase(string)) {
                return rarity;
            }


        }

        return null;

    }



}
