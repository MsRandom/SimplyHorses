package net.msrandom.simplyhorses.horse;

import net.minecraft.entity.passive.AbstractHorse;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.IStringSerializable;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.msrandom.simplyhorses.horse.genetics.GeneticData;
import net.msrandom.simplyhorses.horse.genetics.GeneticType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("unchecked")
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

    private <E extends Enum<E> & IStringSerializable, T extends GeneticType<E>> void setData(T type, E a, E b) {
        int index = type.pos >> 5;
        int relative = type.pos - (index << 5);
        DataParameter<Integer> parameter = GENETICS.get(index);
        dataManager.set(parameter, (dataManager.get(parameter) & -(type.size << 1)) | (a.ordinal() << relative) | (b.ordinal() << (type.size >> 1) << relative));
    }

    private <E extends Enum<E> & IStringSerializable, T extends GeneticType<E>> GeneticData<E> getData(T type) {
        GeneticData<E> data = (GeneticData<E>) geneticCache.computeIfAbsent(type, GeneticData::new);
        int index = type.pos >> 5;
        int bits = (dataManager.get(GENETICS.get(index)) >> (type.pos - (index << 5))) & ((1 << type.size) - 1);
        int size = Integer.highestOneBit(bits) * 2 - 1;
        int most = ((1 << size) - 1);
        return data.setup(type.values[size & most], type.values[(size >> (size >> 1)) & most]);
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
