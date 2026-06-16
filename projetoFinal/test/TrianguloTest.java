import Geometry.figuras.Triangulo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class TrianguloTest {

    @Test
    public void testValidTriangulo() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(2, 4)
        };
        Triangulo t = new Triangulo(vertices);
        assertNotNull(t);
        assertArrayEquals(vertices, t.getVertices());
    }

    @Test
    public void testIntersectionWithRouteCrossing() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(2, 4)
        };
        Triangulo t = new Triangulo(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, 2), new Ponto(5, 2)};
        Route rota = new Route(rotaPts);
        
        List<Ponto> inter = t.intersect(rota);
        assertEquals(2, inter.size());
        
        boolean hasPoint1 = false;
        boolean hasPoint2 = false;
        
        for (Ponto p : inter) {
            if (Math.abs(p.getX() - 1.0) < 1e-9 && Math.abs(p.getY() - 2.0) < 1e-9) hasPoint1 = true;
            if (Math.abs(p.getX() - 3.0) < 1e-9 && Math.abs(p.getY() - 2.0) < 1e-9) hasPoint2 = true;
        }
        
        assertTrue(hasPoint1, "Deveria conter a interseção em (1.0, 2.0)");
        assertTrue(hasPoint2, "Deveria conter a interseção em (3.0, 2.0)");
    }

    @Test
    public void testIntersectionNoCrossing() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(2, 4)
        };
        Triangulo t = new Triangulo(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, -1), new Ponto(5, -1)};
        Route rota = new Route(rotaPts);
        
        List<Ponto> inter = t.intersect(rota);
        assertTrue(inter.isEmpty());
    }
}
