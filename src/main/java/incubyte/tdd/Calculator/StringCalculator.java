package incubyte.tdd.Calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";

        if (numbers.startsWith("//")) {

            delimiter = String.valueOf(numbers.charAt(2));

            numbers = numbers.substring(4);
        } else {
            numbers = numbers.replace("\n", ",");
        }

        String[] nums = numbers.split(Pattern.quote(delimiter));

        int sum = 0;

        for (String num : nums) {
            sum += Integer.parseInt(num);
        }

        return sum;
    }
}
