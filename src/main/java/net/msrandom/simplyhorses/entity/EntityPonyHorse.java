package net.msrandom.simplyhorses.entity;

import net.minecraft.world.World;

public class EntityPonyHorse extends SHEntityHorse {
    public EntityPonyHorse(World world) {
        super(world);
    }

    @Override
    public int getVariant() {
        return 4;
    }

    @Override
    public String getTypeName() {
        return "pony";
    }
}
