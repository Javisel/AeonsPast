package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.javisel.common.effects.Debuffs.*;
import com.javisel.common.effects.buffs.generic.AttackSpeedBuff;
import com.javisel.common.effects.buffs.generic.PhysicalPowerBuff;
import com.javisel.common.effects.buffs.spell.TrueStrikeSpellBuff;
import com.javisel.common.effects.buffs.spell.UppercutSpellBuff;
import com.javisel.common.effects.trackers.BrutalCooldown;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MobEffectRegistration {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, AeonsPast.MODID);

    public static final DeferredHolder<MobEffect,MobEffect> UPPERCUT_SPELL_BUFF = MOB_EFFECTS.register("uppercut_spell_buff", () -> new UppercutSpellBuff());
    public static final DeferredHolder<MobEffect,MobEffect> TRUE_STRIKE_SPELL_BUFF = MOB_EFFECTS.register("true_strike_spell_buff", () -> new TrueStrikeSpellBuff());
    public static final DeferredHolder<MobEffect,MobEffect> BRUTAL_COOLDOWN = MOB_EFFECTS.register("brutal_cooldown", () -> new BrutalCooldown());



    //Damage Debuffs
    public static final DeferredHolder<MobEffect,MobEffect> BLEED = MOB_EFFECTS.register("bleed", () -> new Bleed());
    public static final DeferredHolder<MobEffect,MobEffect> PERFORATE = MOB_EFFECTS.register("perforate", () -> new Perforate());
    public static final DeferredHolder<MobEffect,MobEffect> STAGGER = MOB_EFFECTS.register("stagger", () -> new Stagger());
    public static final DeferredHolder<MobEffect,MobEffect> RADIANCE = MOB_EFFECTS.register("radiance", () -> new Radiance());
    public static final DeferredHolder<MobEffect,MobEffect> BURN = MOB_EFFECTS.register("burn", () -> new Burn());
    public static final DeferredHolder<MobEffect,MobEffect> FREEZE = MOB_EFFECTS.register("freeze", () -> new Freeze());


    public static final DeferredHolder<MobEffect,MobEffect> ATTACK_SPEED_BUFF = MOB_EFFECTS.register("complex_attack_speed_buff", () -> new AttackSpeedBuff());
    public static final DeferredHolder<MobEffect,MobEffect> PHYSICAL_POWER_BUFF = MOB_EFFECTS.register("complex_physical_power_buff", () -> new PhysicalPowerBuff());

    public static final DeferredHolder<MobEffect,MobEffect> TAUNT_DEBUFF = MOB_EFFECTS.register("taunt_debuff", () -> new TauntDebuff());
    
}
