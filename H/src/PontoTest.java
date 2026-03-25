import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PontoTest {

    @Test
    void constructorShouldSetCoordinatesCorrectly() {
        Ponto ponto = new Ponto(3.5, -2.1);
        assertEquals(3.5, ponto.getX());
        assertEquals(-2.1, ponto.getY());
    }


    @Test
    void constructorShouldHandleZeroCoordinates() {
        Ponto ponto = new Ponto(0, 0);
        assertEquals(0, ponto.getX());
        assertEquals(0, ponto.getY());
    }

    @Test
    void constructorShouldHandleLargeCoordinates() {
        Ponto ponto = new Ponto(1e9, -1e9);
        assertEquals(1e9, ponto.getX());
        assertEquals(-1e9, ponto.getY());
    }

    @Test
    void constructorShouldHandleNegativeCoordinates() {
        Ponto ponto = new Ponto(-5.5, -10.1);
        assertEquals(-5.5, ponto.getX());
        assertEquals(-10.1, ponto.getY());
    }

    @Test
    void toStringShouldHandleZeroCoordinates() {
        Ponto ponto = new Ponto(0, 0);
        assertEquals("(0.00,0.00)", ponto.toString());
    }

    @Test
    void toStringShouldHandleNegativeCoordinates() {
        Ponto ponto = new Ponto(-3.14159, -2.71828);
        assertEquals("(-3.14,-2.72)", ponto.toString());
    }

    @Test
    void toStringShouldHandleLargeCoordinates() {
        Ponto ponto = new Ponto(123456.789, -987654.321);
        assertEquals("(123456.79,-987654.32)", ponto.toString());
    }
}