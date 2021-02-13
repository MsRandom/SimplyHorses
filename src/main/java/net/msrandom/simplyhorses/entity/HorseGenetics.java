package net.msrandom.simplyhorses.entity;

import net.msrandom.genetics.Allele;
import net.msrandom.genetics.GeneticsRegistry;

public class HorseGenetics {
    private static final GeneticsRegistry REGISTRY = new GeneticsRegistry(SHEntityHorse.GENETICS::size, SHEntityHorse::createKey);
    public static final GeneticsRegistry.Gene<Extension> EXTENSION = REGISTRY.register(new GeneticsRegistry.Gene<>(Extension.class));
    public static final GeneticsRegistry.Gene<Agouti> AGOUTI = REGISTRY.register(new GeneticsRegistry.Gene<>(Agouti.class));
    public static final GeneticsRegistry.Gene<Grey> GREY = REGISTRY.register(new GeneticsRegistry.Gene<>(Grey.class));
    public static final GeneticsRegistry.Gene<CreamDilution> CREAM_DILUTION = REGISTRY.register(new GeneticsRegistry.Gene<>(CreamDilution.class));
    public static final GeneticsRegistry.Gene<DunDilution> DUN_DILUTION = REGISTRY.register(new GeneticsRegistry.Gene<>(DunDilution.class));
    public static final GeneticsRegistry.Gene<Champagne> CHAMPAGNE = REGISTRY.register(new GeneticsRegistry.Gene<>(Champagne.class));
    public static final GeneticsRegistry.Gene<White> WHITE = REGISTRY.register(new GeneticsRegistry.Gene<>(White.class));

    public enum Extension implements Allele {
        BLACK("E"),
        CHESTNUT("e");

        private final String allele;

        Extension(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Agouti implements Allele {
        WILD_BAY("A+"),
        BAY("A"),
        SEAL_BROWN("At"),
        NONE("a");

        private final String allele;

        Agouti(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Grey implements Allele {
        GREY("G"),
        NONE("g");

        private final String allele;

        Grey(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum CreamDilution implements Allele {
        PALOMINO("Cr"),
        PEARL("prl"),
        NONE("cr");

        private final String allele;

        CreamDilution(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum DunDilution implements Allele {
        RED_DUN("D"),
        NONE("d");

        private final String allele;

        DunDilution(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Champagne implements Allele {
        CHAMPAGNE("Ch"),
        NONE("ch");

        private final String allele;

        Champagne(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum White implements Allele {
        WHITE("W"),
        NONE("W+");

        private final String allele;

        White(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }
}
