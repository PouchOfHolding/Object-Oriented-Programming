import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RouteTest {

    @Test
    void returnsCopyOfPoints() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        Ponto[] copia = route.getPontos();
        assertNotSame(pontos, copia);
        assertEquals(pontos.length, copia.length);
        for (int i = 0; i < pontos.length; i++) {
            assertEquals(pontos[i].getX(), copia[i].getX());
            assertEquals(pontos[i].getY(), copia[i].getY());
        }
    }

    @Test
    void calculatesTotalLengthForMultipleSegments() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(3, 4), new Ponto(3, 8)};
        Route route = new Route(pontos);
        double expected = 5.0 + 4.0; // 5 + 4 = 9
        assertEquals(expected, route.comprimento(), 1e-10);
    }

    @Test
    void calculatesLengthForSingleSegment() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(6, 8)};
        Route route = new Route(pontos);
        double expected = 10.0;
        assertEquals(expected, route.comprimento(), 1e-10);
    }

    @Test
    void calculatesLengthForZeroLengthRoute() {
        Ponto[] pontos = {new Ponto(1, 1)};
        Route route = new Route(pontos);
        double expected = 0.0;
        assertEquals(expected, route.comprimento(), 1e-10);
    }

    @Test
    void generatesCorrectSegments() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(1, 0), new Ponto(1, 1)};
        Route route = new Route(pontos);
        SegmentoReta[] segs = route.segmentos();
        assertEquals(2, segs.length);
        assertEquals(0, segs[0].getPonto().getX(), 1e-10);
        assertEquals(0, segs[0].getPonto().getY(), 1e-10);
        assertEquals(1, segs[0].getVetor().getX(), 1e-10);
        assertEquals(0, segs[0].getVetor().getY(), 1e-10);
        assertEquals(1, segs[1].getPonto().getX(), 1e-10);
        assertEquals(0, segs[1].getPonto().getY(), 1e-10);
        assertEquals(0, segs[1].getVetor().getX(), 1e-10);
        assertEquals(1, segs[1].getVetor().getY(), 1e-10);
    }

    @Test
    void returnsNullForNoIntersection() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(2, 0)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(0, 1), new Ponto(2, 1));
        assertEquals("null", route.intersect(s));
    }

    @Test
    void returnsSingleIntersectionPoint() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(2, 0)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(1, -1), new Ponto(1, 1));
        assertEquals("(1.00,0.00)", route.intersect(s));
    }

    @Test
    void returnsMultipleIntersectionPoints() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(2, 0), new Ponto(2, 2)};
        Route route = new Route(pontos);
        SegmentoReta s = new SegmentoReta(new Ponto(1, -1), new Ponto(1, 1));
        String result = route.intersect(s);
        assertTrue(result.contains("(1.00,0.00)"));
        assertFalse(result.contains("(1.00,1.00)")); // no intersection with second segment
    }

    @Test
    void returnsPositionAtStartTime() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(4, 0)};
        Route route = new Route(pontos);
        Ponto pos = route.ondeesta(2.0, 0.0);
        assertEquals(0, pos.getX(), 1e-10);
        assertEquals(0, pos.getY(), 1e-10);
    }

    @Test
    void returnsPositionAtEndTime() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(4, 0)};
        Route route = new Route(pontos);
        double totalTime = 4.0 / 2.0; // 2
        Ponto pos = route.ondeesta(2.0, totalTime);
        assertEquals(4, pos.getX(), 1e-10);
        assertEquals(0, pos.getY(), 1e-10);
    }

    @Test
    void returnsPositionBeyondEndTime() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(4, 0)};
        Route route = new Route(pontos);
        Ponto pos = route.ondeesta(2.0, 10.0);
        assertEquals(4, pos.getX(), 1e-10);
        assertEquals(0, pos.getY(), 1e-10);
    }

    @Test
    void returnsPositionInMiddleOfSegment() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(4, 0), new Ponto(4, 3)};
        Route route = new Route(pontos);
        // First segment: length 4, time 4/2=2
        // At t=1.5, still in first segment
        Ponto pos = route.ondeesta(2.0, 1.5);
        assertEquals(3, pos.getX(), 1e-10); // 0 + 2*1.5
        assertEquals(0, pos.getY(), 1e-10);
    }

    @Test
    void returnsPositionAtSegmentBoundary() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(4, 0), new Ponto(4, 3)};
        Route route = new Route(pontos);
        // At t=2, exactly at end of first segment
        Ponto pos = route.ondeesta(2.0, 2.0);
        assertEquals(4, pos.getX(), 1e-10);
        assertEquals(0, pos.getY(), 1e-10);
    }

    @Test
    void returnsStartPointForSinglePointRoute() {
        Ponto[] pontos = {new Ponto(5, 5)};
        Route route = new Route(pontos);
        Ponto pos = route.ondeesta(1.0, 10.0);
        assertEquals(5, pos.getX(), 1e-10);
        assertEquals(5, pos.getY(), 1e-10);
    }
}