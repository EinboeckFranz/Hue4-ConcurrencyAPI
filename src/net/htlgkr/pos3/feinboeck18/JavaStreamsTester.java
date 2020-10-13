package net.htlgkr.pos3.feinboeck18;

import java.util.List;
import java.util.stream.Collectors;

public class JavaStreamsTester {
    private static int getCountEmptyString(List<String> strings) {
        return strings.stream()
                .filter(s -> s.equals(" "))
                .toArray()
                .length;
    }

    private static int getCountLength3(List<String> strings) {
        return strings.stream()
                .filter(s -> s.length() == 3)
                .toArray()
                .length;
    }

    private static List<String> deleteEmptyStrings(List<String> strings) {
        return strings.stream()
                .filter(s -> !s.equals(" "))
                .collect(Collectors.toList());
    }

    private static String getMergedString(List<String> strings, String separator) {
        return String.join(separator, strings);
    }

    private static List<Integer> getSquares(List<Integer> numbers) {
        return numbers.stream()
                .map(integer -> (int) Math.pow(integer, 2))
                .collect(Collectors.toList());
    }

    private static int getMax(List<Integer> numbers) {
        return numbers.stream()
                .max(Integer::compare)
                .get();
    }

    private static int getMin(List<Integer> numbers) {
        return numbers.stream()
                .min(Integer::compare)
                .get();
    }

    private static int getSum(List<Integer> numbers) {
        return numbers.stream()
                .reduce(0, Integer::sum);
    }

    private static int getAverage(List<Integer> numbers) {
        return (int) Math.round(numbers.stream()
                .mapToInt(i -> i)
                .average()
                .getAsDouble());
    }
}