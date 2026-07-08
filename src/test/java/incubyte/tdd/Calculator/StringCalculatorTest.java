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
}