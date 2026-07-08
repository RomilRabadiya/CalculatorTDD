package incubyte.tdd.Calculator;

import java.util.regex.Pattern;

import java.util.Arrays;
public class StringCalculator {

    public int add(String numbers) {

        if (numbers.isEmpty()) {
            return 0;
        }

        String delimiterRegex = ",";

        if (numbers.startsWith("//")) {

            int newLineIndex = numbers.indexOf('\n');

            String delimiterSection = numbers.substring(2, newLineIndex);

            if (delimiterSection.startsWith("[") && delimiterSection.endsWith("]")) {

                String[] delimiters = delimiterSection
                        .substring(1, delimiterSection.length() - 1)
                        .split("\\]\\[");

                delimiterRegex = String.join("|",
                        Arrays.stream(delimiters)
                                .map(Pattern::quote)
                                .toArray(String[]::new));

            } else {

                delimiterRegex = Pattern.quote(delimiterSection);
            }

            numbers = numbers.substring(newLineIndex + 1);

        } else {

            numbers = numbers.replace("\n", ",");
        }

        String[] nums = numbers.split(delimiterRegex, -1);

        int sum = 0;

        for (String num : nums) {

            if (num.isEmpty()) {
                throw new IllegalArgumentException("Empty number is not allowed");
            }

            int value = Integer.parseInt(num);
            sum+=value;
        }

        return sum;
    }
}
