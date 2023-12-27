import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println(sameLetterPattern("abab", "cdcd")); // 1
        System.out.println(spiderVsFly("A4", "H4")); // 2
        System.out.println(digitsCount(1289396387328L)); // 3
        System.out.println(totalPoints(new String[] { "trance", "recant" }, "recant")); // 4
        System.out.println(totalPoints(new String[] { "dote", "dotes", "toes", "set", "dot", "dots", "sted" }, "tossed")); // 4
        System.out.println(sumsUp(new int[] { 1, 6, 5, 4, 8, 2, 3, 7 })); // 5
        System.out.println(takeDownAverage(new String[] { "95%", "83%", "90%", "87%", "88%", "93%" })); // 6
        System.out.println(caesarCipher("decode", "almost last task!", 4)); // 7
        System.out.println(setSetup(7, 3)); // 8
        System.out.println(timeDifference("Los Angeles", "April 1, 2011 23:23", "Canberra")); // 9
        System.out.println(isNew(13)); // 10
    }

    public static String printTwoDimensialArray(int[][] arr) {
        String res = "[";
        for (int[] i : arr) {
            res += "[";
            for (int el : i) {
                res += el + ", ";
            }
            res = res.substring(0, res.length() - 2);
            res += "], ";
        }
        res = res.substring(0, res.length() - 2);
        res += "]";
        return res;
    }

    public static boolean sameLetterPattern(String s, String t)
    {
        if(s.length() != t.length()) {
            return false;
        }

        HashMap<Character, Integer> smap = new HashMap<>();
        HashMap<Character, Integer> tmap = new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            if(smap.get(s.charAt(i)) != tmap.get(t.charAt(i))) return false;
            smap.put(s.charAt(i), i);
            tmap.put(t.charAt(i), i);
        }
        return true;
    } // 1

    public static String spiderVsFly(String sp, String fl) {
        char[] letters = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
        int x1 = sp.codePointAt(0);
        int x2 = sp.codePointAt(1);
        int y1 = fl.codePointAt(0);
        int y2 = fl.codePointAt(1);
        if (x1 == y1 && x2 == y2) {
            return fl;
        } 
        if (x2 == 48) {
            x1 = y1;
        }
        int firstWay;
        int secondWay;
        if (x1 > y1) {
            firstWay = letters.length - (x1-65) + (y1-65);
            secondWay = x1-y1;
        } else {
            firstWay = y1 - x1;
            secondWay = letters.length - (y1-65) + (x1-65);
        }
        int min = Math.min(firstWay, secondWay);
        int numberDiff = (x2 - y2 >= 0 ? (x2 - y2 > 0 ? -1 : 0) : 1);
        int wordDiff = (min == secondWay) ? -1 : 1;
        String toCenter = Character.toString((char) (x1)) + Character.toString((char) (x2-1));
        String newNumber = Character.toString((char) x1) + Character.toString((char) (x2 + numberDiff));
        String newWord = Character.toString(letters[(x1-65+wordDiff > 0 ? x1-65+wordDiff : x1-65+wordDiff + 8) % 8]) 
        + Character.toString((char) x2);
        if (min >= 3 && min <= 4) {
            return sp + "-" + spiderVsFly(toCenter, fl);
        } else {
            if (x2 > y2) {
                return sp + "-" + spiderVsFly(newNumber, fl);
            } else {
                if (x1 != y1) {
                    return sp + "-" + spiderVsFly(newWord, fl);
                } else {
                    return sp + "-" + spiderVsFly(newNumber, fl);
                }
            }
        }
    } // 2

    public static int digitsCount(long number) {
        if (number == 0)
            return 0;
        return 1 + digitsCount(number / 10);
    } // 3

    public static int totalPoints(String[] arr, String word) {
        int res = 0;
        boolean b = inArray(arr, word);
        for (int i = 0; i < arr.length; i++) {
            int score = arr[i].length() == 6 ? (b ? 54 : 6) : arr[i].length() - 2;
            for (int j=0; j<arr[i].length(); j++) {
                if (word.indexOf(String.valueOf(arr[i].charAt(j))) == -1) {
                    score = 0;
                    break;
                }
            }
            res += score;
        }
        return res;
    } // 4

    public static boolean inArray(String[] arr, String word) {
        for (int i=0; i<arr.length; i++) {
            if (arr[i].compareTo(word) == 0) return true;
        }
        return false;
    }

    public static ArrayList<ArrayList<Integer>> sumsUp(int[] arr) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        int indexRes = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < arr.length; i++) {
            boolean b = true;
            for (Integer key : map.keySet()) {
                if (key + arr[i] == 8) {
                    ArrayList<Integer> cl = new ArrayList<Integer>();
                    cl.add(0, Math.min(key, arr[i]));
                    cl.add(1, Math.max(key, arr[i]));
                    res.add(indexRes, cl);
                    indexRes++;
                    map.remove(key, 1);
                    b = false;
                    break;
                }
            }
            if (b)
                map.put(arr[i], 1);
        }
        return res;
    } // 5

    public static String takeDownAverage(String[] arr) {
        if (arr.length == 0)
            return "0%";
        double sum = 0;
        for (String str : arr) {
            int number = Integer.parseInt(str.split("%")[0]);
            sum += number;
        }
        double avg = sum / arr.length;
        double res = (arr.length + 1) * (avg - 5) - sum;
        return Integer.toString((int) Math.round(res)) + "%";
    } // 6

    public static String caesarCipher(String mode, String str, int shift) {
        if (mode == "decode")
            shift *= -1;
        str = str.toUpperCase();
        String res = "";
        for (int i = 0; i < str.length(); i++) {
            int el = str.codePointAt(i);
            int number = el + shift;
            if (el >= 65 && el <= 90) {
                if (number < 65) {
                    number = 91 - (65 - number);
                } else if (number > 90) {
                    number = 64 + (number - 90);
                }
                res += Character.toString((char) number);
            } else {
                res += Character.toString((char) el);
            }
        }

        return res;
    } // 7

    public static int factorial(int n) {
        if (n == 0) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static int setSetup(int n, int k) {
        if (n < k) {
            return 0;
        }
        return factorial(n) / factorial(n - k);
    } // 8

    public static String timeDifference(String cityA, String stringDate, String cityB) {
        HashMap<String, TimeZone> timeZones = new HashMap<>();
        timeZones.put("Los Angeles", SimpleTimeZone.getTimeZone("GMT-8"));
        timeZones.put("New York", SimpleTimeZone.getTimeZone("GMT-5"));
        timeZones.put("Caracas", SimpleTimeZone.getTimeZone("GMT-4:30"));
        timeZones.put("Buenos Aires", SimpleTimeZone.getTimeZone("GMT-3"));
        timeZones.put("London", SimpleTimeZone.getTimeZone("GMT"));
        timeZones.put("Rome", SimpleTimeZone.getTimeZone("GMT+1"));
        timeZones.put("Moscow", SimpleTimeZone.getTimeZone("GMT+3"));
        timeZones.put("Tehran", SimpleTimeZone.getTimeZone("GMT+3:30"));
        timeZones.put("New Delhi", SimpleTimeZone.getTimeZone("GMT+5:30"));
        timeZones.put("Beijing", SimpleTimeZone.getTimeZone("GMT+8"));
        timeZones.put("Canberra", SimpleTimeZone.getTimeZone("GMT+10"));
        SimpleDateFormat firstFormat = new SimpleDateFormat("MMMM d, yyyy H:m", Locale.US);
        firstFormat.setTimeZone(timeZones.get(cityA));
        SimpleDateFormat secondFormat = new SimpleDateFormat("yyyy-M-d HH:mm", Locale.US);
        secondFormat.setTimeZone(timeZones.get(cityB));
        try {
            return secondFormat.format(firstFormat.parse(stringDate));
        } catch (ParseException ignored) {
            return "error";
        }
    } // 9

    public static boolean isNew(int num)
    {
        String str = String.valueOf(num);
        for(int i = 1; i < str.length(); i++) {
            if(!(str.charAt(i) == '0' && i==1) && str.charAt(i - 1) > str.charAt(i)) {
                return false;
            }
        }
        return true;
    } // 10
}