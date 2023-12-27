public class ArrayAvg {
    public static int elementAvg(String[] arr) {
        int sum = 0;
        if (arr.length == 0) {
            return sum;
        }
        try {
            for (int i=0; i<arr.length; i++) {
                sum += Integer.parseInt(arr[i]);
            }
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Array index out");
            return 0;
        } catch (NumberFormatException error) {
            System.out.println("Invalid input");
            return 0;
        }

        return sum / arr.length;
    }

    public static void main(String[] args) {
        System.out.println(elementAvg(new String[]{"l", "1"}));
    }
}
