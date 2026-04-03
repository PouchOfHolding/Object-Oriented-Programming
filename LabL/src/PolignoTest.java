import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class PolignoTest {
    @Test
    void shouldCreatePolignoWithThreeVertices() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(0, 1)};
        Poligno poligno = new Poligno(vertices);
        assertNotNull(poligno);
    }

    @Test
    void shouldReturnVerticesArray() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(0, 1)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] result = poligno.getVertices();
        assertSame(vertices, result);
    }

    @Test
    void shouldReturnNullWhenNoIntersection() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] routePoints = {new Ponto(3, 3), new Ponto(4, 4)};
        Route rota = new Route(routePoints);
        assertEquals("null", poligno.intersect(rota));
    }

    @Test
    void shouldReturnIntersectionPointWhenIntersecting() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] routePoints = {new Ponto(1, -1), new Ponto(1, 1)};
        Route rota = new Route(routePoints);
        assertEquals("(1.00,0.00)", poligno.intersect(rota));
    }

    @Test
    void shouldReturnMultipleIntersections() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(2, 2), new Ponto(0, 2)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] routePoints = {new Ponto(1, -1), new Ponto(1, 3)};
        Route rota = new Route(routePoints);
        assertEquals("(1.00,0.00) (1.00,2.00)", poligno.intersect(rota));
    }

    @Test
    void shouldAvoidDuplicateIntersectionsAtVertices() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1), new Ponto(0, 1)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] routePoints = {new Ponto(1, -1), new Ponto(1, 2)};
        Route rota = new Route(routePoints);
        assertEquals("(1.00,0.00) (1.00,1.00)", poligno.intersect(rota));
    }

    @Test
    void shouldHandleRouteWithMultipleSegmentsIntersecting() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(1, 2)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] routePoints = {new Ponto(0.5, -1), new Ponto(0.5, 1), new Ponto(1.5, 1)};
        Route rota = new Route(routePoints);
        assertEquals("(0.50,0.00)", poligno.intersect(rota));
    }

    @Test
    void shouldReturnNullForRouteWithOnePoint() {
        Ponto[] vertices = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(0, 1)};
        Poligno poligno = new Poligno(vertices);
        Ponto[] routePoints = {new Ponto(0.5, 0.5)};
        Route rota = new Route(routePoints);
        assertEquals("null", poligno.intersect(rota));
    }
}