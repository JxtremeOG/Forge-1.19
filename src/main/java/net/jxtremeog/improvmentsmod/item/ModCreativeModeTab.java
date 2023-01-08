package net.jxtremeog.improvmentsmod.item;

import net.jxtremeog.improvmentsmod.block.ModBlocks;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTab {
    public static final CreativeModeTab IMPROVMENTS_TAB = new CreativeModeTab("improvmentstab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.QUARTZ_ORE.get());
        }
    };
}
