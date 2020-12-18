package net.msrandom.simplyhorses.entity.genetics;

import net.msrandom.simplyhorses.entity.SHEntityHorse;

public class Gene<T extends Enum<T> & Allele> {
    private static int current;
    public final T[] values;
    private final int size;
    private int pos;

    public Gene(Class<T> geneticClass) {
        this.values = geneticClass.getEnumConstants();
        this.size = (Integer.toBinaryString(values.length).length() - 1) * 2;
    }

    public int getSize() {
        return size;
    }

    public int getPos() {
        return pos;
    }

    public static <T extends Enum<T> & Allele> Gene<T> register(Gene<T> gene) {
        gene.pos = current;
        current += gene.size;
        if ((current / 32) > SHEntityHorse.GENETICS.size() - 1) SHEntityHorse.createKey();
        return gene;
    }
}
