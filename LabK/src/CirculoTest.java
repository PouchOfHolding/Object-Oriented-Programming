import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class CirculoTest {
    @Test
    void createsValidCircle() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        assertNotNull(circulo);
    }

    @Test
    void handlesLargeRadius() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1e9);
        assertNotNull(circulo);
    }

    @Test
    void createsCircleWithNegativeCenterCoordinates() {
        Ponto centro = new Ponto(-1, -1);
        Circulo circulo = new Circulo(centro, 1.0);
        assertNotNull(circulo);
    }

    @Test
    void createsCircleWithZeroCenterCoordinates() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        assertNotNull(circulo);
    }

    @Test
    void returnsCenter() {
        Ponto centro = new Ponto(1, 2);
        Circulo circulo = new Circulo(centro, 1.0);
        assertSame(centro, circulo.getCentro());
    }

    @Test
    void returnsRadius() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 2.5);
        assertEquals(2.5, circulo.getRaio());
    }

    @Test
    void doesNotIntersectWithRoute() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        Ponto[] routePoints = {new Ponto(2, 2), new Ponto(3, 3)};
        Route rota = new Route(routePoints);
        assertEquals("null", circulo.intersect(rota));
    }

    @Test
    void intersectsAtTangentPoint() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        Ponto[] routePoints = {new Ponto(1, -1), new Ponto(1, 1)};
        Route rota = new Route(routePoints);
        assertEquals("(1.00,0.00)", circulo.intersect(rota));
    }

    @Test
    void intersectsAtTwoPoints() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        Ponto[] routePoints = {new Ponto(0, -2), new Ponto(0, 2)};
        Route rota = new Route(routePoints);
        assertEquals("(0.00,-1.00) (0.00,1.00)", circulo.intersect(rota));
    }

    @Test
    void intersectsWithMultipleSegments() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        Ponto[] routePoints = {new Ponto(0, -2), new Ponto(0, 0), new Ponto(2, 0)};
        Route rota = new Route(routePoints);
        assertEquals("(0.00,-1.00) (0.00,1.00) (1.00,0.00)", circulo.intersect(rota));
    }

    @Test
    void returnsNullForRouteWithOnePoint() {
        Ponto centro = new Ponto(0, 0);
        Circulo circulo = new Circulo(centro, 1.0);
        Ponto[] routePoints = {new Ponto(0.5, 0.5)};
        Route rota = new Route(routePoints);
        assertEquals("null", circulo.intersect(rota));
    }
}