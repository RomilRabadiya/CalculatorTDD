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

            if(numbers.isEmpty())
            {
                throw new IllegalArgumentException("No numbers found after custom delimiter declaration.");
            }

            if(numbers.contains("\n"))
            {
                throw  new IllegalArgumentException("Avoid newline when a custom delimiter is specified.");
            }

        }
        else if (numbers.startsWith("[") && !numbers.startsWith("//[")) {
            throw new IllegalArgumentException(
                    "Custom delimiter declaration must start with '//'."
            );
        }
        else {
            if (numbers.contains("\n\n")) {
                throw new IllegalArgumentException(
                        "Input cannot contain consecutive newline delimiters."
                );
            }

            numbers = numbers.replace("\n", ",");
        }
        if(numbers.startsWith(delimiterRegex) && numbers.endsWith(delimiterRegex))
        {
            throw new IllegalArgumentException("Input cannot contain empty numbers.");
        }
        else if (numbers.startsWith(delimiterRegex)) {
            throw new IllegalArgumentException("Input cannot start with a delimiter.");
        }
        else if(numbers.endsWith(delimiterRegex))
        {
            throw  new IllegalArgumentException("Input cannot end with a delimiter.");
        }

        String[] nums = numbers.split(delimiterRegex, -1);

        int sum = 0;

        for (String num : nums) {

            if (num.isEmpty()) {
                throw new IllegalArgumentException("Input cannot contain empty numbers.");
            }
            try
            {
                int value = Integer.parseInt(num);
                sum+=value;
            }
            catch (NumberFormatException e) {
                throw new NumberFormatException("Invalid number: '" + num + "'");
            }
        }

        return sum;
    }
}
