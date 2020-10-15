package net.htlgkr.pos3.feinboeck18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("SimplifyStreamApiCallChains")
public class JavaStreamsTester {
    private static List<Integer> integerList = new ArrayList<>();
    private static List<String> stringList = new ArrayList<>();

    public static void main(String[] args) {
        setUpLists();
        System.out.println("Expected 2 - Actual:" + JavaStreamsTester.getCountEmptyString(stringList));
        System.out.println("Expected 0 - Actual:" + JavaStreamsTester.getCountLength3(stringList));
        System.out.println(JavaStreamsTester.deleteEmptyStrings(stringList));
        System.out.println(JavaStreamsTester
                .getMergedString(Arrays.asList(new String[]{"This", "should", "be", "merged", "with", "commas"}.clone()), ","));
        System.out.println("Expected 4, 9, 16 - Actual:" + JavaStreamsTester.getSquares(integerList));
        System.out.println("Expected 4 - Actual:" + JavaStreamsTester.getMax(integerList));
        System.out.println("Expected 2 - Actual:" + JavaStreamsTester.getMin(integerList));
        System.out.println("Expected 9 - Actual:" + JavaStreamsTester.getSum(integerList));
        System.out.println("Expected 3 - Actual:" + JavaStreamsTester.getAverage(integerList));
    }

    private static void setUpLists() {
        //Add Values to String-List
        stringList.add("TestString");
        stringList.add("");
        stringList.add("AnotherTestString");
        stringList.add("");
        //Add Values to Integer-List
        integerList.add(2);
        integerList.add(3);
        integerList.add(4);
    }

    private static int getCountEmptyString(List<String> strings) {
        return (int) strings.stream()
                .filter(String::isEmpty)
                .count();
    }

    private static int getCountLength3(List<String> strings) {
        return (int) strings.stream()
                .filter(s -> s.length() == 3)
                .count();
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream()
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings, String separator) {
        return strings.stream()
                .collect(Collectors.joining(separator));
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream()
                .map(integer -> (int) Math.pow(integer, 2))
                .collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream()
                .max(Integer::compare)
                .orElse(0);
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream()
                .min(Integer::compare)
                .orElse(0);
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) numbers.stream()
                .mapToInt(i -> i)
                .average().orElse(0.0);
    }
}