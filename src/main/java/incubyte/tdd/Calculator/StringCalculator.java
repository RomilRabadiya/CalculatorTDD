package incubyte.tdd.Calculator;

import java.util.regex.Pattern;

public class StringCalculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiter = ",";

        if (numbers.startsWith("//")) {

            int newLineIndex = numbers.indexOf('\n');

            String delimiterSection = numbers.substring(2, newLineIndex);

            if (delimiterSection.startsWith("[") && delimiterSection.endsWith("]")) {
                delimiter = delimiterSection.substring(1, delimiterSection.length() - 1);
            } else {
                delimiter = delimiterSection;
            }

            numbers = numbers.substring(newLineIndex + 1);

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
