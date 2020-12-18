package net.msrandom.simplyhorses.entity.genetics;

public class HorseGenetics {
    public static final Gene<Agouti> AGOUTI = Gene.register(new Gene<>(Agouti.class));

    public enum Agouti implements Allele {
        ;

        @Override
        public String getAllele() {
            return null;
        }
    }
}
