package net.msrandom.simplyhorses.horse.genetics;

import net.minecraft.util.IStringSerializable;
import net.msrandom.simplyhorses.horse.genetics.EntitySHorse;

public class GeneticType<T extends Enum<T> & IStringSerializable> {
    private static int current;
    public final T[] values;
    public final int size;
    public final int pos;

    public GeneticType(Class<T> cls) {
        this.values = cls.getEnumConstants();
        this.size = (Integer.toBinaryString(values.length).length() - 1) * 2;
        this.pos = current;
        current += size;

        if ((current >> 5) > EntitySHorse.GENETICS.size() - 1) EntitySHorse.createKey();
    }
}
