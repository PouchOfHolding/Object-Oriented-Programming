import Geometry.figuras.Quadrado;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class QuadradoTest {

    @Test
    public void testValidQuadrado() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(2, 0),
            new Ponto(2, 2),
            new Ponto(0, 2)
        };
        Quadrado q = new Quadrado(vertices);
        assertNotNull(q);
        assertArrayEquals(vertices, q.getVertices());
    }

    @Test
    public void testIntersectionWithRouteCrossing() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(4, 4),
            new Ponto(0, 4)
        };
        Quadrado q = new Quadrado(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, 2), new Ponto(5, 2)};
        Route r = new Route(rotaPts);
        
        List<Ponto> inter = q.intersect(r);
        assertEquals(2, inter.size());
        
        // A ordem exata pode depender da iteração das arestas na implementação de Poligono
        boolean hasPoint1 = false;
        boolean hasPoint2 = false;
        
        for (Ponto p : inter) {
            if (Math.abs(p.getX() - 0.0) < 1e-9 && Math.abs(p.getY() - 2.0) < 1e-9) hasPoint1 = true;
            if (Math.abs(p.getX() - 4.0) < 1e-9 && Math.abs(p.getY() - 2.0) < 1e-9) hasPoint2 = true;
        }
        
        assertTrue(hasPoint1, "Deveria conter a interseção em (0.0, 2.0)");
        assertTrue(hasPoint2, "Deveria conter a interseção em (4.0, 2.0)");
    }

    @Test
    public void testIntersectionNoCrossing() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(2, 0),
            new Ponto(2, 2),
            new Ponto(0, 2)
        };
        Quadrado q = new Quadrado(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, -1), new Ponto(-1, 3)};
        Route r = new Route(rotaPts);
        
        List<Ponto> inter = q.intersect(r);
        assertTrue(inter.isEmpty());
    }
}
