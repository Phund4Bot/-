import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        

        System.out.println("duplicateChars---------------------------------");
        System.out.println(duplicateChars("Donald"));
        System.out.println(duplicateChars("orange"));
        System.out.println();

        System.out.println("getInitials---------------------------------");
        System.out.println(getInitials("Ryan Gosling"));
        System.out.println(getInitials("Barack Obama"));
        System.out.println();

        System.out.println("differenceEvenOdd---------------------------------");
        System.out.println(differenceEvenOdd(new int[] {44, 32, 86, 19 }));
        System.out.println(differenceEvenOdd(new int[] {22, 50, 16, 63, 31, 55 }));
        System.out.println();

        System.out.println("equalToAvg---------------------------------");
        System.out.println(equalToAvg(new int[] {1, 2, 3, 4, 5}));
        System.out.println(equalToAvg(new int[] {1, 2, 3, 4, 6}));
        System.out.println();

        System.out.println("indexMult---------------------------------");
        System.out.println(indexMult(new int[] {1, 2, 3}));
        System.out.println(indexMult(new int[] {3, 3, -2, 408, 3, 31}));
        System.out.println();

        System.out.println("reverse---------------------------------");
        System.out.println(reverse("Hello World"));
        System.out.println(reverse("The quick brown fox."));
        System.out.println();

        System.out.println("Tribonacci---------------------------------");
        System.out.println(Tribonacci(7));
        System.out.println(Tribonacci(11));
        System.out.println();

        System.out.println("pseudoHash---------------------------------");
        System.out.println(pseudoHash(5));
        System.out.println(pseudoHash(10));
        System.out.println(pseudoHash(0));
        System.out.println();

        System.out.println("botHelper---------------------------------");
        System.out.println(botHelper("Hello, I’m under the water, she help! me"));
        System.out.println(botHelper("Two pepperoni pizzas please"));
        System.out.println();

        System.out.println("isAnagram---------------------------------");
        System.out.println(isAnagram("listen", "silent"));
        System.out.println(isAnagram("eleven plus two", "twelve plus one"));
        System.out.println(isAnagram("hello", "world"));
        System.out.println();

    }

    public static boolean duplicateChars(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            for(int j = i + 1; j < chars.length; j ++) {
                if (Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])) return true;
            }
        }
        return false;
    }

    public static String getInitials(String s) {
        String[] words = s.split(" ");
        return String.valueOf(words[0].charAt(0)).concat(String.valueOf(words[1].charAt(0)));
    }

    public static int differenceEvenOdd(int[] arr) {
        int diff = 0;
        for (int num: arr) {
            if (num % 2 == 0) diff += num;
            else diff -= num;
        }
        return Math.abs(diff);
    }

    public static boolean equalToAvg(int[] arr) {
        float sum = (float) Arrays.stream(arr).sum() / (float) arr.length;
        for (float num: arr) {
            if (num == sum) return true;
        }
        return false;
    }

    public static String indexMult(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr[i] * i;
        }
        return Arrays.toString(arr);
    }

    public static String reverse(String s) {
        char[] chars = s.toCharArray();
        String result = "";
        for (int i = chars.length - 1; i >= 0; i--) {
            result += String.valueOf(chars[i]);
        }
        return result;
    }

    public static int Tribonacci(int num) {
        int[] arr = new int[num];
        arr[0] = 0;
        arr[1] = 0;
        arr[2] = 1;
        for (int i = 3; i < arr.length; i++) {
            arr[i] = arr[i - 1] + arr[i - 2] + arr[i - 3];
        }
        return arr[arr.length - 1];
    }

    public static String pseudoHash(int len) {
        String result = "";
        String[] chars = new String[] {"a", "b", "c", "d", "e", "f", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        Random rand = new Random();
        for (int i = 0; i < len; i++)
        {
            result += chars[rand.nextInt(16)];
        }
        return result;
    }

    public static String botHelper(String s) {
        s = s.replaceAll("[.!?,;:]", " ");
        String[] words = s.split(" ");
        for (String word : words) {
            if(word.equalsIgnoreCase("help")) return "Calling for a staff member";
        }
        return "Keep waiting";
    }

    public static boolean isAnagram(String s1, String s2) {
        if (s1.length() != s2.length()) return false;
        HashMap<Character, Integer> dict = new HashMap<>();

        for (int i = 0; i < s1.length(); i++) {
            if (dict.containsKey(s1.charAt(i))) dict.put(s1.charAt(i), dict.get(s1.charAt(i)) + 1);
            else dict.put(s1.charAt(i), 1);

            if (dict.containsKey(s2.charAt(i))) dict.put(s2.charAt(i), dict.get(s2.charAt(i)) - 1);
            else dict.put(s2.charAt(i), -1);
        }
        for (int num: dict.values()) {
            if (num != 0) return false;
        }
        return true;
    }
}







