package net.msrandom.simplyhorses.entity.genetics;

import net.msrandom.simplyhorses.entity.SHEntityHorse;

public class GeneticType<T extends Enum<T> & AlleleCarrier> {
    private static int current;
    public final T[] values;
    public final int size;
    public final int pos;

    public GeneticType(Class<T> geneticClass) {
        this.values = geneticClass.getEnumConstants();
        this.size = (Integer.toBinaryString(values.length).length() - 1) * 2;
        this.pos = current;
        current += size;

        if ((current >> 5) > SHEntityHorse.GENETICS.size() - 1) SHEntityHorse.createKey();
    }
}
