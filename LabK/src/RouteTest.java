import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RouteTest {
    @Test
    void shouldReturnCopyOfPointsWhenGettingPontos() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        Ponto[] result = route.getPontos();
        assertNotSame(pontos, result);
        assertEquals(pontos.length, result.length);
        for (int i = 0; i < pontos.length; i++) {
            assertEquals(pontos[i].getX(), result[i].getX());
            assertEquals(pontos[i].getY(), result[i].getY());
        }
    }

    @Test
    void shouldReturnEmptyArrayWhenRouteHasOnePoint() {
        Ponto[] pontos = {new Ponto(0, 0)};
        Route route = new Route(pontos);
        SegmentoReta[] segmentos = route.segmentos();
        assertEquals(0, segmentos.length);
    }

    @Test
    void shouldReturnSingleSegmentWhenRouteHasTwoPoints() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        SegmentoReta[] segmentos = route.segmentos();
        assertEquals(1, segmentos.length);
        assertEquals(pontos[0], segmentos[0].getPonto());
        assertEquals(1, segmentos[0].getVetor().getX());
        assertEquals(1, segmentos[0].getVetor().getY());
    }

    @Test
    void shouldReturnMultipleSegmentsWhenRouteHasMultiplePoints() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        SegmentoReta[] segmentos = route.segmentos();
        assertEquals(2, segmentos.length);
    }

    @Test
    void shouldReturnZeroLengthWhenRouteHasOnePoint() {
        Ponto[] pontos = {new Ponto(0, 0)};
        Route route = new Route(pontos);
        assertEquals(0, route.comprimento());
    }

    @Test
    void shouldCalculateLengthForTwoPoints() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(3, 4)};
        Route route = new Route(pontos);
        assertEquals(5, route.comprimento());
    }

    @Test
    void shouldCalculateTotalLengthForMultiplePoints() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        assertEquals(2, route.comprimento());
    }

    @Test
    void shouldReturnNullWhenNoIntersection() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 0)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(0, 1), new Ponto(1, 1));
        assertEquals("null", route.intersect(s));
    }

    @Test
    void shouldReturnIntersectionPointWhenIntersecting() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(2, 0)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(1, -1), new Ponto(1, 1));
        assertEquals("(1.00,0.00)", route.intersect(s));
    }

    @Test
    void shouldReturnMultipleIntersectionsWhenIntersectingMultipleSegments() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(2, 2)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(1, -1), new Ponto(1, 1));
        assertEquals("(1.00,0.00)", route.intersect(s));
    }

    @Test
    void shouldAvoidDuplicateIntersectionsAtVertices() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(1, -1), new Ponto(1, 2));
        assertEquals("(1.00,0.00) (1.00,1.00)", route.intersect(s));
    }

    @Test
    void shouldReturnNullForRouteWithOnePoint() {
        Ponto[] pontos = {new Ponto(0, 0)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(0, 1), new Ponto(1, 1));
        assertEquals("null", route.intersect(s));
    }
}