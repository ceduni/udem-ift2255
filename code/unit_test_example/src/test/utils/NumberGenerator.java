package test.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {
    private static final int COUNT = 10;
    private static final int RANGE = 1000;
    private static final Random RANDOM = new Random();

    public static List<Integer> generateNumbers(int min, int max) {
        return generateNumbers(min, max, COUNT);
    }

    public static List<Integer> generateNumbers(int min, int max, int count) {
        List<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            numbers.add(RANDOM.nextInt(max - min + 1) + min);
        }
        return numbers;
    }

    public static List<Integer> generateNumbersLt(int max) {
        return generateNumbersLt(max, RANGE, COUNT);
    }

    public static List<Integer> generateNumbersLt(int max, int count) {
        return generateNumbersLt(max, RANGE, count);
    }

    public static List<Integer> generateNumbersLt(int max, int range, int count) {
        return generateNumbers(max - RANGE, max, count);
    }

    public static List<Integer> generateNumbersGt(int min) {
        return generateNumbersGt(min, RANGE, COUNT);
    }

    public static List<Integer> generateNumbersGt(int min, int count) {
        return generateNumbers(min, min + RANGE, count);
    }

    public static List<Integer> generateNumbersGt(int min, int range, int count) {
        return generateNumbers(min, min + range, count);
    }

    public static int generateNumber(int min, int max) {
        return RANDOM.nextInt(max - min + 1) + min;
    }

    public static int generateNumber(int min) {
        return generateNumber(min, RANGE);
    }
}
