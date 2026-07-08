package incubyte.tdd.Calculator;

import incubyte.tdd.Calculator.StringCalculator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    @Test
    @DisplayName("ReturnZeroWhenInputIsEmpty")
    void shouldReturnZeroWhenInputIsEmpty() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    @DisplayName("ReturnSameNumberWhenSingleNumberIsProvided")
    void shouldReturnSameNumberWhenSingleNumberIsProvided() {
        assertEquals(5, calculator.add("5"));
    }
    @Test
    @DisplayName("ReturnZeroWhenInputIsZero")
    void shouldReturnZeroWhenInputIsZero() {
        assertEquals(0, calculator.add("0"));
    }

    @Test
    @DisplayName("ReturnSumWhenTwoNumbersAreProvided")
    void shouldReturnSumWhenTwoNumbersAreProvided() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    @DisplayName("ReturnSumWhenMultipleNumbersAreProvided")
    void shouldReturnSumWhenMultipleNumbersAreProvided() {
        assertEquals(15, calculator.add("1,2,3,4,5"));
    }

    @Test
    @DisplayName("SupportNewLineAsDelimiter")
    void shouldSupportNewLineAsDelimiter() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    @DisplayName("SupportCustomDelimiter")
    void shouldSupportCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    @DisplayName("SupportMultipleNumbersWithCustomDelimiter")
    void shouldSupportMultipleNumbersWithCustomDelimiter() {
        assertEquals(10, calculator.add("//;\n1;2;3;4"));
    }

}