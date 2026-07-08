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
    @DisplayName("TC-16: Should reject non-numeric input")
    void shouldThrowExceptionWhenInputContainsNonNumericValue() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,a")
        );

        assertEquals("Invalid number: 'a'", exception.getMessage());
    }

    @Test
    @DisplayName("TC-17: Should reject consecutive delimiters")
    void shouldThrowExceptionWhenInputContainsEmptyToken() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,,2")
        );

        assertEquals("Input cannot contain empty numbers.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-18: Should reject input starting with a delimiter")
    void shouldThrowExceptionWhenInputStartsWithDelimiter() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(",1")
        );

        assertEquals("Input cannot start with a delimiter.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-19: Should reject input ending with a delimiter")
    void shouldThrowExceptionWhenInputEndsWithDelimiter() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1,")
        );

        assertEquals("Input cannot end with a delimiter.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-20: Should remain stateless across multiple method calls")
    void shouldRemainStatelessAcrossMultipleCalls() {
        assertEquals(3, calculator.add("1,2"));
        assertEquals(5, calculator.add("5"));
        assertEquals(0, calculator.add(""));
        assertEquals(5, calculator.add("2,3"));
    }

    @Test
    @DisplayName("TC-21: Should reject decimal numbers")
    void shouldThrowExceptionForDecimalNumbers() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1.5,2")
        );

        assertEquals("Invalid number: '1.5'", exception.getMessage());
    }

    @Test
    @DisplayName("TC-22: Should reject alphabetic input")
    void shouldThrowExceptionForAlphabeticInput() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("abc")
        );

        assertEquals("Invalid number: 'abc'", exception.getMessage());
    }

    @Test
    @DisplayName("TC-23: Should reject input containing only a delimiter")
    void shouldThrowExceptionForOnlyDelimiter() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add(",")
        );

        assertEquals("Input cannot contain empty numbers.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-24: Should reject input containing only a newline")
    void shouldThrowExceptionForOnlyNewLine() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("\n")
        );

        assertEquals("Input cannot contain empty numbers.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-25: Should reject consecutive newline delimiters")
    void shouldThrowExceptionForConsecutiveNewLines() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("1\n\n2")
        );

        assertEquals("Input cannot contain consecutive newline delimiters.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-26: Should reject newline when a custom delimiter is specified")
    void shouldRejectNewLineWhenCustomDelimiterIsSpecified() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//;\n1\n2")
        );

        assertEquals(
                "Avoid newline when a custom delimiter is specified.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("TC-27: Should reject custom delimiter declaration without numbers")
    void shouldThrowExceptionWhenNoNumbersAfterCustomDelimiter() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//;\n")
        );

        assertEquals("No numbers found after custom delimiter declaration.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-28: Should reject consecutive custom delimiters")
    void shouldThrowExceptionForConsecutiveCustomDelimiters() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("//[*][%]\n1*%2")
        );

        assertEquals("Input cannot contain empty numbers.", exception.getMessage());
    }

    @Test
    @DisplayName("TC-29: Should reject custom delimiter declaration without '//' prefix")
    void shouldRejectCustomDelimiterWithoutPrefix() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> calculator.add("[***]\n1***2")
        );

        assertEquals(
                "Custom delimiter declaration must start with '//'.",
                exception.getMessage()
        );
    }

    @Test
    @DisplayName("TC-30: Should support long custom delimiters")
    void shouldSupportLongCustomDelimiter() {
        assertEquals(
                3,
                calculator.add("//[abcdef]\n1abcdef2")
        );
    }

    @Test
    @DisplayName("TC-31: Should support three custom delimiters")
    void shouldSupportThreeCustomDelimiters() {
        assertEquals(
                10,
                calculator.add("//[a][b][c]\n1a2b3c4")
        );
    }
}
