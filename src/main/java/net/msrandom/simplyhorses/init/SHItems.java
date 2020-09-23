package net.msrandom.simplyhorses.init;

import net.minecraft.item.Item;
import net.msrandom.simplyhorses.SimplyHorses;
import net.msrandom.simplyhorses.item.SHItemSaddle;

import java.util.ArrayList;
import java.util.List;

public class SHItems {
    public static final List<Item> REGISTERED = new ArrayList<>();
    public static final SHItemSaddle ALL_AROUND_SADDLE = register("all_around_saddle", new SHItemSaddle());
    public static final SHItemSaddle DRESSAGE_SADDLE = register("dressage_saddle", new SHItemSaddle());
    public static final SHItemSaddle JUMPING_SADDLE = register("jumping_saddle", new SHItemSaddle());
    public static final SHItemSaddle WESTERN_SADDLE = register("western_saddle", new SHItemSaddle());


    private static <T extends Item> T register(String name, T item) {
        item.setTranslationKey(SimplyHorses.MOD_ID + "." + name);
        item.setRegistryName(name);
        REGISTERED.add(item);
        return item;
    }
}
