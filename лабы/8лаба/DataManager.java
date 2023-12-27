package main;

import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class DataManager {
    private List<Object> processors = new ArrayList<>();
    private List<String> inputData = new ArrayList<>();
    private List<String> processedData = new ArrayList<>();

    public void registerDataProcessor(Object processor) {
        processors.add(processor);
    }

    public void loadData(String source) {
        try {
            inputData = Files.readAllLines(Paths.get(source));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void processData() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        List<List<String>> allProcessedData = new ArrayList<>();

        for (Object processor : processors) {
            Class<?> processorClass = processor.getClass();
            for (Method method : processorClass.getDeclaredMethods()) {
                if (method.isAnnotationPresent(DataProcessor.class)) {
                    Object finalProcessor = processor;
                    Method finalMethod = method;

                    executorService.submit(() -> {
                        List<List<String>> tempResultList = inputData.stream()
                                .map(data -> invokeDataProcessorMethod(finalProcessor, finalMethod, data))
                                .map(innerList -> innerList.stream().collect(Collectors.toList()))
                                .collect(Collectors.toList());

                        allProcessedData.addAll(tempResultList);
                    });
                }
            }
        }

        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }

        SortProcessor sortProcessor = new SortProcessor();
        List<String> sortedData = sortProcessor.sortData(allProcessedData.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));

        System.out.println("Отсортированные данные: " + sortedData);

        FilterProcessor filterProcessor = new FilterProcessor();
        List<String> filteredData = filterProcessor.filterData(allProcessedData.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));

        System.out.println("Отфильтрованные данные: " + filteredData);

        TransformProcessor transformProcessor = new TransformProcessor();
        List<String> transformedData = transformProcessor.transformData(allProcessedData.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()));

        System.out.println("Трансформированные данные: " + transformedData);

        processedData.addAll(sortedData);
        processedData.add("\n");
        processedData.addAll(filteredData);
        processedData.add("\n");
        processedData.addAll(transformedData);
    }

    private List<String> invokeDataProcessorMethod(Object processor, Method method, String data) {
        try {
            Object result = method.invoke(processor, Collections.singletonList(data));

            if (result != null) {
                if (result instanceof List<?>) {
                    return (List<String>) result;
                } else if (result instanceof String) {
                    return Collections.singletonList((String) result);
                } else {
                    return Collections.emptyList();
                }
            }

            return Collections.emptyList();
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public void saveData(String destination) {
        try {
            Path outputPath = Paths.get(destination);

            if (!Files.exists(outputPath.getParent())) {
                Files.createDirectories(outputPath.getParent());
            }

            String resultString = String.join(" ", processedData);
            Files.write(outputPath, Collections.singletonList(resultString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String inputPath = "D:\\лабы\\итип\\лабы\\8лаба\\input.txt";
        String outputPath = "D:\\лабы\\итип\\лабы\\8лаба\\output.txt";

        DataManager dataManager = new DataManager();
        dataManager.registerDataProcessor(new SortProcessor());
        dataManager.loadData(inputPath);

        System.out.println("Данные до обработки: ");
        dataManager.inputData.forEach(System.out::println);
        System.out.println();

        dataManager.processData();
        dataManager.saveData(outputPath);
    }
}

class SortProcessor {
    @DataProcessor
    public List<String> sortData(List<String> data) {
        String[] dataArray = data.stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .toArray(String[]::new);

        Arrays.sort(dataArray);
        return Arrays.asList(dataArray);
    }
}

class FilterProcessor {
    @DataProcessor
    public List<String> filterData(List<String> data) {
        return data.stream()
                .map(s -> s.replaceAll("\\d", ""))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
}

class TransformProcessor {
    @DataProcessor
    public List<String> transformData(List<String> data) {
        List<String> transformedData = new ArrayList<>();
        int offset = 0;

        for (String s : data) {
            StringBuilder transformed = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                char currentChar = s.charAt(i);
                if (Character.isDigit(currentChar)) {
                    for (char digit : String.valueOf(currentChar).toCharArray()) {
                        transformed.append(i + 1 + offset).append(")").append(digit).append(" ");
                    }
                } else {
                    transformed.append(i + 1 + offset).append(")").append(currentChar).append(" ");
                }
            }
            transformedData.add(transformed.toString().trim());
            offset += s.length();
        }

        return transformedData;
    }
}
