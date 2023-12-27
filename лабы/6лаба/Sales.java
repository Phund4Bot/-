import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

public class Sales {
    private TreeSet<Product> data;

    public Sales() {
        this.data = new TreeSet<Product>();
    }

    public void addProduct(String name, double price) {
        if(!data.add(new Product(name, price))) {
            Iterator<Product> iterator = data.iterator();
            while(iterator.hasNext()) {
                Product product = iterator.next();
                if(product.getName() == name) {
                    product.incrementCount();
                    return;
                }
            }
        };
    }

    public void printProducts() {
        for (Product product : data) {
            System.out.println(product.getName() + " " + product.getPrice());
        }
    }

    public double totalSales() {
        double res = 0;
        for (Product product : data) {
            res += product.getPrice() * product.getCount();
        }
        return res;
    }

    public ArrayList<String> getMostPopularProduct() {
        if (data.isEmpty()) {
            return null;
        }

        Map<String, Integer> map = new HashMap<>();
        for (Product product : data) {
            map.put(product.getName(), product.getCount());
        }

        ArrayList<String> res = new ArrayList<String>();
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= maxCount) {
                if (entry.getValue() > maxCount) {
                    res.clear();
                    maxCount = entry.getValue();
                }
                res.add(entry.getKey());
            }
        }

        return res;
    }
}
