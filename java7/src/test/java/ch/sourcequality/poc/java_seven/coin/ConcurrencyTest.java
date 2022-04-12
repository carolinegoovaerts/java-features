package ch.sourcequality.poc.java_seven.coin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

class ConcurrencyTest {

    // example based on https://docs.oracle.com/javase/7/docs/api/java/util/concurrent/RecursiveAction.html
    // TODO understand how it works + refactor

    private static double sumOfSquares(ForkJoinPool pool, double[] array) {
        SumOfSquaresAction action = new SumOfSquaresAction(array, 0, array.length, null);
        pool.invoke(action);

        return action.result;
    }

    @Test
    void calculateSumOfSquaresUsingForkJoinPool() {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();
        final double[] values = {1, 2, 4};
        final double sumOfSquares = sumOfSquares(forkJoinPool, values);

        Assertions.assertEquals(21, sumOfSquares);
    }

    private static class SumOfSquaresAction extends RecursiveAction {
        private final double[] array;
        private final int min, max;
        private final SumOfSquaresAction next; // keeps track of right-hand-side tasks
        private double result;

        SumOfSquaresAction(double[] array, int min, int max, SumOfSquaresAction next) {
            this.array = array;
            this.min = min;
            this.max = max;
            this.next = next;
        }

        @Override
        protected void compute() {
            int min = this.min;
            int max = this.max;
            SumOfSquaresAction right = null;

            while (max - min > 1 && getSurplusQueuedTaskCount() <= 3) {
                int mid = (min + max) >>> 1;
                right = new SumOfSquaresAction(array, mid, max, right);
                right.fork();
                max = mid;
            }
            double sum = atLeaf(min, max);
            while (right != null) {
                if (right.tryUnfork()) { // directly calculate if not stolen
                    sum += right.atLeaf(right.min, right.max);
                } else {
                    right.join();
                    sum += right.result;
                }
                right = right.next;
            }
            result = sum;
        }

        private double atLeaf(int min, int max) {
            double sum = 0;
            for (int i = min; i < max; ++i) { // perform leftmost base step
                sum += array[i] * array[i];
            }
            return sum;
        }
    }
}
