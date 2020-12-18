package net.msrandom.simplyhorses.entity.genetics;

public class Locus<T extends Enum<T> & Allele> {
    private T left;
    private T right;
    private T dominant;

    public T getLeft() {
        return left;
    }

    public T getRight() {
        return right;
    }

    public T getDominant() {
        return dominant;
    }

    public Locus<T> setup(T left, T right) {
        this.left = left;
        this.right = right;
        dominant = left.ordinal() < right.ordinal() ? left : right;
        return this;
    }

    @Override
    public String toString() {
        return left.getAllele() + "-" + right.getAllele();
    }
}
