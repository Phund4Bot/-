public class Main {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop()); 
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());

        Sales sales = new Sales();
        sales.addProduct("banana", 50.0);
        sales.addProduct("apple", 30.0);
        sales.addProduct("cheese", 20.0);
        sales.printProducts();
        System.out.println(sales.totalSales());
        sales.addProduct("banana", 50.0);
        System.out.println(sales.totalSales());
        System.out.println(sales.getMostPopularProduct());
    }
}
