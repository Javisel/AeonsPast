package com.javisel.common.registration;

import com.javisel.AeonsPast;
import com.javisel.common.item.ItemData;
import com.javisel.common.item.armor.ArmorData;
import com.javisel.common.item.weapon.WeaponData;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

import static com.javisel.common.item.weapon.WeaponData.BASIC_CODEC;
import static com.javisel.common.item.weapon.WeaponData.BASIC_STREAM_CODEC;

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

  public static final Supplier<DataComponentType<ItemData>> ITEM_DATA = REGISTRAR.registerComponentType(
          "item_data",
          builder -> builder
                  // The codec to read/write the data to disk
                  .persistent(ItemData.BASIC_CODEC)
                  // The codec to read/write the data across the network
                  .networkSynchronized(ItemData.BASIC_STREAM_CODEC)
  );

  public static final Supplier<DataComponentType<ArmorData>> ARMOR_DATA = REGISTRAR.registerComponentType(
          "armor_data",
          builder -> builder
                  // The codec to read/write the data to disk
                  .persistent(ArmorData.BASIC_CODEC)
                  // The codec to read/write the data across the network
                  .networkSynchronized(ArmorData.BASIC_STREAM_CODEC)
  );




}
