import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VetorTest {

    @Test
    void constructorShouldThrowExceptionForZeroLengthVector() {
        assertThrows(IllegalArgumentException.class, () -> new Vetor(0, 0));
    }

    @Test
    void distanciaShouldReturnCorrectMagnitude() {
        Vetor vetor = new Vetor(3, 4);
        assertEquals(5, vetor.distancia());
    }

    @Test
    void prodinternoShouldReturnCorrectDotProduct() {
        Vetor v1 = new Vetor(1, 2);
        Vetor v2 = new Vetor(3, 4);
        assertEquals(11, v1.prodinterno(v2));
    }

    @Test
    void cossineShouldReturnCorrectCosineSimilarity() {
        Vetor v1 = new Vetor(1, 0);
        Vetor v2 = new Vetor(0, 1);
        assertEquals(0, v1.cossine(v2));
    }

    @Test
    void multShouldScaleVectorCorrectly() {
        Vetor vetor = new Vetor(1, 2);
        Vetor scaled = vetor.mult(3);
        assertEquals(3, scaled.getX());
        assertEquals(6, scaled.getY());
    }

    @Test
    void addShouldReturnCorrectSum() {
        Vetor v1 = new Vetor(1, 2);
        Vetor v2 = new Vetor(3, 4);
        Vetor sum = v1.add(v2);
        assertEquals(4, sum.getX());
        assertEquals(6, sum.getY());
    }

    @Test
    void subShouldReturnCorrectDifference() {
        Vetor v1 = new Vetor(5, 7);
        Vetor v2 = new Vetor(3, 4);
        Vetor difference = v1.sub(v2);
        assertEquals(2, difference.getX());
        assertEquals(3, difference.getY());
    }

    @Test
    void toStringShouldFormatCorrectly() {
        Vetor vetor = new Vetor(1.23456, -7.89012);
        assertEquals("[1.23,-7.89]", vetor.toString());
    }
}