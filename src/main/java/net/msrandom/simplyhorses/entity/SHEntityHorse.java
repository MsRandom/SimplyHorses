package net.msrandom.simplyhorses.entity;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.simplyhorses.entity.genetics.Allele;
import net.msrandom.simplyhorses.entity.genetics.Locus;
import net.msrandom.simplyhorses.entity.genetics.Gene;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SHEntityHorse extends AbstractHorse {
    public static final List<DataParameter<Integer>> GENETICS = new ArrayList<>();
    private final Map<Gene<?>, Locus<?>> geneticCache = new HashMap<>();

    public SHEntityHorse(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        for (DataParameter<Integer> genetic : GENETICS) {
            dataManager.register(genetic, 0);
        }
    }

    private <E extends Enum<E> & Allele> void setGenotype(Gene<E> type, E left, E right) {
        int index = type.pos / 32;
        int relative = type.pos - (index * 32);
        DataParameter<Integer> parameter = GENETICS.get(index);
        dataManager.set(parameter, (dataManager.get(parameter) & ~(((1 << type.size) - 1) << relative)) | (left.ordinal() << relative) | (right.ordinal() << (relative + (type.size >> 1))));
    }

    @SuppressWarnings("unchecked")
    private <E extends Enum<E> & Allele> Locus<E> getGenotype(Gene<E> type) {
        Locus<E> locus = (Locus<E>) geneticCache.computeIfAbsent(type, k -> new Locus<>());
        int index = type.pos / 32;
        int value = (dataManager.get(GENETICS.get(index)) >> (type.pos - (index * 32))) & ((1 << type.size) - 1);
        int size = type.size >> 1;
        int most = (1 << size) - 1;
        return locus.setup(type.values[value & most], type.values[(value >> size) & most]);
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
        for (int i = 0; i < genetics.length; ++i) {
            if (i >= GENETICS.size()) createKey();
            dataManager.set(GENETICS.get(i), genetics[i]);
        }
    }

    public int getVariant() {
        return 0;
    }

    //TODO
    @SideOnly(Side.CLIENT)
    public String getTexture() {
        return "";
    }

    @SideOnly(Side.CLIENT)
    public String[] getSpots() {
        return new String[0];
    }

    public static void createKey() {
        GENETICS.add(EntityDataManager.createKey(SHEntityHorse.class, DataSerializers.VARINT));
    }
}
