package net.htlgkr.pos3.feinboeck18;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Collectors;

public class FileWithNumbersReader {
    private List<Integer> numbers = new ArrayList<>();

    public static void main(String[] args) {
        FileWithNumbersReader fWnR = new FileWithNumbersReader();
        fWnR.run();
    }

    //Requests User Input
    public void run() {
        Scanner sc = new Scanner(System.in);

        readInNumbers("numbers.csv");
        System.out.print("Enter chunks>");
        int chunks = Integer.parseInt(sc.nextLine());
        System.out.print("Enter divider>");
        int divider = Integer.parseInt(sc.nextLine());

        handleMainInput(chunks, divider);
    }

    //"Application Logic"
    private void handleMainInput(int chunks, int divider) {
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(chunks);
        List<Callable<Boolean>> callables = new ArrayList<>();
        int startValue = 0;
        int splitPosition;

        for (int i = 1; i <= chunks; i++) {
            splitPosition = (numbers.size()/chunks) * i;
            callables.add(new GetDivisibility(startValue, splitPosition, divider));
            startValue = splitPosition + 1;
        }
        try {
            threadPoolExecutor.invokeAll(callables);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        threadPoolExecutor.shutdown();
        System.exit(0);
    }

    //Reads in File
    private void readInNumbers(String path) {
        try {
            numbers = Files.lines(new File(path).toPath())
                    .flatMap(line -> Arrays.stream(line.split(":")))
                    .map(string -> {
                        try {
                            return Integer.parseInt(string);
                        } catch (NumberFormatException nfe) {
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Inner-Class class
    public static class GetDivisibility implements Callable<Boolean> {
        private final int startValue;
        private final int endValue;
        private final int divider;

        public GetDivisibility(int startValue, int endValue, int divider) {
            this.startValue = startValue;
            this.endValue = endValue;
            this.divider = divider;
        }

        @Override
        public Boolean call() {
            //Check if Strings are correct separated System.err.println(String.format("%d %d %d", startValue, endValue, divider));
            for (int i = startValue; i <= endValue; i++) {
                if(i % divider == 0)
                    System.out.println(i);
            }
            return true;
        }
    }
}