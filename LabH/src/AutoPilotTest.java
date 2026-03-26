

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AutoPilotTest {
    @Test
    public void speedShouldHandleLargeWindSpeed() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(100, 100);
        AutoPilot autoPilot = new AutoPilot(start, finish);
        Vetor windSpeed = new Vetor(50, 50);
        Vetor result = autoPilot.speed(windSpeed, 10);
        assertEquals(5, result.getX());
        assertEquals(5, result.getY());
    }

    @Test
    public void timeShouldHandleLargeDistance() {
        Ponto start = new Ponto(0, 0);
        Ponto finish = new Ponto(1000, 1000);
        AutoPilot autoPilot = new AutoPilot(start, finish);
        Vetor windSpeed = new Vetor(10, 10);
        double result = autoPilot.time(windSpeed, 100);
        assertEquals(13.997, result, 0.001);
    }
}