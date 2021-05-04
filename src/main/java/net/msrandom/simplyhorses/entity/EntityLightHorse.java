package net.msrandom.simplyhorses.entity;

import net.minecraft.world.World;

public class EntityLightHorse extends SHEntityHorse {
    public EntityLightHorse(World world) {
        super(world);
    }

    @Override
    public int getVariant() {
        return 3;
    }

    @Override
    public String getTypeName() {
        return "light";
    }
}
