import navPort.*;

import Geometry.Ponto;
import Geometry.Route;
import Geometry.Vetor;
import Geometry.figuras.Circulo;
import Geometry.figuras.Figura;
import Interface.padraoState.EstadoEspera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SimuladorTest {

    private Simulador simulador;
    private Vetor correntePadrao;

    @BeforeEach
    void setUp() throws Exception {
        // Limpar o Singleton antes de cada teste com Reflection
        Field instanciaUnicaField = Simulador.class.getDeclaredField("instanciaUnica");
        instanciaUnicaField.setAccessible(true);
        instanciaUnicaField.set(null, null);

        correntePadrao = new Vetor(1.0, 1.0);
        simulador = Simulador.getInstance(correntePadrao);
    }

    @Test
    void testGetInstance() {
        Simulador novaInstancia = Simulador.getInstance(new Vetor(2.0, 2.0));
        assertSame(simulador, novaInstancia, "getInstance deve retornar sempre a mesma instância na memória");
        assertEquals(1.0, novaInstancia.getCorrenteAtual().getX(), "A corrente não deve ser alterada se a instância já existir");
    }

    @Test
    void testAdicionarERecuperarPortos() {
        Porto portoA = new Porto("A", new Ponto(10, 10));
        simulador.addPorto(portoA);

        assertEquals(1, simulador.getPortos().size());
        assertEquals(portoA, simulador.getPortoByNome("A"));
        assertNull(simulador.getPortoByNome("B"), "Deve retornar null para um porto inexistente");
    }

    @Test
    void testAdicionarERecuperarRotas() {
        Route rota = new Route(new double[]{0, 0, 10, 10});
        simulador.addRota(rota);

        assertEquals(1, simulador.getRotas().size());
        assertEquals(rota, simulador.getRotas().get(0));
    }

    @Test
    void testAdicionarERecuperarObstaculosFixos() {
        Figura figuraSegura = new Figura() {
            @Override
            public List<Ponto> intersect(Route r) {
                return new ArrayList<>();
            }
        };
        simulador.addObstaculoFixo(figuraSegura);

        assertEquals(1, simulador.getObstaculoFixo().size());
        assertEquals(figuraSegura, simulador.getObstaculoFixo().get(0));
    }

    @Test
    void testAdicionarERecuperarObstaculosMoveis() {
        Circulo circulo = new Circulo(new Ponto(5, 5), 10.0);
        simulador.addObstaculoMovel(circulo);

        assertEquals(1, simulador.getObstaculosMoveis().size());
        assertEquals(circulo, simulador.getObstaculosMoveis().get(0));
    }

    @Test
    void testGerarObstaculosMoveisAleatorios() {
        simulador.addRota(new Route(new double[]{0, 0, 100, 100}));
        simulador.addRota(new Route(new double[]{100, 100, 200, 200}));

        simulador.gerarObstaculosMoveisAleatorios(2);
        assertEquals(2, simulador.getObstaculosMoveis().size(), "Devem ter sido gerados 2 obstáculos móveis");
    }

    @Test
    void testAvancarTempoEDespacharNavios() {
        Porto porto = new Porto("Origem", new Ponto(0, 0));
        Porto destino = new Porto("Destino", new Ponto(100, 100));
        simulador.addPorto(porto);
        simulador.addPorto(destino);

        simulador.addRota(new Route(new double[]{0, 0, 100, 100}));

        // Navio programado para sair no tempo 1
        Navio navio = new Navio("N1", "Destino", 1, 10.0);
        porto.addNavio(navio);

        int novoTempo = simulador.avancarTempo();

        assertEquals(1, novoTempo, "O tempo deve ter avançado para 1");
        assertEquals(0, porto.getNaviosEmEspera().size(), "O navio deve ter saído do porto");
        assertEquals(1, simulador.getNaviosNoMar().size(), "O navio deve estar no mar");
    }

    @Test
    void testCalcularRotaParaNavioDijkstra() {
        Ponto p1 = new Ponto(0, 0);   // Origem
        Ponto p3 = new Ponto(20, 0);  // Destino

        // Adicionamos duas rotas que se conectam no ponto (10,0)
        simulador.addRota(new Route(new double[]{0,0, 10,0}));
        simulador.addRota(new Route(new double[]{10,0, 20,0}));

        Route rotaCalculada = simulador.calcularRotaParaNavio(p1, p3, false);

        assertNotNull(rotaCalculada, "Deve encontrar uma rota válida");
        Ponto[] pontosRota = rotaCalculada.getPontos();

        assertEquals(3, pontosRota.length, "O caminho mais curto deve conter 3 pontos");
        assertEquals(0.0, pontosRota[0].getX());
        assertEquals(10.0, pontosRota[1].getX());
        assertEquals(20.0, pontosRota[2].getX());
    }

    @Test
    void testIsSimulacaoTerminada() {
        Porto porto = new Porto("A", new Ponto(0, 0));
        simulador.addPorto(porto);

        assertFalse(simulador.isSimulacaoTerminada(), "De acordo com o Simulador, se não há navios no mar, retorna false");

        Navio navioQueChegou = new Navio("N1", "B", 1, 10);
        simulador.getNaviosNoMar().add(navioQueChegou);
        navioQueChegou.setEstado(new EstadoEspera()); // O navio já terminou a rota

        assertTrue(simulador.isSimulacaoTerminada(), "Se todos os navios no mar estiverem em espera (chegaram), retorna true");
    }
}