package net.msrandom.simplyhorses.horse.genetics;

import net.minecraft.util.IStringSerializable;

public class GeneticData<T extends Enum<T> & IStringSerializable> {
    private T a;
    private T b;

    public GeneticData(GeneticType<T> type) {
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public GeneticData<T> setup(T a, T b) {
        this.a = a;
        this.b = b;
        return this;
    }
}
