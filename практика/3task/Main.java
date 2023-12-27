import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.Set;    

public class Main {
    public static void main(String[] args) {
        System.out.println(replaceVovels("apple"));

        System.out.println();
        System.out.println(stringTransform("addD"));

        System.out.println();
        System.out.println(doesBlockFit(1, 3,5, 4, 5));
        System.out.println(doesBlockFit(1, 8, 1, 1, 1));
        System.out.println(doesBlockFit(1, 2, 2, 1, 1));

        System.out.println();
        System.out.println(numCheck(243));
        System.out.println(numCheck(52));

        System.out.println();
        System.out.println(countRoots(1, -3, 2));
        System.out.println(countRoots(2, 5, 2));
        System.out.println(countRoots(1, -6, 9));

        System.out.println();
        salesData(new String[][]{{"Apple", "Shop1", "Shop2", "Shop3", "Shop4"},{"Banana", "Shop2", "Shop3", "Shop4"},
        {"Orange", "Shop1", "Shop3", "Shop4"}, {"Pear", "Shop2", "Shop4"}});  
        System.out.println();
        salesData(new String[][]{
        {"Fridge", "Shop2", "Shop3"},
        {"Microwave", "Shop1", "Shop2", "Shop3", "Shop4"},
        {"Laptop", "Shop3", "Shop4"},
        {"Phone", "Shop1", "Shop2", "Shop3", "Shop4"}
        }
        );

        System.out.println();
        System.out.println();
        System.out.println(validSplit("apple eagle egg goat"));
        System.out.println(validSplit("cat dog goose fish"));
        System.out.println(validSplit("aa ac cb"));
        System.out.println(validSplit("ea ea ea ae"));
        System.out.println(validSplit("ab aa aa ca"));
        
        System.out.println();
        System.out.println(waveForm(new int[] {3, 1, 4, 2, 7, 5}));
        System.out.println(waveForm(new int[] {1, 2, 3, 4, 5}));
        System.out.println(waveForm(new int[] {1, 2, -6, 10, 3}));

        System.out.println();
        System.out.println(commonVovel("Hello world"));
        System.out.println(commonVovel("Actions speak louder than words."));

        System.out.println();
        System.out.println();
        dataScience(new int[][]{{1, 2, 3, 4, 5},
                                {6, 7, 8, 29, 10},
                                {5, 5, 5, 5, 35},
                                {7, 4, 3, 14, 2},
                                {1, 0, 11, 10, 1}
                                });
        
        dataScience(new int[][] {{6, 4, 19, 0, 0},
                                {81, 25, 3, 1, 17},
                                {48, 12, 60, 32, 14},
                                {91, 47, 16, 65, 217},
                                {5, 73, 0, 4, 21}
                                });


    }

    public static String replaceVovels(String s) {
        char[] chars = new char[] {'a', 'e', 'i', 'o', 'u', 'y'};
        for(int i = 0; i < chars.length; i++) {
            s = s.replace(chars[i], '*').replace(Character.toUpperCase(chars[i]), '*');
        }
        return s;
    }

    public static String stringTransform(String s) {
        s+=" ";
        String result = "";
        for(int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == s.charAt(i + 1)) {
                result += "Double" + Character.toUpperCase(s.charAt(i));
                i++;
                continue;
            }
            result+=s.charAt(i);
        }
        return result;
    }

    public static boolean doesBlockFit(int a, int b, int c, int w, int h) {
        int m1 = Math.min(a, Math.min(b, c));
        int m2 = a + b + c - m1 - Math.max(a, Math.max(b, c));
        if(Math.min(w, h) >= m1 && Math.max(w, h) >= m2) return true;
        return false;
    }

    public static boolean numCheck(int num) {
        int sum = 0;
        String s = String.valueOf(num);
        for(int i = 0; i < s.length(); i++) {
            sum += (int)s.charAt(i) * (int)s.charAt(i);
        }
        return (num % 2 == sum % 2) ? true : false;
    }

    public static int countRoots(int a, int b, int c) {
        int D = b*b - 4*a*c;
        if(D == 0 && -b % (2*a) == 0) return 1;
        if(D > 0) {
            int count = 0;
            if(((-b + Math.sqrt(D)) % (2*a)) == 0) count++;
            if(((-b - Math.sqrt(D)) % (2*a)) == 0) count++;
            return count;
        }
        return 0;
    }

    public static void salesData(String[][] arr) {
        ArrayList<String> shops = new ArrayList<>();
        for (String[] arr1 : arr) {
            for(int i = 1; i < arr1.length; i++) shops.add(arr1[i]);
        }
        for (String[] arr1 : arr) {
            if(arr1.length == shops.stream().distinct().count() + 1) System.out.print(arr1[0] + " ");
        }       
    }

    public static boolean validSplit(String s)  {
        String[] words = s.split(" ");
        Character[] firstChars = new Character[words.length];
        Character[] lastChars = new Character[words.length];

        for(int i = 0; i < words.length; i++) {
            firstChars[i] = words[i].charAt(0);
            lastChars[i] = words[i].charAt(words[i].length() - 1);
        }

        int count = 0;
        for(int i = 0; i < firstChars.length; i++) {
            for(int j = 0; j < firstChars.length; j++) {
                if(firstChars[i] == lastChars[j]) {
                    lastChars[j] = ' ';
                    count++;
                    break;
                }
            }
        }
        return (count) == words.length - 1;

    }

    public static boolean waveForm(int[] arr) {
        for(int i = 1; i < arr.length - 1; i++) {
            if(!((arr[i] < arr[i - 1] && arr[i] < arr[i + 1]) || (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]))) return false;
        }
        return true;
    }

    public static char commonVovel(String s) {
        HashMap<Character, Integer> map = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            if(map.containsKey(s.charAt(i)) && s.charAt(i) != ' ') map.put(s.charAt(i), map.get(s.charAt(i)) + 1);
            else if(s.charAt(i) != ' ') map.put(s.charAt(i), 1);
        }

        int maxLen = 0;
        char maxChar = ' ';
        for (char c : map.keySet()) {
            if(map.get(c) > maxLen) {
                maxLen = map.get(c);
                maxChar = c;
            }    
        }
        return maxChar;
    }

    public static int[][] dataScience(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            int sum = 0;
            for(int j = 0; j < arr.length; j++) {
                if(i != j) sum+=arr[j][i];
            }
            arr[i][i] = Math.round(sum / (float)(arr.length - 1));
        }

        for (int[] is : arr) {
            for (int item : is) {
                System.out.print(item + " ");
            }
            System.out.println();
        }
        return arr;
    }

    
}