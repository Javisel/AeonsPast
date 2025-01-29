package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.javisel.common.item.WeaponData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.javisel.common.item.WeaponData.BASIC_CODEC;
import static com.javisel.common.item.WeaponData.BASIC_STREAM_CODEC;

public class DataComponentsRegistration {

  public static final DeferredRegister.DataComponents REGISTRAR = DeferredRegister.createDataComponents(Registries.DATA_COMPONENT_TYPE, AeonsPast.MODID);


    public static final Supplier<DataComponentType<WeaponData>> WEAPON_DATA = REGISTRAR.registerComponentType(
            "weapon_data",
            builder -> builder
                    // The codec to read/write the data to disk
                    .persistent(BASIC_CODEC)
                    // The codec to read/write the data across the network
                    .networkSynchronized(BASIC_STREAM_CODEC)
    );








}
