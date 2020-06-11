package net.msrandom.simplyhorses.horse.genetics;

import net.minecraft.util.IStringSerializable;

public class HorseGenetics {
    public static final GeneticType<Agouti> AGOUTI = new GeneticType<>(Agouti.class);

    public enum Agouti implements IStringSerializable {
        A, B, C, D;

        @Override
        public String getName() {
            return null;
        }
    }
}
