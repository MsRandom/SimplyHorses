package net.msrandom.simplyhorses.entity;

import net.msrandom.genetics.Allele;
import net.msrandom.genetics.GeneticsRegistry;

public class HorseGenetics {
    private static final GeneticsRegistry REGISTRY = new GeneticsRegistry(SHEntityHorse.GENETICS::size, SHEntityHorse::createKey);
    public static final GeneticsRegistry.Gene<Agouti> AGOUTI = REGISTRY.register(new GeneticsRegistry.Gene<>(Agouti.class));

    public enum Agouti implements Allele {
        ;

        @Override
        public String getAllele() {
            return null;
        }
    }
}
