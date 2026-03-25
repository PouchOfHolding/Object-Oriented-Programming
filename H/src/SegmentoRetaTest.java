import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SegmentoRetaTest {

    @Test
    void constructorShouldThrowExceptionForZeroLengthVector() {
        Vetor zeroVector = new Vetor(0, 0);
        Ponto ponto = new Ponto(1, 1);
        assertThrows(IllegalArgumentException.class, () -> new SegmentoReta(ponto, zeroVector));
    }

    @Test
    void constructorShouldCreateSegmentFromTwoDistinctPoints() {
        Ponto p1 = new Ponto(0, 0);
        Ponto p2 = new Ponto(3, 4);
        SegmentoReta segmento = new SegmentoReta(p1, p2);
        assertEquals(5, segmento.comprimento());
    }

    @Test
    void comprimentoShouldReturnCorrectLength() {
        Ponto ponto = new Ponto(0, 0);
        Vetor vetor = new Vetor(3, 4);
        SegmentoReta segmento = new SegmentoReta(ponto, vetor);
        assertEquals(5, segmento.comprimento());
    }

    @Test
    void intersectShouldReturnNullForParallelSegments() {
        Ponto ponto = new Ponto(0, 0);
        Vetor vetor1 = new Vetor(1, 1);
        Vetor vetor2 = new Vetor(2, 2);
        SegmentoReta segmento = new SegmentoReta(ponto, vetor1);
        assertNull(segmento.intersect(vetor2));
    }

    @Test
    void intersectShouldReturnIntersectionPointForIntersectingSegments() {
        Ponto ponto = new Ponto(0, 0);
        Vetor vetor1 = new Vetor(1, 0);
        Vetor vetor2 = new Vetor(0, 1);
        SegmentoReta segmento = new SegmentoReta(ponto, vetor1);
        Ponto intersection = segmento.intersect(vetor2);
        assertNotNull(intersection);
        assertEquals(0, intersection.getX());
        assertEquals(0, intersection.getY());
    }

    @Test
    void toStringShouldFormatCorrectly() {
        Ponto ponto = new Ponto(0, 0);
        Vetor vetor = new Vetor(3, 4);
        SegmentoReta segmento = new SegmentoReta(ponto, vetor);
        assertEquals("sr((0.00,0.00); (3.00,4.00))", segmento.toString());
    }
}