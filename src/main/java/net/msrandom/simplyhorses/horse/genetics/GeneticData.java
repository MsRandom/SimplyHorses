package net.msrandom.simplyhorses.horse.genetics;

public class GeneticData<T extends Enum<T> & AlleleCarrier> {
    private T left;
    private T right;

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    public GeneticData<T> setup(T left, T right) {
        this.left = left;
        this.right = right;
        return this;
    }

    @Override
    public String toString() {
        return left.getAllele() + "-" + right.getAllele();
    }
}
