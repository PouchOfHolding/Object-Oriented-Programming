import Geometry.figuras.Poligono;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class PoligonoTest {

    @Test
    public void testValidPoligono() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligono p = new Poligono(vertices);
        assertNotNull(p);
        assertArrayEquals(vertices, p.getVertices());
    }

    @Test
    public void testIntersectionWithRouteCrossingOneEdge() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligono p = new Poligono(vertices);
        
        Ponto[] rotaPts = {new Ponto(1, -1), new Ponto(1, 1)};
        Route r = new Route(rotaPts);
        
        List<Ponto> inter = p.intersect(r);
        assertEquals(1, inter.size());
        assertEquals(1.0, inter.get(0).getX(), 1e-9);
        assertEquals(0.0, inter.get(0).getY(), 1e-9);
    }



    @Test
    public void testIntersectionWithNoCrossing() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligono p = new Poligono(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, -1), new Ponto(-1, 2)};
        Route r = new Route(rotaPts);
        
        List<Ponto> inter = p.intersect(r);
        assertTrue(inter.isEmpty());
    }

    @Test
    public void testIntersectionInsidePolygonNoCrossing() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(4, 0), new Ponto(4, 4), new Ponto(0, 4)};
        Poligono p = new Poligono(vertices);
        
        Ponto[] rotaPts = {new Ponto(1, 1), new Ponto(3, 3)};
        Route r = new Route(rotaPts);
        
        List<Ponto> inter = p.intersect(r);
        assertTrue(inter.isEmpty());
    }
    
    @Test
    public void testIntersectionAtVertex() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligono p = new Poligono(vertices);
        
        Ponto[] rotaPts = {new Ponto(-1, -1), new Ponto(2, 2)};
        Route r = new Route(rotaPts);
        
        List<Ponto> inter = p.intersect(r);
        assertEquals(2, inter.size());
        assertEquals(0.0, inter.get(0).getX(), 1e-9);
        assertEquals(0.0, inter.get(0).getY(), 1e-9);
    }
}
