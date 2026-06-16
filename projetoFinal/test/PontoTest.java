import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class PontoTest {

    @Test
    void pontoRetornaCoordenadasCorretas() {
        Ponto ponto = new Ponto(3, -2);
        assertEquals(3, ponto.getX());
        assertEquals(-2, ponto.getY());
    }

    @Test
    void pontoToStringFormatoCorreto() {
        Ponto ponto = new Ponto(3.50, -2.10);
        assertEquals("(3,50,-2,10)", ponto.toString());
    }

    @Test
    void pontoComCoordenadasZero() {
        Ponto ponto = new Ponto(0.0, 0.0);
        assertEquals(0.0, ponto.getX());
        assertEquals(0.0, ponto.getY());
        assertEquals("(0,00,0,00)", ponto.toString());
    }

    @Test
    void pontoComCoordenadasGrandes() {
        Ponto ponto = new Ponto(1e9, -1e9);
        assertEquals(1e9, ponto.getX());
        assertEquals(-1e9, ponto.getY());
        assertEquals("(1000000000,00,-1000000000,00)", ponto.toString());
    }
}