package net.msrandom.simplyhorses.horse.genetics;

import net.minecraft.util.IStringSerializable;
import org.lwjgl.Sys;
import scala.Int;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class EntitySHorse {
    public static final List<AtomicInteger> GENETICS = new ArrayList<>();
    private final Map<GeneticType<?>, GeneticData<?>> geneticCache = new HashMap<>();

    private <E extends Enum<E> & IStringSerializable, T extends GeneticType<E>> void setData(T type, E a, E b) {
        int index = type.pos >> 5;
        int relative = type.pos - (index << 5);
        AtomicInteger parameter = GENETICS.get(index);
        parameter.set((parameter.get() & ~(((1 << type.size) - 1) << relative)) | (a.ordinal() << relative) | (b.ordinal() << (relative + (type.size >> 1))));
    }

    private <E extends Enum<E> & IStringSerializable, T extends GeneticType<E>> GeneticData<E> getData(T type) {
        GeneticData<E> data = (GeneticData<E>) geneticCache.computeIfAbsent(type, GeneticData::new);
        int index = type.pos >> 5;
        int relative = type.pos - (index << 5);
        int value = (GENETICS.get(index).get() >> relative) & ((1 << type.size) - 1);
        int size = type.size >> 1;
        int most = ((1 << size) - 1);
        return data.setup(type.values[value & most], type.values[(value >> size) & most]);
    }
    
    public static void main() {
        GeneticType<HorseGenetics.Agouti> a = new GeneticType<>(HorseGenetics.Agouti.class);
        GeneticType<HorseGenetics.Agouti> b = new GeneticType<>(HorseGenetics.Agouti.class);
        GeneticType<HorseGenetics.Agouti> c = new GeneticType<>(HorseGenetics.Agouti.class);
        GeneticType<HorseGenetics.Agouti> d = new GeneticType<>(HorseGenetics.Agouti.class);
        EntitySHorse s = new EntitySHorse();
        s.setData(d, HorseGenetics.Agouti.B, HorseGenetics.Agouti.A);
        s.setData(c, HorseGenetics.Agouti.D, HorseGenetics.Agouti.C);
        GeneticData<HorseGenetics.Agouti> bd = s.getData(d);
        System.out.println(bd.getA());
        System.out.println(bd.getB());
        bd = s.getData(c);
        System.out.println(bd.getA());
        System.out.println(bd.getB());
    }

    public static void createKey() {
        GENETICS.add(new AtomicInteger());
    }
}
