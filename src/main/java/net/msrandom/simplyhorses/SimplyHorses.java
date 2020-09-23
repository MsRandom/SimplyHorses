package net.msrandom.simplyhorses;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.simplyhorses.entity.SHEntityHorse;
import net.msrandom.simplyhorses.client.renderer.entity.SHRenderHorse;
import net.msrandom.simplyhorses.init.SHEntities;
import net.msrandom.simplyhorses.init.SHItems;
import net.msrandom.simplyhorses.item.SHDyableItem;
import net.msrandom.simplyhorses.item.SHItemSaddle;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

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
            SHEntities.REGISTERED.forEach(event.getRegistry()::register);
        }

        @SubscribeEvent
        public static void registerItems(RegistryEvent.Register<Item> event) {
            SHItems.REGISTERED.forEach(event.getRegistry()::register);
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public static void registerClient(ModelRegistryEvent event) {
            RenderingRegistry.registerEntityRenderingHandler(SHEntityHorse.class, SHRenderHorse::new);
            for (Item item : SHItems.REGISTERED) {
                ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(Objects.requireNonNull(item.getRegistryName()), "inventory"));
            }
        }

        @SideOnly(Side.CLIENT)
        @SubscribeEvent
        public static void registerItemColors(ColorHandlerEvent.Item event) {
            event.getItemColors().registerItemColorHandler((stack, tintIndex) -> tintIndex == 0 ? SHDyableItem.getColor(stack) : -1,
                    SHItems.ALL_AROUND_SADDLE, SHItems.DRESSAGE_SADDLE, SHItems.JUMPING_SADDLE, SHItems.WESTERN_SADDLE);
        }
    }
}
