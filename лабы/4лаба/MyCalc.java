public class MyCalc {
    public static double operation(String op, double a, double b) throws CustomUnsupportedOperationException {
        switch (op) {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "*":
                return a*b;
            case "/":
                try {
                    return a/b;
                } catch (ArithmeticException e) {
                    System.out.println("Division by zero!");
                    
                    return 0;
                }
            default: {
                throw new CustomUnsupportedOperationException("Invalid operation!");
            }
                
        }
    }

    
}
