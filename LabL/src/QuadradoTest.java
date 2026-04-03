import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class QuadradoTest {
    @Test
    void createsValidSquare() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        assertNotNull(quadrado);
    }

    @Test
    void handlesLargeCoordinates() {
        Ponto a = new Ponto(1e9, 1e9);
        Ponto b = new Ponto(1e9 + 1, 1e9);
        Ponto c = new Ponto(1e9 + 1, 1e9 + 1);
        Ponto d = new Ponto(1e9, 1e9 + 1);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        assertNotNull(quadrado);
    }

    @Test
    void createsSquareWithNegativeCoordinates() {
        Ponto a = new Ponto(-1, -1);
        Ponto b = new Ponto(0, -1);
        Ponto c = new Ponto(0, 0);
        Ponto d = new Ponto(-1, 0);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        assertNotNull(quadrado);
    }

    @Test
    void createsSquareWithZeroCoordinates() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        assertNotNull(quadrado);
    }

    @Test
    void returnsVerticesArray() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        Ponto[] vertices = quadrado.getVertices();
        assertEquals(4, vertices.length);
        assertSame(a, vertices[0]);
        assertSame(b, vertices[1]);
        assertSame(c, vertices[2]);
        assertSame(d, vertices[3]);
    }

    @Test
    void intersectsWithRoute() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        Ponto[] routePoints = {new Ponto(0.5, -1), new Ponto(0.5, 2)};
        Route rota = new Route(routePoints);
        assertEquals("(0.50,0.00) (0.50,1.00)", quadrado.intersect(rota));
    }

    @Test
    void doesNotIntersectWithRoute() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(1, 1);
        Ponto d = new Ponto(0, 1);
        Quadrado quadrado = new Quadrado(a, b, c, d);
        Ponto[] routePoints = {new Ponto(2, 2), new Ponto(3, 3)};
        Route rota = new Route(routePoints);
        assertEquals("null", quadrado.intersect(rota));
    }
}