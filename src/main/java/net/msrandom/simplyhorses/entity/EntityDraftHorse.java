package net.msrandom.simplyhorses.entity;

import net.minecraft.world.World;

public class EntityDraftHorse extends SHEntityHorse {
    public EntityDraftHorse(World world) {
        super(world);
    }

    @Override
    public int getVariant() {
        return 1;
    }

    @Override
    public String getTypeName() {
        return "draft";
    }
}
