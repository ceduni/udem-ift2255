package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import main.Calculator;
import test.utils.NumberGenerator;

class CalculatorTest {
    Calculator calculator = new Calculator();

    // Tests for the add function
    @Test
    public void testAddCommutativity() {
        // Arrange
        List<Integer> numbers1 = NumberGenerator.generateNumbers(-10000, 10000);
        List<Integer> numbers2 = NumberGenerator.generateNumbers(-10000, 10000);

        for (Integer num1 : numbers1) {
            for (Integer num2 : numbers2) {
                // Act
                int result1 = calculator.add(num1, num2);
                int result2 = calculator.add(num2, num1);

                // Assert
                assertEquals(result1, result2);
            }
        }
    }

    @Test
    public void testAddPositiveNumbers() {
        // Arrange
        List<Integer> posNumbers1 = NumberGenerator.generateNumbersGt(1);
        List<Integer> posNumbers2 = NumberGenerator.generateNumbersGt(1);

        for (Integer num1 : posNumbers1) {
            for (Integer num2 : posNumbers2) {
                // Act
                int result = calculator.add(num1, num2);

                // Assert
                assertEquals(num1 + num2, result);
                assertTrue(result > 0);
                assertTrue(result > num1);
                assertTrue(result > num2);
                assertTrue(result <= 2 * Math.max(num1, num2));
            }
        }
    }

    @Test
    public void testAddNegativeNumbers() {
        // Arrange
        List<Integer> negNumbers1 = NumberGenerator.generateNumbersLt(-1);
        List<Integer> negNumbers2 = NumberGenerator.generateNumbersLt(-1);

        for (Integer num1 : negNumbers1) {
            for (Integer num2 : negNumbers2) {
                // Act
                int result = calculator.add(num1, num2);

                // Assert
                assertEquals(num1 + num2, result);
                assertTrue(result < 0);
                assertTrue(result < num1);
                assertTrue(result < num2);
                assertTrue(result > 2 * Math.min(num1, num2));
            }
        }
    }

    @Test
    public void testAddPositiveAndNegativeNumbers() {
        // Arrange
        List<Integer> posNumbers = NumberGenerator.generateNumbersGt(1);
        List<Integer> negNumbers = NumberGenerator.generateNumbersLt(-1);

        for (Integer num1 : posNumbers) {
            for (Integer num2 : negNumbers) {
                // Act
                int result = calculator.add(num1, num2);

                // Assert
                assertEquals(num1 + num2, result);
                if (num1 > Math.abs(num2)) {
                    assertTrue(result > 0);
                } else if (num1 < Math.abs(num2)) {
                    assertTrue(result < 0);
                } else {
                    assertTrue(result == 0);
                }
            }
        }
    }

    @Test
    public void testAddZero() {
        // Arrange
        List<Integer> numbers = NumberGenerator.generateNumbers(-10000, 10000);

        for (Integer num : numbers) {
            // Act
            int result = calculator.add(num, 0);

            // Assert
            assertEquals(num, result);
        }
    }

    // Other test cases for subtract, multiply, divide functions can follow a
    // similar pattern

    @Test
    public void testAddOverflow() {
        // Arrange
        int num1 = Integer.MAX_VALUE;
        int num2 = 1;

        // Act
        int result = calculator.add(num1, num2);

        // Assert
        // This is a simple underflow check. Java handles underflow by wrapping around.
        // Hence, no exception is thrown, but the result would be Integer.MIN_VALUE.
        assertTrue(result < 0);
    }

    @Test
    public void testSubtractUnderflow() {
        // Arrange
        int min = Integer.MIN_VALUE;
        int num = 1;

        // Act
        int result = calculator.subtract(min, num);

        // Assert
        // This is a simple underflow check. Java handles underflow by wrapping around.
        // Hence, no exception is thrown, but the result would be Integer.MAX_VALUE.
        assertTrue(result > 0);
    }

    // Tests for the divide function
    @Test
    public void testDivideByZero() {
        // Arrange
        List<Integer> numbers = NumberGenerator.generateNumbers(-10000, 10000);

        for (Integer num : numbers) {
            // Act & Assert
            assertThrows(ArithmeticException.class, () -> calculator.divide(num, 0));
        }
    }
}
