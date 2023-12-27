
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        System.out.println(nonRepeatable("abracadabra")); // 21

        System.out.println(generateBrackets(3)); // 22

        System.out.println(binarySystem(3)); // 23

        System.out.println(alphabeticRow("klmabzyxw")); // 24

        System.out.println(inRow("vvvvaajaaaaa")); // 25 

        System.out.println(convertToNum("one")); // 26

        System.out.println(uniqueSubstring("77897898")); // 27

        System.out.println(shortestWay(new int[][] {
            {2, 7, 3},
            {1, 4, 8}, 
            {4, 5, 9}
        })); // 28

        System.out.println(numericOrder("re6sponsibility Wit1h gr5eat power3 4comes g2reat")); // 29

        System.out.println(switchNums(519, 723)); // 30
    }

    public static String nonRepeatable(String a) {
        if (a == null || a.length() <= 1) {
            return a;
        }
        String firstChar = ""+ a.charAt(0);
        String remaining = nonRepeatable(a.substring(1));
        remaining = remaining.replace(firstChar, "");
        return firstChar + remaining;
    } // 21

    public static void backtrackBrackets(ArrayList<String> list, String str, int open, int close, int max) {
        if (str.length() == max * 2) {
            list.add(str);
            return;
        }

        if (open < max) {
            backtrackBrackets(list, str + "(", open + 1, close, max);
        }
        if (close < open) {
            backtrackBrackets(list, str + ")", open, close + 1, max);
        }

    }

    public static ArrayList<String> generateBrackets(int n) {
        ArrayList<String> list = new ArrayList<String>();
        backtrackBrackets(list, "", 0, 0, n);
        return list;
    } // 22

    public static void backtrackBinary(ArrayList<String> list, String str, int one, int zero, int max) {
        if (str.length() == max) {
            list.add(str);
            return;
        }

        if (str.length() == 0 || str.charAt(str.length() - 1) != '0') {
            backtrackBinary(list, str + "0", one, zero + 1, max);
        }
        if (one + zero < max) {
            backtrackBinary(list, str + "1", one + 1, zero, max);
        }
    }

    public static List<String> binarySystem(int n) {
        ArrayList<String> list = new ArrayList<String>();
        backtrackBinary(list, "", 0, 0, n);
        return list;
    } // 23

    public static String alphabeticRow(String s) {
        String maxLenForw = "";
        String currForw = String.valueOf(s.charAt(0));

        String maxLenBack = "";
        String currBack = String.valueOf(s.charAt(s.length() - 1));

        for(int i = 0; i < s.length() - 1; i++)
        {
            char thisElement = s.charAt(i);
            char nextElement = s.charAt(i+1);
            char prevElement = s.charAt(s.length()-1-i);
            char prevprevElement = s.charAt(s.length()-2-i);
            if((int)thisElement - (int)nextElement == -1) {
                currForw += nextElement;
            } else {
                if(maxLenForw.length() < currForw.length())  {
                    maxLenForw = currForw;
                }
                currForw = String.valueOf(nextElement);
            }
            
            if ((int)prevElement - (int)prevprevElement == -1) {
                currBack += prevprevElement;
            } else {
                if (maxLenBack.length() < currBack.length()) {
                    maxLenBack = currBack;
                }
                currBack = String.valueOf(prevprevElement);
            }
        }

        return (maxLenBack.length() > maxLenForw.length()) ? new StringBuilder(maxLenBack).reverse().toString() : maxLenForw;
    } // 24

    public static String inRow(String s) {
        ArrayList<String> words = new ArrayList<>();
        String word = String.valueOf(s.charAt(0));

        for(int i = 0; i < s.length() - 1; i++)
        {
            if(s.charAt(i) == s.charAt(i + 1)) {
                word += s.charAt(i + 1);
            } else {
                words.add(word);
                word = String.valueOf(s.charAt(i + 1));
            }
        }
        words.add(word);

        List<String> sortedList = words.stream().sorted( (str1,str2) ->  str1.length() - str2.length()).collect(Collectors.toList());

        String result = "";
        for (String str : sortedList) {
            result += str.charAt(0) + String.valueOf(str.length());
        }
        return result;
    } // 25

    public static int convert0to9(String s) {
        switch(s) {
            case "one": {return 1;}
            case "two": {return 2;}
            case "three": {return 3;}
            case "four": {return 4;}
            case "five": {return 5;}
            case "six": {return 6;}
            case "seven": {return 7;}
            case "eight": {return 8;}
            case "nine": {return 9;}
            default: {return 0;}
        }
    }

    public static int convert10to90(String s) {
        String number = s.substring(0, s.length() - 2);
        if (number.compareToIgnoreCase("twen") == 0) return 20;
        else if (number.compareToIgnoreCase("thir") == 0) return 30;
        else if (number.compareToIgnoreCase("for") == 0) return 40;
        else if (number.compareToIgnoreCase("fif") == 0) return 50;
        else if (number.compareToIgnoreCase("eigh") == 0) return 80;
        else return convert0to9(number) * 10;
    }

    public static int convertToNum(String s) {
        int res = 0;
        String[] arr = s.split(" ");
        for(int i=0; i<arr.length; i++) {
            if (arr[i].endsWith("ty")) {
                res += convert10to90(arr[i]);
            } else if (arr[i].compareToIgnoreCase("hundred") == 0) {
                res *= 100;
            } else if (arr[i].compareToIgnoreCase("ten") == 0) {
                res += 10;
            } else {
                res += convert0to9(arr[i]);
            }
        }
        return res;
    } // 26

    public static String uniqueSubstring(String s) {
        if (s.length() <= 1) return s;
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int length = 0;
        String res = "";
        for (int i=0; i<s.length(); i++) {
            char thisChar = s.charAt(i);
            if(!map.containsKey(thisChar)) {
                map.put(thisChar, 1);
                length++;
                if (length > res.length()) {
                    res = s.substring(i+1-map.size(), i+1);
                }
            } else if (s.charAt(i) == s.charAt(i-1)) {
                map.clear();
                map.put(thisChar, 1);
            }
        }
        return res;
    } // 27

    public static int shortestWay(int[][] matrix) {
        int n = matrix.length;
        int[][] dp = new int[n][n];
        dp[0][0] = matrix[0][0];
        
        for (int i = 1; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + matrix[i][0];
        }
        
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + matrix[0][j];
        }
        
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = matrix[i][j] + Math.min(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[n - 1][n - 1];
    } // 28

    public static String numericOrder(String s) {
        String[] arr = s.split(" ");
        String[] interArr = new String[arr.length];
        for (int i=0; i<arr.length; i++) {
            String thisWord = arr[i];
            String resWord = "";
            int resIndex = 0;
            for(int j=0; j<thisWord.length(); j++) {
                String thisSymbol = String.valueOf(thisWord.charAt(j));
                try {
                    resIndex = Integer.parseInt(thisSymbol) - 1;
                } catch (NumberFormatException e) {
                    resWord += thisSymbol;
                }
            }
            interArr[resIndex] = resWord;
        }
        return String.join(" ", interArr);
    } // 29

    public static int switchNums(int a, int b) {
        char[] aChars = (String.valueOf(a)).toCharArray();
        char[] bChars = (String.valueOf(b)).toCharArray();

        Arrays.sort(aChars);
        
        int index = aChars.length - 1;
        for (int i = 0; i < bChars.length; i++) {
            if (index >= 0 && bChars[i] < aChars[index]) {
                bChars[i] = aChars[index];
                index--;
            }
        }

        return Integer.valueOf(String.valueOf(bChars));
    } // 30

    
}