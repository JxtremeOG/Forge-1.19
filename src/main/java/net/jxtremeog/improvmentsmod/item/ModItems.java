package net.jxtremeog.improvmentsmod.item;

import net.jxtremeog.improvmentsmod.ImprovmentsMod;
import net.jxtremeog.improvmentsmod.fluid.ModFluids;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ImprovmentsMod.MOD_ID);

    public static final RegistryObject<Item> ZIRCON = ITEMS.register("zircon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> RAW_ZIRCON = ITEMS.register("raw_zircon",
            () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    //Regeneration Bucket
    public static final RegistryObject<Item> REGENERATION_BUCKET = ITEMS.register("regeneration_bucket",
            () -> new BucketItem(ModFluids.SOURCE_REGENERATION,
                    new Item.Properties().tab(ModCreativeModeTab.IMPROVMENTS_TAB)
                            .craftRemainder(Items.BUCKET).stacksTo(1)));



    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
