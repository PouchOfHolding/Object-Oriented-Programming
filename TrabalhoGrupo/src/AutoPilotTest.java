import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AutoPilotTest {

    @Test
    void calculatesTimeForPositiveDistanceAndVelocity() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(3, 4);
        AutoPilot ap = new AutoPilot(start, finish);
        double vl = 5.0;
        double expected = 5.0 / 5.0; // distance is 5, vl=5, time=1
        assertEquals(expected, ap.time(vl), 1e-10);
    }

    @Test
    void calculatesTimeForZeroDistance() {
        Ponto start = new Ponto(1, 1);
        Ponto finish = new Ponto(1, 1);
        AutoPilot ap = new AutoPilot(start, finish);
        double vl = 10.0;
        double expected = 0.0;
        assertEquals(expected, ap.time(vl), 1e-10);
    }

    @Test
    void calculatesTimeForLargeVelocity() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(0, 10);
        AutoPilot ap = new AutoPilot(start, finish);
        double vl = 100.0;
        double expected = 10.0 / 100.0;
        assertEquals(expected, ap.time(vl), 1e-10);
    }

    @Test
    void calculatesAirSpeedWithTailWind() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(10, 0);
        SegmentoReta s = new SegmentoReta(start, finish);
        Vetor wind = new Vetor(2, 0); // tail wind
        double vl = 8.0; // desired ground speed
        AutoPilot ap = new AutoPilot(start, finish);
        Vetor airSpeed = ap.speed(s, wind, vl);
        // vSolo = s.getVetor().mult(vl / s.comprimento()) = (10,0).mult(8/10) = (8,0)
        // airSpeed = (8,0) - (2,0) = (6,0)
        assertEquals(6.0, airSpeed.getX(), 1e-10);
        assertEquals(0.0, airSpeed.getY(), 1e-10);
    }

    @Test
    void calculatesAirSpeedWithHeadWind() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(10, 0);
        SegmentoReta s = new SegmentoReta(start, finish);
        Vetor wind = new Vetor(-3, 0); // head wind
        double vl = 8.0;
        AutoPilot ap = new AutoPilot(start, finish);
        Vetor airSpeed = ap.speed(s, wind, vl);
        // vSolo = (10,0).mult(8/10) = (8,0)
        // airSpeed = (8,0) - (-3,0) = (11,0)
        assertEquals(11.0, airSpeed.getX(), 1e-10);
        assertEquals(0.0, airSpeed.getY(), 1e-10);
    }

    @Test
    void calculatesAirSpeedWithCrossWind() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(6, 8); // distance 10
        SegmentoReta s = new SegmentoReta(start, finish);
        Vetor wind = new Vetor(0, 2);
        double vl = 5.0;
        AutoPilot ap = new AutoPilot(start, finish);
        Vetor airSpeed = ap.speed(s, wind, vl);
        // vSolo = (6,8).mult(5/10) = (3,4)
        // airSpeed = (3,4) - (0,2) = (3,2)
        assertEquals(3.0, airSpeed.getX(), 1e-10);
        assertEquals(2.0, airSpeed.getY(), 1e-10);
    }

    @Test
    void calculatesAirSpeedWithZeroWind() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(4, 3); // distance 5
        SegmentoReta s = new SegmentoReta(start, finish);
        Vetor wind = new Vetor(0, 0);
        double vl = 5.0;
        AutoPilot ap = new AutoPilot(start, finish);
        Vetor airSpeed = ap.speed(s, wind, vl);
        // vSolo = (4,3).mult(5/5) = (4,3)
        // airSpeed = (4,3) - (0,0) = (4,3)
        assertEquals(4.0, airSpeed.getX(), 1e-10);
        assertEquals(3.0, airSpeed.getY(), 1e-10);
    }
}