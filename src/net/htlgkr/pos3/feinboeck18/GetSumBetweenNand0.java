package net.htlgkr.pos3.feinboeck18;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class GetSumBetweenNand0 {

    public static void main(String[] args) {
        GetSumBetweenNand0 main = new GetSumBetweenNand0();
        main.run();
    }

    public void run() {
        int range = getUserInput();
        if(range == -1)
            return;
        int howMuchThreads = range/100 + 1;
        ExecutorService pool = Executors.newFixedThreadPool(howMuchThreads);
        int sum = 0;

        try {
            for (final Future<Integer> integer : pool.invokeAll(distributeNumbers(range, howMuchThreads)))
                sum+=integer.get();
            pool.shutdown();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("My Sum: " + sum);
        System.out.println("Gauss Sum: " + gaussSum(range));
    }

    private int gaussSum(int number) {
        return (int)((Math.pow(number , 2) + number)/2);
    }

    private int getUserInput() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("n>");
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException nfe) { System.out.println("Unexpected Input"); }
        return -1;
    }

    private List<Callable<Integer>> distributeNumbers(int range, int howMuchThreads) {
        List<Callable<Integer>> callables = new ArrayList<>();
        int startValue = 0;
        for (int i = 1; i <= howMuchThreads; i++) {
            int splitPosition = (int)((double)i/howMuchThreads)*range;
            callables.add(new CalcSumBetween(startValue, splitPosition));
            startValue = splitPosition + 1;
        }
        return callables;
    }

    public static class CalcSumBetween implements Callable<Integer> {
        private final int startValue;
        private final int endValue;

        public CalcSumBetween(int startValue, int endValue) {
            this.startValue = startValue;
            this.endValue = endValue;
        }

        @Override
        public Integer call() {
            int returnValue = 0;
            for (int i = startValue; i <= endValue ; i++)
                returnValue = returnValue + i;
            return returnValue;
        }
    }
}