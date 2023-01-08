package net.jxtremeog.improvmentsmod;

import com.mojang.logging.LogUtils;
import net.jxtremeog.improvmentsmod.block.entity.ModBlockEntities;
import net.jxtremeog.improvmentsmod.fluid.ModFluidTypes;
import net.jxtremeog.improvmentsmod.fluid.ModFluids;
import net.jxtremeog.improvmentsmod.item.ModItems;
import net.jxtremeog.improvmentsmod.block.ModBlocks;
import net.jxtremeog.improvmentsmod.recipe.ModRecipes;
import net.jxtremeog.improvmentsmod.screen.ModMenuTypes;
import net.jxtremeog.improvmentsmod.screen.TempScreen;
import net.jxtremeog.improvmentsmod.screen.TierOneScreen;
import net.jxtremeog.improvmentsmod.villager.ModVillagers;
import net.jxtremeog.improvmentsmod.world.feature.ModConfiguredFeatures;
import net.jxtremeog.improvmentsmod.world.feature.ModPlacedFeatures;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ImprovmentsMod.MOD_ID)
public class ImprovmentsMod
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "improvmentsmod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public ImprovmentsMod()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModVillagers.register(modEventBus);

        ModConfiguredFeatures.register(modEventBus);
        ModPlacedFeatures.register(modEventBus);

        ModFluids.register(modEventBus);
        ModFluidTypes.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModVillagers.registerPOIS();
        });
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.SOURCE_REGENERATION.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.FLOWING_REGENERATION.get(), RenderType.translucent());

            MenuScreens.register(ModMenuTypes.TEMP_MENU.get(), TempScreen::new);
            MenuScreens.register(ModMenuTypes.TIERONE_MENU.get(), TierOneScreen::new);
        }
    }
}
