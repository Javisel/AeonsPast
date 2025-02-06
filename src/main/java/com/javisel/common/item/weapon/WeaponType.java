package com.javisel.common.item.weapon;

import com.javisel.common.combat.ComplexDamageTypes;

public enum WeaponType {
    UNARMED("aeonspast:unarmed", ComplexDamageTypes.IMPACT, 0),
    SWORD("aeonspast:sword", ComplexDamageTypes.SLASH, 1),
    AXE("aeonspast:axe", ComplexDamageTypes.SLASH, 2),
    SPEAR("aeonspast:spear", ComplexDamageTypes.PUNCTURE, 3),
    DAGGER("aeonspast:dagger", ComplexDamageTypes.PUNCTURE, 4),
    BOW("aeonspast:bow", ComplexDamageTypes.PUNCTURE, 5),
    CROSSBOW("aeonspast:crossbow", ComplexDamageTypes.IMPACT, 6);


    private final String unlocalizedName;
    private final String descriptionKey;
    private final ComplexDamageTypes damageType;
    private final int id;

    WeaponType(String unlocalizedName, ComplexDamageTypes damageType, int id) {

        this.unlocalizedName = unlocalizedName;
        this.damageType = damageType;
        this.id = id;
        this.descriptionKey = unlocalizedName + ".desc";


    }

    public String getUnlocalizedName() {
        return unlocalizedName;
    }

    public String getDescriptionKey() {
        return descriptionKey;
    }

    public ComplexDamageTypes getDamageType() {
        return damageType;
    }

    public int getId() {
        return id;
    }
}
