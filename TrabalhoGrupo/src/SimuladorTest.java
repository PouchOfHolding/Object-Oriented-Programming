import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class SimuladorTest {

    @Test
    void testConstrutorSimulador() {
        Vetor corrente = new Vetor(1.0, 2.0);

        Simulador simulador = new Simulador(corrente);

        assertNotNull(simulador, "O objeto simulador não deve ser nulo");
    }

    @Test
    void testAddPorto() {
        // Preparação
        Vetor corrente = new Vetor(1.0, 2.0);
        Simulador simulador = new Simulador(corrente);
        Ponto localPorto = new Ponto(10.5, 20.5);

        simulador.addPorto(localPorto);
        assertNotNull(simulador.getportos(), "A lista de portos não deve ser nula");
        assertEquals(1, simulador.getportos().size(), "O simulador deve ter 1 porto após o addPorto");
        Ponto localResultante = simulador.getportos().get(0).getlocalporto();
        assertEquals(localPorto.getX(), localResultante.getX(), 1e-9);
        assertEquals(localPorto.getY(), localResultante.getY(), 1e-9);
    }

    @Test
    void testAddObstaculo() {
        Simulador simulador = new Simulador(new Vetor(0, 0));
        Poligono quadrado = new Poligono();
        simulador.addObstaculo(quadrado);
        assertNotNull(simulador.getObstaculos(), "A lista de obstáculos deve existir");
        assertEquals(1, simulador.getObstaculos().size(), "Deve haver 1 obstáculo na lista");
        assertTrue(simulador.getObstaculos().contains(quadrado), "O obstáculo na lista deve ser o que foi adicionado");
    }

    @Test
    void testAddRota() {
        Simulador simulador = new Simulador(new Vetor(0, 0));
        Route rotaTeste = new Route();
        simulador.addRota(rotaTeste);

        assertNotNull(simulador.getRotas(), "A lista de rotas não deve ser nula");
        assertEquals(1, simulador.getRotas().size(), "Deve existir 1 rota no simulador");
        assertEquals(rotaTeste, simulador.getRotas().get(0), "A rota guardada deve ser a mesma que foi passada");
    }

    @Test
    void testRelogioJogo() {
        Simulador simulador = new Simulador(new Vetor(1, 0));
        double avanco = 5.5;
        simulador.relogiojogo(avanco);
        assertTrue(simulador.getTempoAtual() > 0, "O tempo do simulador deve ter avançado");
    }

    @Test
    void testGetEstadoSim() {
        Vetor corrente = new Vetor(1.0, 0.0);
        Simulador simulador = new Simulador(corrente);

        simulador.addPorto(new Ponto(10, 10));
        simulador.addObstaculo(new Poligono());
        simulador.relogiojogo(10.0);
        transferenciados estado = simulador.getEstadoSim();
        assertNotNull(estado, "O estado retornado não deve ser nulo");
        assertEquals(1, estado.getListaPortas().size(), "O estado deve reportar 1 porto");
        assertEquals(1, estado.getListaObstaculos().size(), "O estado deve reportar 1 obstáculo");
        assertTrue(estado.getTempoAtual() >= 10);
    }



}