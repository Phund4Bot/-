import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main {
    public static void main(String[] args) {
        double res1, res2;
        try{
            res1 = MyCalc.operation("/", 2, 3);
            System.out.println(res1);
            res2 = MyCalc.operation("&", 4, 1);
            System.out.println(res2);
        } catch (CustomUnsupportedOperationException e) {
            logException(e);
            System.out.println(e);
        }
    }

    public static void logException(Exception e) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("error.log", true))) {
            e.printStackTrace(writer);
        } catch (IOException exception) {
            System.out.println("Error: " + exception);
        }
    }
}
