import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TrianguloTest {

    @Test
    void createsValidTriangle() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(0, 1);
        Triangulo triangulo = new Triangulo(a, b, c);
        assertNotNull(triangulo);
    }

    @Test
    void handlesLargeCoordinates() {
        Ponto a = new Ponto(1e9, 1e9);
        Ponto b = new Ponto(1e9 + 1, 1e9);
        Ponto c = new Ponto(1e9, 1e9 + 1);
        Triangulo triangulo = new Triangulo(a, b, c);
        assertNotNull(triangulo);
    }

    @Test
    void createsTriangleWithNegativeCoordinates() {
        Ponto a = new Ponto(-1, -1);
        Ponto b = new Ponto(0, -1);
        Ponto c = new Ponto(-1, 0);
        Triangulo triangulo = new Triangulo(a, b, c);
        assertNotNull(triangulo);
    }

    @Test
    void createsTriangleWithZeroCoordinates() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(0, 1);
        Ponto c = new Ponto(1, 0);
        Triangulo triangulo = new Triangulo(a, b, c);
        assertNotNull(triangulo);
    }

    @Test
    void returnsVerticesArray() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(1, 0);
        Ponto c = new Ponto(0, 1);
        Triangulo triangulo = new Triangulo(a, b, c);
        Ponto[] vertices = triangulo.getVertices();
        assertEquals(3, vertices.length);
        assertSame(a, vertices[0]);
        assertSame(b, vertices[1]);
        assertSame(c, vertices[2]);
    }

    @Test
    void intersectsWithRoute() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(2, 0);
        Ponto c = new Ponto(1, 2);
        Triangulo triangulo = new Triangulo(a, b, c);
        Ponto[] routePoints = {new Ponto(1, -1), new Ponto(1, 1)};
        Route rota = new Route(routePoints);
        assertEquals("(1.00,0.00)", triangulo.intersect(rota));
    }

    @Test
    void doesNotIntersectWithRoute() {
        Ponto a = new Ponto(0, 0);
        Ponto b = new Ponto(2, 0);
        Ponto c = new Ponto(1, 2);
        Triangulo triangulo = new Triangulo(a, b, c);
        Ponto[] routePoints = {new Ponto(3, 3), new Ponto(4, 4)};
        Route rota = new Route(routePoints);
        assertEquals("null", triangulo.intersect(rota));
    }
}