import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        numberFinder("19.99abc55 19,99");
        System.out.println(correctPassword("12345@@DDDddd"));
        System.out.println(replaceLinks("www.youtube.com/ hi"));
        System.out.println(isValidIPAddress("127.3.111.1"));
        myStartsWith("Aaa aaa bb abd", 'A');
    }

    public static void numberFinder(String text) {
        try {
            Pattern pattern = Pattern.compile("-?\\d+([\\.\\,]\\d+)?");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                System.out.print(matcher.group() + " ");
            }
            System.out.println();
        } catch(PatternSyntaxException e) {
            System.out.println(e);
        }
        
    }

    public static boolean correctPassword(String pass) {
        try {
            Pattern pattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,16}$");
            Matcher matcher = pattern.matcher(pass);
            return matcher.matches();
        } catch (PatternSyntaxException e) {
            System.out.println(e);
            return false;
        }
        
    }

    public static String replaceLinks(String link) {
        try {
            Pattern pattern = Pattern.compile("\\b(https?:\\/\\/)?(www\\.[a-zA-Z]+\\.[a-zA-Z]+)\\b");
            Matcher matcher = pattern.matcher(link);

            StringBuffer output = new StringBuffer();

            while (matcher.find()) {
                if (matcher.group(1) != null) {
                    throw new IllegalArgumentException("Ошибка: это гиперссылка");
                }
                matcher.appendReplacement(output, "https://$2");
            }
            matcher.appendTail(output);
            return output.toString();
        }catch (Exception e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public static boolean isValidIPAddress(String ip) {
        try {
            String str = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." +
                "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
            Pattern p = Pattern.compile(str);
            Matcher m = p.matcher(ip);
            return m.matches();
        } catch (PatternSyntaxException e) {
            System.out.println(e);
            return false;
        }
        
    }

    public static void myStartsWith(String text, char letter) {
        try {
            Pattern pattern = Pattern.compile("\\b" + letter + "\\w*\\b");
            Matcher matcher = pattern.matcher(text);
            while (matcher.find()) {
                System.out.print(matcher.group() + " ");
            }
            System.out.println();
        } catch (PatternSyntaxException e) {
            System.out.println(e);
        }
        
    }
}
