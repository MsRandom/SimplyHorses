package net.msrandom.simplyhorses.init;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityEntryBuilder;
import net.msrandom.simplyhorses.SimplyHorses;
import net.msrandom.simplyhorses.entity.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class SHEntities {
    private static int id;
    public static final List<EntityEntry> REGISTERED = new ArrayList<>();
    public static final EntityEntry NORMAL = register("normal_horse", SHEntityHorse.class, SHEntityHorse::new, 0, 0);
    public static final EntityEntry PONY = register("pony_horse", EntityPonyHorse.class, EntityPonyHorse::new, 0, 0);
    public static final EntityEntry LIGHT = register("light_horse", EntityLightHorse.class, EntityLightHorse::new, 0, 0);
    public static final EntityEntry HEAVY = register("heavy_horse", EntityHeavyHorse.class, EntityHeavyHorse::new, 0, 0);
    public static final EntityEntry DRAFT = register("draft_horse", EntityDraftHorse.class, EntityDraftHorse::new, 0, 0);

    private static <T extends AbstractHorse> EntityEntry register(String name, Class<T> entityClass, Function<World, T> factory, int eggPrimary, int eggSecondary) {
        EntityEntryBuilder<T> builder = EntityEntryBuilder.create();
        EntityEntry entry = builder
                .entity(entityClass)
                .factory(factory)
                .name(SimplyHorses.MOD_ID + "." + name)
                .tracker(80, 3, true)
                .id(name, id++)
                .egg(eggPrimary, eggSecondary)
                .build();
        REGISTERED.add(entry);
        return entry;
    }
}
