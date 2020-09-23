package net.msrandom.simplyhorses.entity.genetics;

public class HorseGenetics {
    public static final GeneticType<Agouti> AGOUTI = new GeneticType<>(Agouti.class);

    public enum Agouti implements AlleleCarrier {
        ;

        @Override
        public String getAllele() {
            return null;
        }
    }
}
