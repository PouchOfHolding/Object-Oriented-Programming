import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

import java.util.List;

public class RouteTest {


    @Test
    public void testLength() {
        Ponto[] pontos = { new Ponto(5,1), new Ponto(5,5), new Ponto(7,5) };
        Route r = new Route(pontos);
        assertEquals(6.0, r.length(), 1e-9);
    }

    @Test
    public void testPosition() {
        Ponto[] pontos = { new Ponto(5,1), new Ponto(5,5), new Ponto(7,5) };
        Route r = new Route(pontos);
        Ponto p = r.position(2.25, 2);
        assertEquals(5.50, p.getX(), 1e-9);
        assertEquals(5.00, p.getY(), 1e-9);
    }

    @Test
    public void testSpeeds() {
        Ponto[] pontos = { new Ponto(5,1), new Ponto(5,5), new Ponto(7,5) };
        Route r = new Route(pontos);
        Vetor[] speeds = r.speeds(new Vetor(1,1), 2);
        assertEquals(-1.00, speeds[0].getX(), 1e-9);
        assertEquals( 1.00, speeds[0].getY(), 1e-9);
        assertEquals( 1.00, speeds[1].getX(), 1e-9);
        assertEquals(-1.00, speeds[1].getY(), 1e-9);
    }

    @Test
    public void testConstrutorCoordsEGetPontos() {

        double[] coords = {0.0, 0.0, 10.0, 10.0, 20.0, 0.0};
        Route r = new Route(coords);

        Ponto[] pontos = r.getPontos();

        assertNotNull(pontos, "O array de pontos não deve ser nulo");
        assertEquals(3, pontos.length, "Devem ter sido criados exatamente 3 pontos");

        assertEquals(0.0, pontos[0].getX(), 1e-9);
        assertEquals(0.0, pontos[0].getY(), 1e-9);
        assertEquals(10.0, pontos[1].getX(), 1e-9);
        assertEquals(10.0, pontos[1].getY(), 1e-9);
    }

    @Test
    public void testIntersectSegmentoReta() {

        Route r = new Route(new double[]{0.0, 0.0, 10.0, 10.0, 20.0, 0.0});

        SegmentoReta sr = new SegmentoReta(new Ponto(0, 5), new Ponto(20, 5));

        List<Ponto> intersecoes = r.intersect(sr);

        assertEquals(2, intersecoes.size(), "Devem ser detetadas 2 interseções");

        assertEquals(5.0, intersecoes.get(0).getX(), 1e-9);
        assertEquals(5.0, intersecoes.get(0).getY(), 1e-9);

        assertEquals(15.0, intersecoes.get(1).getX(), 1e-9);
        assertEquals(5.0, intersecoes.get(1).getY(), 1e-9);
    }

    @Test
    public void testCalcularRotaMaisCurtaDijkstra() {

        Ponto p0 = new Ponto(0, 0);
        Ponto p1 = new Ponto(10, 0);
        Ponto p2 = new Ponto(10, 10);
        Ponto p3 = new Ponto(20, 10);

        Ponto[] todosPontos = {p0, p1, p2, p3};

        int[][] matrizPesos = {
                {0,  10, 30,  0}, // Ligações do p0
                {10,  0, 10,  0}, // Ligações do p1
                {30, 10,  0, 10}, // Ligações do p2
                {0,   0, 10,  0}  // Ligações do p3
        };

        Route rotaCalculada = Route.calcularRotaMaisCurta(todosPontos, matrizPesos, 0, 3);

        assertNotNull(rotaCalculada, "O algoritmo deve encontrar um caminho");
        Ponto[] pontosRota = rotaCalculada.getPontos();

        assertEquals(4, pontosRota.length, "A rota ideal deve passar por 4 nós");
        assertEquals(p0, pontosRota[0]);
        assertEquals(p1, pontosRota[1]);
        assertEquals(p2, pontosRota[2]);
        assertEquals(p3, pontosRota[3]);
    }

    @Test
    public void testCalcularRotaMaisCurtaSemLigacao() {
        Ponto[] pontos = {new Ponto(0, 0), new Ponto(10, 10)};

        int[][] matrizSemLigacao = {
                {0, 0},
                {0, 0}
        };

        Route rota = Route.calcularRotaMaisCurta(pontos, matrizSemLigacao, 0, 1);

        assertNull(rota, "Deve retornar null quando é impossível chegar ao destino");
    }
}