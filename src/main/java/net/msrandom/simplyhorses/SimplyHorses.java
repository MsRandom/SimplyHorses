package net.msrandom.simplyhorses;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.simplyhorses.horse.EntitySHorse;
import net.msrandom.simplyhorses.horse.client.RenderSHorse;
import net.msrandom.simplyhorses.init.ModItems;
import net.msrandom.simplyhorses.item.ItemCustomSaddle;
import org.apache.logging.log4j.Logger;

@Mod(modid = SimplyHorses.MOD_ID, name = "Simply Horses", version = "1.0")
public class SimplyHorses {
    public static final String MOD_ID = "simplyhorses";

    private static Logger logger;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        logger = event.getModLog();
    }

    public static Logger getLogger() {
        return logger;
    }

    @Mod.EventBusSubscriber
    public static class Registry {
        @SubscribeEvent
        public static void registerEntity(RegistryEvent.Register<EntityEntry> event) {
            event.getRegistry().register(EntityEntryBuilder.create().entity(EntitySHorse.class).id("horse", 0).name("shorse").tracker(32, 2, true).build());
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            ModItems.register(event.getRegistry());
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public static void registerClient(ModelRegistryEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(EntitySHorse.class, RenderSHorse::new);
            ModItems.registerModels();
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public static void registerItemColors(ColorHandlerEvent.Item event) {
            event.getItemColors().registerItemColorHandler(
                    (stack, tintIndex) -> tintIndex > 0 ? -1 : ((ItemCustomSaddle) stack.getItem()).getColor(stack),
                    ModItems.ALL_AROUND_SADDLE, ModItems.DRESSAGE_SADDLE, ModItems.JUMPING_SADDLE, ModItems.WESTERN_SADDLE);

        }
    }
}
