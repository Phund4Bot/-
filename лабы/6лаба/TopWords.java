import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TopWords {
    public static void main(String[] args) {
        String filePath = "text.txt";
        File file = new File(filePath);
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
            return;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        try{
            FileInputStream in = new FileInputStream(file);
            int c;
            String s = "";
            while((in.available())!=1){  
                c = in.read();
                if((int)c!=32){
                    s = s + (char)c;
                } else {
                    if (!map.containsKey(s)) {
                        map.put(s, 1);
                    } else {
                        int count = map.get(s);
                        map.put(s, count += 1);
                    }
                    s = "";
                }
            }
            in.close();
        } catch (IOException e) {
            System.out.println("Player file wouldn't load!");
            scanner.close();
            return;
        }
        scanner.close();
        // 1. Создаю новый отсортированный по значению hashMap
        // 2. entrySet - возвращает set из элементов исходного map.
        // 3. stream - поток представляет канал передачи данных из источника данных, понятие потока в программировании размыто;
        // метод stream создает из set поток данных.
        // 4. sorted - метод, сортирующий по возрастанию. Принимает в себя компаратор;
        // Коллекция, которую мы хотим отсортировать, должна реализовывать интерфейс comparator.
        // 6. Comparable делает наши объекты «сравнимыми» и создает для них наиболее естественный порядок сортировки.
        // 7. collect - позволяет получить данные не в виде потока, а в виде коллекции.
        // 8. Collectors.toMap() - используется для сбора элементов Stream в Map.
        // Я использую перегрузку, которая принимает ключ, значение, функцию - разрешитель конфликтов (хотя она тут избыточна,
        // т.к. коллекция уже отсортирована и мы используем hashmap) и создается новый LinkedHashMap.
        // 9. Разница между HashMap и LinkedHashMap заключается в том, что HashMap не сохраняет порядок ввода ключей или элементов, 
        // тогда как LinkedHashMap следует порядку вставки ключей и сохраняет порядок элементов.
        Map<String, Integer> sortedMap = map.entrySet().stream()
                .sorted((e1, e2) -> {
                    return e2.getValue() - e1.getValue();
                })
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> { throw new AssertionError(); }, 
                        LinkedHashMap::new 
                    ));

        int i=0;
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (i>=10) break;
            i++;
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}