package demo;

import java.util.*;

public class FunctionalRefactor {
    public static void main(String[] args) {
        // Imperative style
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        int sum = 0;
        for (int n : nums) {
            if (n % 2 == 0) sum += n;
        }
        System.out.println("Imperative Sum of even numbers: " + sum);

        // Functional style using streams
        int functionalSum = nums.stream()
                .filter(n -> n % 2 == 0)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println("Functional Sum of even numbers: " + functionalSum);
    }
}
