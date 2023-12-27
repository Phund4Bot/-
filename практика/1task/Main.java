public class Main {
    public static void main(String[] args) {
        System.out.println("21345667");
    }

    public static float convert(int x) {
        return x*3.785f;
    }

    public static int fitCalc(int time, int intensity) {
        return time*intensity;
    }

    public static int containers(int a, int b, int c) {
        return a*20 + b*50 + c*100;
    }

    public static string triangleType(int x, int y, int z) {
        if(x == y & y == z) return "isosceles";
        else if(x == y | y == z | x == z) return "equilateral";
        else if(x != y & y != z & z != x)
        {
            if (x + y > z & x + z > y & y + z > x) return "different-sides";
            return "not a triangle";
        }
    }

    public static int ternaryEvaluation(int x, int y) {
        return (x > y) ? x : y;
    }

    public static int howManyItems(int n, float w, float h) {
        return (n / (w*h*2));
    }

    public static int factorial(int x) {
        int sum = x;
        for(int i = x - 1; i >= 1; i--) sum = sum + sum * x;
        return sum;      
    }
}