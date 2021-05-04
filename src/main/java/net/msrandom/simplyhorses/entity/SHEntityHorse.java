package net.msrandom.simplyhorses.entity;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.msrandom.genetics.GeneticsRegistry;
import net.msrandom.genetics.GeneticsRegistry.Gene;
import net.msrandom.genetics.GenotypeHandler;
import net.msrandom.simplyhorses.entity.HorseGenetics.*;

import java.util.ArrayList;
import java.util.List;

public class SHEntityHorse extends AbstractHorse {
    public static final List<DataParameter<Integer>> GENETICS = new ArrayList<>();

    //These are here since we want GENETICS to be populated before the entity is created.
    private static final GeneticsRegistry REGISTRY = new GeneticsRegistry(GENETICS::size, () -> GENETICS.add(EntityDataManager.createKey(SHEntityHorse.class, DataSerializers.VARINT)));
    public static final Gene<Extension> EXTENSION = REGISTRY.register(Extension.class);
    public static final Gene<Agouti> AGOUTI = REGISTRY.register(Agouti.class);
    public static final Gene<Dun> DUN = REGISTRY.register(Dun.class);
    public static final Gene<Cream> CREAM = REGISTRY.register(Cream.class);
    public static final Gene<Champagne> CHAMPAGNE = REGISTRY.register(Champagne.class);
    public static final Gene<Silver> SILVER = REGISTRY.register(Silver.class);
    public static final Gene<Mushroom> MUSHROOM = REGISTRY.register(Mushroom.class);
    public static final Gene<Gray> GRAY = REGISTRY.register(Gray.class);
    public static final Gene<Tobiano> TOBIANO = REGISTRY.register(Tobiano.class);
    public static final Gene<LeopardComplex> LEOPARD_COMPLEX = REGISTRY.register(LeopardComplex.class);
    public static final Gene<FrameOvero> FRAME_OVERO = REGISTRY.register(FrameOvero.class);

    private final GenotypeHandler genotypeHandler = new GenotypeHandler(
            index -> dataManager.get(GENETICS.get(index)),
            (index, value) -> dataManager.set(GENETICS.get(index), value)
    );

    public SHEntityHorse(World world) {
        super(world);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        for (DataParameter<Integer> genetic : GENETICS) {
            dataManager.register(genetic, 0);
        }
    }

    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        super.writeEntityToNBT(compound);
        compound.setIntArray("Genetics", GENETICS.stream().mapToInt(genetic -> dataManager.get(genetic)).toArray());
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        super.readEntityFromNBT(compound);
        int[] genetics = compound.getIntArray("Genetics");
        for (int i = 0; i < Math.min(genetics.length, GENETICS.size()); ++i) {
            dataManager.set(GENETICS.get(i), genetics[i]);
        }
    }

    public int getVariant() {
        return 0;
    }

    public String getTypeName() {
        return "standard";
    }

    public GenotypeHandler getGenotypeHandler() {
        return genotypeHandler;
    }
}
