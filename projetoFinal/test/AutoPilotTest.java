import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class AutoPilotTest {


    // ---- time() ----

    @Test
    public void testTime1() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        assertEquals(5.00, ap.time(0.4), 1e-9);
    }

    @Test
    public void testTime2() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 4), new Ponto(3, 2));
        assertEquals(5.00, ap.time(0.4), 1e-9);
    }

    @Test
    public void testTimeHorizontal() {
        // segmento horizontal de comprimento 2, vl = 1
        AutoPilot ap = new AutoPilot(new Ponto(0, 0), new Ponto(2, 0));
        assertEquals(2.0, ap.time(1.0), 1e-9);
    }

    @Test
    public void testTimeDiagonal() {
        // segmento diagonal, distância = sqrt(2)
        AutoPilot ap = new AutoPilot(new Ponto(0, 0), new Ponto(1, 1));
        assertEquals(Math.sqrt(2), ap.time(1.0), 1e-9);
    }

    // ---- speed() ----

    @Test
    public void testSpeed1() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 2), new Ponto(3, 4));
        Vetor w = new Vetor(0.2, 0.2);
        double t = ap.time(0.4); // t = 5
        Vetor s = ap.speed(w, t);
        assertEquals(-0.20, s.getX(), 1e-9);
        assertEquals( 0.20, s.getY(), 1e-9);
    }

    @Test
    public void testSpeed2() {
        AutoPilot ap = new AutoPilot(new Ponto(3, 4), new Ponto(3, 2));
        Vetor w = new Vetor(0.2, 0.2);
        double t = ap.time(0.4); // t = 5
        Vetor s = ap.speed(w, t);
        assertEquals(-0.20, s.getX(), 1e-9);
        assertEquals(-0.60, s.getY(), 1e-9);
    }

    @Test
    public void testSpeedNoWind() {
        // sem vento, velocidade = r/t
        AutoPilot ap = new AutoPilot(new Ponto(0, 0), new Ponto(2, 0));
        Vetor w = new Vetor(0.0, 1.0); // vento só em y
        double t = 2.0;
        Vetor s = ap.speed(w, t);
        assertEquals(1.0,  s.getX(), 1e-9); // r/t = 2/2 = 1
        assertEquals(-1.0, s.getY(), 1e-9); // 0 - w.y = -1
    }
}