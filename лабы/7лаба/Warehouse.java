import java.util.concurrent.Semaphore;

class Warehouse {
    private final Semaphore semaphore;
    private int[] products;
    private int totalWeight;
    private Loader[] loaders;

    public Warehouse(int totalWeight, int[] products, Loader[] loaders) {
        this.totalWeight = totalWeight;
        this.semaphore = new Semaphore(loaders.length);
        this.products = products;
        this.loaders = loaders;
    }

    public boolean isEmpty() {
        return this.products.length == 0;
    }

    private int popProduct() {
        if (this.isEmpty()) return -1;
        int res = this.products[this.products.length - 1];
        int[] newArr = new int[this.products.length-1];
        for (int i=0; i<products.length - 1; i++) {
            newArr[i] = products[i];
        }
        this.products = newArr;
        return res;
    }

    public void start() throws InterruptedException {
        while(!isEmpty()) {
            int maxWeight = this.totalWeight;
            semaphore.release(3);
            while (this.totalWeight > 0) {
                for (Loader loader : loaders) {
                    Thread.sleep(1000);
                    if (isEmpty() || this.totalWeight - this.products[this.products.length - 1] < 0) {
                        this.totalWeight = 0;
                        break;
                    }
                    int product = this.popProduct();
                    loader.getProduct(product);
                    this.totalWeight -= product;
                }
                
            }
            Thread.sleep(2000);
            semaphore.acquire(3);
            for (Loader loader : loaders) {
                new Thread(loader).start();
            }
            this.totalWeight = maxWeight;
        }
        System.out.println("Warehouse is empty");
    }
}
