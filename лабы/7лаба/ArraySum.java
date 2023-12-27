import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ArraySum {
    public static void main(String[] args) {
        int[] data = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};

        int numThreads = 2;
        Future<Integer>[] results =  new Future[numThreads];
        ExecutorService executor = Executors.newFixedThreadPool(numThreads);

        int chunkSize = data.length / numThreads;
        int startIndex = 0;
        int endIndex = chunkSize;

        for (int i = 0; i < numThreads; i++) {
            int start = startIndex;
            int end = endIndex;
            results[i] = executor.submit(() -> {
                int sum = 0;
                for (int j = start; j < end; j++) {
                    sum += data[j];
                }
                return sum;
            });
            startIndex = endIndex;
            endIndex = (i == numThreads - 2) ? data.length : endIndex + chunkSize;
        }

        executor.shutdown();

        int totalSum = 0;
        for (Future<Integer> result : results) {
            try {
                totalSum += result.get();
            } catch (Exception e) {
                System.out.println(e);
                return;
            }
        }

        System.out.println(totalSum);
    }
}