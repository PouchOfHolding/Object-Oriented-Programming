import Geometry.figuras.Circulo;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class CirculoTest {

    @Test
    public void createsCircleWithPositiveRadius() {
        Ponto centro = new Ponto(0, 0);
        Circulo c = new Circulo(centro, 1.0);
        assertNotNull(c);
    }

    @Test
    public void createsCircleWithSmallPositiveRadius() {
        Ponto centro = new Ponto(0, 0);
        Circulo c = new Circulo(centro, 1e-8);
        assertNotNull(c);
    }

    @Test
    public void returnsEmptyListWhenRouteDoesNotIntersectCircle() {
        Circulo c = new Circulo(new Ponto(0, 0), 1);
        Ponto[] pontos = {new Ponto(2, 2), new Ponto(3, 3)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertTrue(inter.isEmpty());
    }

    @Test
    public void returnsTwoIntersectionsWhenSegmentCrossesCircle() {
        Circulo c = new Circulo(new Ponto(0, 0), 1);
        Ponto[] pontos = {new Ponto(-2, 0), new Ponto(2, 0)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertEquals(2, inter.size());
        assertEquals(-1, inter.get(0).getX(), 1e-9);
        assertEquals(0, inter.get(0).getY(), 1e-9);
        assertEquals(1, inter.get(1).getX(), 1e-9);
        assertEquals(0, inter.get(1).getY(), 1e-9);
    }

    @Test
    public void returnsOneIntersectionWhenSegmentIsTangentToCircle() {
        Circulo c = new Circulo(new Ponto(0, 0), 1);
        Ponto[] pontos = {new Ponto(1, 1), new Ponto(1, -1)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertEquals(1, inter.size());
        assertEquals(1, inter.get(0).getX(), 1e-9);
        assertEquals(0, inter.get(0).getY(), 1e-9);
    }

    @Test
    public void returnsIntersectionsFromMultipleSegments() {
        Circulo c = new Circulo(new Ponto(0, 0), 1);
        Ponto[] pontos = {new Ponto(-2, 0), new Ponto(0, 0), new Ponto(2, 0)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertEquals(2, inter.size());
        assertEquals(-1, inter.get(0).getX(), 1e-9);
        assertEquals(0, inter.get(0).getY(), 1e-9);
        assertEquals(1, inter.get(1).getX(), 1e-9);
        assertEquals(0, inter.get(1).getY(), 1e-9);
    }

    @Test
    public void includesIntersectionWhenSegmentStartsOnCircle() {
        Circulo c = new Circulo(new Ponto(0, 0), 1);
        Ponto[] pontos = {new Ponto(1, 0), new Ponto(2, 0)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertEquals(1, inter.size());
        assertEquals(1, inter.get(0).getX(), 1e-9);
        assertEquals(0, inter.get(0).getY(), 1e-9);
    }

    @Test
    public void includesIntersectionWhenSegmentEndsOnCircle() {
        Circulo c = new Circulo(new Ponto(0, 0), 1);
        Ponto[] pontos = {new Ponto(2, 0), new Ponto(1, 0)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertEquals(1, inter.size());
        assertEquals(1, inter.get(0).getX(), 1e-9);
        assertEquals(0, inter.get(0).getY(), 1e-9);
    }

    @Test
    public void returnsEmptyListWhenRouteIsInsideCircleWithoutCrossing() {
        Circulo c = new Circulo(new Ponto(0, 0), 2);
        Ponto[] pontos = {new Ponto(0.5, 0), new Ponto(1, 0)};
        Route r = new Route(pontos);
        List<Ponto> inter = c.intersect(r);
        assertTrue(inter.isEmpty());
    }
}
