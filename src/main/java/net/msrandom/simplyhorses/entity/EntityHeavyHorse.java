package net.msrandom.simplyhorses.entity;

import net.minecraft.world.World;

public class EntityHeavyHorse extends SHEntityHorse {
    public EntityHeavyHorse(World world) {
        super(world);
    }

    @Override
    public int getVariant() {
        return 2;
    }

    @Override
    public String getTypeName() {
        return "heavy";
    }
}
