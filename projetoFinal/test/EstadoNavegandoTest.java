

import Geometry.Ponto;
import Geometry.Route;
import Geometry.Vetor;
import Geometry.figuras.Circulo;
import Interface.padraoState.EstadoAguardando;
import Interface.padraoState.EstadoEspera;
import Interface.padraoState.EstadoNavegando;
import navPort.Navio;
import navPort.Porto;
import navPort.Simulador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class EstadoNavegandoTest {

    private Simulador simulador;
    private EstadoNavegando estadoNavegando;
    private Navio navio;

    @BeforeEach
    void setUp() throws Exception {
        // Reset ao Singleton do Simulador
        Field instanciaUnicaField = Simulador.class.getDeclaredField("instanciaUnica");
        instanciaUnicaField.setAccessible(true);
        instanciaUnicaField.set(null, null);

        simulador = Simulador.getInstance(new Vetor(1.0, 1.0));
        estadoNavegando = new EstadoNavegando();

        // Inicializar um Navio padrão para os testes
        navio = new Navio("N1", "Destino", 1, 10.0);
        Route rota = new Route(new double[]{0, 0, 100, 0}); // Rota em linha reta no eixo X
        navio.setRota(rota);
        navio.setEstado(estadoNavegando);
        simulador.getNaviosNoMar().add(navio);
    }

    @Test
    void testMovimentoNormalAvancaNaRota() {
        // Garantir que começa no ponto inicial (0,0)
        assertEquals(0.0, navio.getPosicaoAtual().getX());
        assertEquals(0, navio.getTempoNavegando());

        // Atualizar o estado (1 unidade de tempo)
        estadoNavegando.atualizar(navio, simulador);

        // O navio tem velocidade 10. Em 1 "tick" de tempo, deve avançar 10 unidades.
        assertEquals(1, navio.getTempoNavegando(), "O tempo de navegação deve ter incrementado");
        assertEquals(10.0, navio.getPosicaoAtual().getX(), 1e-9, "O navio deve ter avançado para x=10");
        assertTrue(navio.getEstado() instanceof EstadoNavegando, "O estado deve continuar a ser EstadoNavegando");
    }

    @Test
    void testChegadaAoDestinoMudaParaEstadoEspera() {
        // Criar uma rota muito curta onde a velocidade de 1 tick o fará chegar ao fim imediatamente
        Route rotaCurta = new Route(new double[]{0, 0, 10, 0});
        navio.setRota(rotaCurta);
        navio.setEstado(estadoNavegando);

        estadoNavegando.atualizar(navio, simulador);

        // A velocidade é 10. A distância é 10. Ele deve chegar ao final exato.
        assertEquals(10.0, navio.getPosicaoAtual().getX(), 1e-9);
        assertTrue(navio.getEstado() instanceof EstadoEspera, "O navio deve mudar para EstadoEspera ao chegar ao destino");
    }

    @Test
    void testPrevencaoColisaoMudaParaEstadoAguardando() {
        // Configurar um segundo navio muito próximo do N1
        // A área de interseção configurada no Navio é 50 de raio.
        Navio navioProximo = new Navio("N2", "Destino2", 5, 10.0);
        navioProximo.setPosicaoAtual(new Ponto(20, 0)); // Está a apenas 20 unidades de distância (20 <= 50)
        navioProximo.setEstado(new EstadoNavegando());
        simulador.getNaviosNoMar().add(navioProximo);

        // A lógica do teu código diz: se navio.getTempoSaida() < outro.getTempoSaida(), ele aguarda.
        // O nosso "navio" tem tempoSaida = 1. O "navioProximo" tem 5. Logo, 1 < 5 (Verdadeiro).
        estadoNavegando.atualizar(navio, simulador);

        // O navio detetou a colisão eminente e deve ter mudado de estado para Aguardando
        assertTrue(navio.getEstado() instanceof EstadoAguardando, "O navio com tempo de saída menor deve mudar para EstadoAguardando");
        assertEquals(0, navio.getTempoNavegando(), "O navio não se deve ter movido (tempoNavegando = 0)");
        assertEquals(0.0, navio.getPosicaoAtual().getX(), 1e-9, "A posição não se deve ter alterado");
    }

    @Test
    void testRecalculoDeRotaComObstaculoMovel() {
        // Para este teste, precisamos de configurar o simulador com um Porto de Destino
        Porto portoDestino = new Porto("Destino", new Ponto(100, 0));
        simulador.addPorto(portoDestino);

        // Temos que adicionar duas rotas: a principal que ficará bloqueada, e uma alternativa
        simulador.addRota(new Route(new double[]{0, 0, 100, 0}));           // Rota Direta
        simulador.addRota(new Route(new double[]{0, 0, 50, 50, 100, 0}));   // Rota Alternativa

        // Colocar um obstáculo móvel (Círculo) no meio da rota direta
        Circulo obstaculo = new Circulo(new Ponto(50, 0), 10.0);
        simulador.addObstaculoMovel(obstaculo);

        // Guardamos a rota antiga para comparar
        Route rotaAntiga = navio.getRota();

        // Simular o comportamento do navio
        estadoNavegando.atualizar(navio, simulador);

        // Verifica que a rota foi trocada!
        Route novaRota = navio.getRota();
        assertNotSame(rotaAntiga, novaRota, "O navio deve ter recalculado a rota");

        // A nova rota deve ser a rota alternativa com 3 pontos (0,0 -> 50,50 -> 100,0)
        assertEquals(3, novaRota.getPontos().length, "O navio deve estar a usar a rota de desvio com 3 pontos");
        assertEquals(50.0, novaRota.getPontos()[1].getY(), 1e-9, "O ponto intermédio da nova rota deve ser y=50");

        // Verifica se o tempo de navegação foi reiniciado
        // NOTA: Como o atualizar avança 1 "tick" no final, o tempo de navegação vai passar de 0 (reiniciado) para 1.
        assertEquals(1, navio.getTempoNavegando(), "O tempo de navegação foi reiniciado e incrementado para 1 nesta ronda");
    }
}