import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RetanguloTest {
    @Test
    void createsValidRectangle() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(2, 0);
        Ponto c = new Ponto(2, 1);
        Ponto d = new Ponto(0, 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        assertNotNull(retangulo);
    }

    @Test
    void createsSquare() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        assertNotNull(retangulo);
    }

    @Test
    void handlesLargeCoordinates() {
        Ponto a = new Ponto(1e9, 1e9);
        Ponto b = new Ponto(1e9 + 2, 1e9);
        Ponto c = new Ponto(1e9 + 2, 1e9 + 1);
        Ponto d = new Ponto(1e9, 1e9 + 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        assertNotNull(retangulo);
    }

    @Test
    void createsRectangleWithNegativeCoordinates() {
        Ponto a = new Ponto(-2, -1);
        Ponto b = new Ponto(0, -1);
        Ponto c = new Ponto(0, 0);
        Ponto d = new Ponto(-2, 0);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        assertNotNull(retangulo);
    }

    @Test
    void createsRectangleWithZeroCoordinates() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        assertNotNull(retangulo);
    }

    @Test
    void returnsVerticesArray() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(2, 0);
        Ponto c = new Ponto(2, 1);
        Ponto d = new Ponto(0, 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        Ponto[] vertices = retangulo.getVertices();
        assertEquals(4, vertices.length);
        assertSame(a, vertices[0]);
        assertSame(b, vertices[1]);
        assertSame(c, vertices[2]);
        assertSame(d, vertices[3]);
    }

    @Test
    void intersectsWithRoute() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(2, 0);
        Ponto c = new Ponto(2, 1);
        Ponto d = new Ponto(0, 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        Ponto[] routePoints = {new Ponto(1, -1), new Ponto(1, 1)};
        Route rota = new Route(routePoints);
        assertEquals("(1.00,0.00) (1.00,1.00)", retangulo.intersect(rota));
    }

    @Test
    void doesNotIntersectWithRoute() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(2, 0);
        Ponto c = new Ponto(2, 1);
        Ponto d = new Ponto(0, 1);
        Retangulo retangulo = new Retangulo(a, b, c, d);
        Ponto[] routePoints = {new Ponto(3, 3), new Ponto(4, 4)};
        Route rota = new Route(routePoints);
        assertEquals("null", retangulo.intersect(rota));
    }
}