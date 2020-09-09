package net.msrandom.simplyhorses.init;

import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.registries.IForgeRegistry;
import net.msrandom.simplyhorses.item.ItemCustomSaddle;

public class ModItems {
    public static final ItemCustomSaddle ALL_AROUND_SADDLE = new ItemCustomSaddle("all_around_saddle");
    public static final ItemCustomSaddle DRESSAGE_SADDLE = new ItemCustomSaddle("dressage_saddle");
    public static final ItemCustomSaddle JUMPING_SADDLE = new ItemCustomSaddle("jumping_saddle");
    public static final ItemCustomSaddle WESTERN_SADDLE = new ItemCustomSaddle("western_saddle");

    public static void register(IForgeRegistry<Item> registry) {
        registry.registerAll(
                ALL_AROUND_SADDLE,
                DRESSAGE_SADDLE,
                JUMPING_SADDLE,
                WESTERN_SADDLE
        );
    }

    @SideOnly(Side.CLIENT)
    public static void registerModels() {
        ALL_AROUND_SADDLE.registerItemModel();
        DRESSAGE_SADDLE.registerItemModel();
        JUMPING_SADDLE.registerItemModel();
        WESTERN_SADDLE.registerItemModel();
    }
}
