package ch.sourcequality.poc.java7.coin;

import java.util.concurrent.RecursiveAction;

class SquaresTask extends RecursiveAction {

    private static final int SMALL_ENOUGH = 2;
    private final long[] array;
    private final int min;
    private final int max;

    SquaresTask(long[] array, int min, int max) {
        this.array = array;
        this.min = min;
        this.max = max;
    }

    @Override
    protected void compute() {
        if (isWorkLoadSmallEnough()) {
            conquer();
        } else {
            divide();
        }
    }

    private void divide() {
        final int mid = (min + max) >>> 1;
        SquaresTask left = new SquaresTask(array, min, mid);
        SquaresTask right = new SquaresTask(array, mid, max);

        invokeAll(left, right);
    }

    private void conquer() {
        for (int i = min; i < max; ++i) {
            array[i] *= array[i];
        }
    }

    private boolean isWorkLoadSmallEnough() {
        return max - min <= SMALL_ENOUGH;
    }
}
