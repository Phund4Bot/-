import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MaxElement {
    public static void main(String[] args) {
        int[][] data = new int[][]{
            {1, 2, 3, 4, 5},
            {5, 6, 65, 2, 1},
            {835, 273, 5, 34}
        };

        int numThreads = data.length;
        Future<Integer>[] results =  new Future[numThreads];
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        for (int i = 0; i < numThreads; i++) {
            int index = i;
            results[index] = executor.submit(() -> {
                int max = data[index][0];
                for (int j = 1; j < data[index].length; j++) {
                    max = (data[index][j] > max) ? data[index][j] : max;
                }
                return max;
            });
        }

        executor.shutdown();

        int maxElement = data[0][0];
        for (Future<Integer> result : results) {
            try {
                int number = result.get();
                maxElement = number > maxElement ? number : maxElement;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println(maxElement);
    }
}
