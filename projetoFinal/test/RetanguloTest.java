import Geometry.figuras.Retangulo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class RetanguloTest {

    @Test
    public void testValidRetangulo() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(4, 2),
            new Ponto(0, 2)
        };
        Retangulo r = new Retangulo(vertices);
        assertNotNull(r);
        assertArrayEquals(vertices, r.getVertices());
    }

    @Test
    public void testIntersectionWithRouteCrossing() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(4, 2),
            new Ponto(0, 2)
        };
        Retangulo r = new Retangulo(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, 1), new Ponto(5, 1)};
        Route rota = new Route(rotaPts);
        
        List<Ponto> inter = r.intersect(rota);
        assertEquals(2, inter.size());
        
        boolean hasPoint1 = false;
        boolean hasPoint2 = false;
        
        for (Ponto p : inter) {
            if (Math.abs(p.getX() - 0.0) < 1e-9 && Math.abs(p.getY() - 1.0) < 1e-9) hasPoint1 = true;
            if (Math.abs(p.getX() - 4.0) < 1e-9 && Math.abs(p.getY() - 1.0) < 1e-9) hasPoint2 = true;
        }
        
        assertTrue(hasPoint1, "Deveria conter a interseção em (0.0, 1.0)");
        assertTrue(hasPoint2, "Deveria conter a interseção em (4.0, 1.0)");
    }

    @Test
    public void testIntersectionNoCrossing() {
        Ponto[] vertices = {
            new Ponto(0, 0),
            new Ponto(4, 0),
            new Ponto(4, 2),
            new Ponto(0, 2)
        };
        Retangulo r = new Retangulo(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, -1), new Ponto(5, -1)};
        Route rota = new Route(rotaPts);
        
        List<Ponto> inter = r.intersect(rota);
        assertTrue(inter.isEmpty());
    }
}
