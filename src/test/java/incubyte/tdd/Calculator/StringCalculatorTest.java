package incubyte.tdd.Calculator;

import incubyte.tdd.Calculator.StringCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringCalculatorTest {

    private StringCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new StringCalculator();
    }

    @Test
    @DisplayName("TC-01: Should return 0 for empty input")
    void shouldReturnZeroForEmptyInput() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    @DisplayName("TC-02: Should return the single number when one number is provided")
    void shouldReturnSingleNumber() {
        assertEquals(5, calculator.add("5"));
    }

    @Test
    @DisplayName("TC-03: Should return 0 when input is zero")
    void shouldReturnZeroWhenInputIsZero() {
        assertEquals(0, calculator.add("0"));
    }

    @Test
    @DisplayName("TC-04: Should return the sum of two comma-separated numbers")
    void shouldReturnSumOfTwoNumbers() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    @DisplayName("TC-05: Should return the sum of multiple comma-separated numbers")
    void shouldReturnSumOfMultipleNumbers() {
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    @DisplayName("TC-06: Should support newline as a delimiter")
    void shouldSupportNewLineAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    @DisplayName("TC-07: Should support a custom single-character delimiter")
    void shouldSupportCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    @DisplayName("TC-08: Should support multiple numbers with a custom delimiter")
    void shouldSupportMultipleNumbersWithCustomDelimiter() {
        assertEquals(10, calculator.add("//;\n1;2;3;4"));
    }

    @Test
    @DisplayName("TC-09: Should support a delimiter of any length")
    void shouldSupportDelimiterOfAnyLength() {
        assertEquals(6, calculator.add("//[***]\n1***2***3"));
    }

    @Test
    @DisplayName("TC-10: Should support multiple delimiters")
    void shouldSupportMultipleDelimiters() {
        assertEquals(6, calculator.add("//[*][%]\n1*2%3"));
    }

    @Test
    @DisplayName("TC-11: Should support multiple delimiters of any length")
    void shouldSupportMultipleLongDelimiters() {
        assertEquals(6, calculator.add("//[***][%%%]\n1***2%%%3"));
    }

    @Test
    @DisplayName("TC-12: Should return 0 when all numbers are zero")
    void shouldReturnZeroWhenAllNumbersAreZero() {
        assertEquals(0, calculator.add("0,0,0"));
    }

    @Test
    @DisplayName("TC-13: Should support numbers with leading zeros")
    void shouldSupportNumbersWithLeadingZeros() {
        assertEquals(3, calculator.add("01,02"));
    }

    @Test
    @DisplayName("TC-14: Should correctly sum duplicate numbers")
    void shouldReturnSumWhenDuplicateNumbersAreProvided() {
        assertEquals(6, calculator.add("2,2,2"));
    }

    @Test
    @DisplayName("TC-15: Should handle a large number of inputs")
    void shouldHandleLargeNumberOfInputs() {
        String input = IntStream.rangeClosed(1, 100)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(","));

        assertEquals(5050, calculator.add(input));
    }

    @Test
    @DisplayName("TC-16: Should throw NumberFormatException for non-numeric input")
    void shouldThrowExceptionWhenInputContainsNonNumericValue() {
        assertThrows(
                NumberFormatException.class,
                () -> calculator.add("1,a")
        );
    }

    @Test
    @DisplayName("TC-17: Should throw IllegalArgumentException for consecutive delimiters")
    void shouldThrowExceptionWhenInputContainsEmptyToken() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,,2")
        );
    }

    @Test
    @DisplayName("TC-18: Should throw IllegalArgumentException when input starts with a delimiter")
    void shouldThrowExceptionWhenInputStartsWithDelimiter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(",1")
        );
    }

    @Test
    @DisplayName("TC-19: Should throw IllegalArgumentException when input ends with a delimiter")
    void shouldThrowExceptionWhenInputEndsWithDelimiter() {
        assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,")
        );
    }

    @Test
    @DisplayName("TC-20: Should remain stateless across multiple method calls")
    void shouldRemainStatelessAcrossMultipleCalls() {
        assertEquals(3, calculator.add("1,2"));
        assertEquals(5, calculator.add("5"));
        assertEquals(0, calculator.add(""));
        assertEquals(5, calculator.add("2,3"));
    }
}
