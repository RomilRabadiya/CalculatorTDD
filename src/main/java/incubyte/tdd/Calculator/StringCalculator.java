package incubyte.tdd.Calculator;

public class StringCalculator {

    public int add(String numbers) {
        // Minimal Code for Test4
        if (numbers.isEmpty()) {
            return 0;
        }

        if (!numbers.contains(",")) {
            return Integer.parseInt(numbers);
        }

        String[] nums = numbers.split(",");

        return Integer.parseInt(nums[0]) +
                Integer.parseInt(nums[1]);
    }
}
