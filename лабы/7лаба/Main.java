public class Main {
    public static void main(String[] args) {
        Loader loader1 = new Loader();
        Loader loader2 = new Loader();
        Loader loader3 = new Loader();
        int[] products = new int[]{30, 30, 30, 50, 70, 76, 78, 30, 30};
        Loader[] loaders = new Loader[]{loader1, loader2, loader3};
        Warehouse warehouse = new Warehouse(150, products, loaders);
        try{
            warehouse.start();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}