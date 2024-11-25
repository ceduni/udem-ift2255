import org.junit.jupiter.api.Test;

import com.ift2255a24.Calculator;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void testAdd_Pos_Number() {
        int a = 2;
        int b = 3;
        assertEquals(5, calculator.add(a, b));
    }

    @Test
    void testAdd_Neg_Number() {
        int a = -2;
        int b = -3;
        assertEquals(-5, calculator.add(a, b));
    }

    @Test
    void testAdd_PosAndNeg() {
        int a = 3;
        int b = -2;
        assertEquals(1, calculator.add(a, b));
    }

    @Test
    void testAdd_A_Eq_Zero() {
        int a = 0;
        int b = 4;
        assertEquals(b, calculator.add(a, b));
    }

    @Test
    void testAdd_B_Eq_Zero() {
        int a = -4;
        int b = 0;
        assertEquals(a, calculator.add(a, b));
    }

    @Test
    void testAdd_Zero() {
        int a = 0;
        int b = 0;
        assertEquals(0, calculator.add(a, b));
    }

    @Test
    void testSubtract() {
        assertEquals(1, calculator.subtract(3, 2));
    }

    @Test
    void testMultiply() {
        assertEquals(6, calculator.multiply(2, 3));
    }

    @Test
    void testDivide() {
        assertEquals(2, calculator.divide(6, 3));
    }

    @Test
    void testDivideByZero() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            calculator.divide(6, 0);
        });
        assertEquals("La division par zéro n'est pas permise.", exception.getMessage());
    }
}
