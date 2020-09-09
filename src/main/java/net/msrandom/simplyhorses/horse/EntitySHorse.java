package net.msrandom.simplyhorses.horse;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.simplyhorses.horse.genetics.AlleleCarrier;
import net.msrandom.simplyhorses.horse.genetics.GeneticData;
import net.msrandom.simplyhorses.horse.genetics.GeneticType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntitySHorse extends AbstractHorse {
    public static final List<DataParameter<Integer>> GENETICS = new ArrayList<>();
    private final Map<GeneticType<?>, GeneticData<?>> geneticCache = new HashMap<>();

    public EntitySHorse(World worldIn) {
        super(worldIn);
    }

    @Override
    protected void entityInit() {
        super.entityInit();
        for (DataParameter<Integer> genetic : GENETICS) {
            dataManager.register(genetic, 0);
        }
    }

    private <E extends Enum<E> & AlleleCarrier, T extends GeneticType<E>> void setData(T type, E left, E right) {
        int index = type.pos >> 5;
        int relative = type.pos - (index << 5);
        DataParameter<Integer> parameter = GENETICS.get(index);
        dataManager.set(parameter, (dataManager.get(parameter) & ~(((1 << type.size) - 1) << relative)) | (left.ordinal() << relative) | (right.ordinal() << (relative + (type.size >> 1))));
    }

    @SuppressWarnings("unchecked")
    private <E extends Enum<E> & AlleleCarrier, T extends GeneticType<E>> GeneticData<E> getData(T type) {
        GeneticData<E> data = (GeneticData<E>) geneticCache.computeIfAbsent(type, k -> new GeneticData<>());
        int index = type.pos >> 5;
        int value = (dataManager.get(GENETICS.get(index)) >> (type.pos - (index << 5))) & ((1 << type.size) - 1);
        int size = type.size >> 1;
        int most = (1 << size) - 1;
        return data.setup(type.values[value & most], type.values[(value >> size) & most]);
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

    @SideOnly(Side.CLIENT)
    public String getTexture() {
        return "";
    }

    @SideOnly(Side.CLIENT)
    public String[] getSpots() {
        return new String[0];
    }

    public static void createKey() {
        GENETICS.add(EntityDataManager.createKey(EntitySHorse.class, DataSerializers.VARINT));
    }
}
