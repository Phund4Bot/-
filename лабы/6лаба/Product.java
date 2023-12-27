class Product implements Comparable<Product> {
    private String name;
    private double price;
    private int count;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
        this.count = 1;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public int getCount() {
        return this.count;
    }

    public void incrementCount() {
        this.count += 1;
    }

    @Override
    public int compareTo(Product otherProduct) {
        return this.name.compareTo(otherProduct.getName());
    }
}