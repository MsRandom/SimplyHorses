package net.msrandom.simplyhorses.entity.genetics;

import net.msrandom.genetics.Allele;

public class HorseGenetics {
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
        AGOUTI("A"),
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

    public enum Dun implements Allele {
        DUN("D"),
        PRIMITIVE("ND1"),
        NONE("ND2");

        private final String allele;

        Dun(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Cream implements Allele {
        CREAM("Cr"),
        PEARL("prl"),
        SNOWDROP("sn"),
        NONE("n");

        private final String allele;

        Cream(String allele) {
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

    public enum Silver implements Allele {
        SILVER("Z"),
        NONE("z");

        private final String allele;

        Silver(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Mushroom implements Allele {
        MUSHROOM("Mu"),
        NONE("mu");

        private final String allele;

        Mushroom(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Gray implements Allele {
        GRAY("G"),
        NONE("g");

        private final String allele;

        Gray(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum Tobiano implements Allele {
        TOBIANO("To"),
        NONE("to");

        private final String allele;

        Tobiano(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum LeopardComplex implements Allele {
        LEOPARD("Lp"),
        NONE("lp");

        private final String allele;

        LeopardComplex(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }

    public enum FrameOvero implements Allele {
        FRAME_OVERO("O"),
        NONE("o");

        private final String allele;

        FrameOvero(String allele) {
            this.allele = allele;
        }

        @Override
        public String getAllele() {
            return allele;
        }
    }
}
