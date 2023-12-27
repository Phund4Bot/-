class Loader implements Runnable {
    private int productWeight;

    public Loader() {
        this.productWeight = 0;
    }

    public void getProduct(int product) {
        System.out.println("Loader take a product weight: " + product);
        this.productWeight += product;
    }

    @Override
    public void run() {
        try {
            System.out.println("A loader carries products weighing " + this.productWeight + " kg");
            Thread.sleep(0);
            this.productWeight = 0;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}