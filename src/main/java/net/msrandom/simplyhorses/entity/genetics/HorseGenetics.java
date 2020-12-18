package net.msrandom.simplyhorses.entity.genetics;

public class HorseGenetics {
    private static final GeneticsRegistry REGISTRY = new GeneticsRegistry();
    public static final GeneticsRegistry.Gene<Agouti> AGOUTI = REGISTRY.register(new GeneticsRegistry.Gene<>(Agouti.class));

    public enum Agouti implements Allele {
        ;

        @Override
        public String getAllele() {
            return null;
        }
    }
}
