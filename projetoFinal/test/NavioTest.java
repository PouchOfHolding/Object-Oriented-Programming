import Geometry.Ponto;
import Geometry.Route;
import Geometry.Vetor;
import Geometry.figuras.Circulo;
import Interface.padraoState.EstadoAguardando;
import Interface.padraoState.EstadoEspera;
import Interface.padraoState.EstadoNavegando;
import Interface.padraoState.EstadoNavio;
import navPort.Navio;
import navPort.Simulador;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NavioTest {

    @Test
    void deveInicializarNavioComValoresCorretos() {
        String cod = "NAV123";
        String dest = "Porto C";
        int tempoSaida = 10;
        double velocidade = 15.0;

        Navio navio = new Navio(cod, dest, tempoSaida, velocidade);

        assertEquals(cod, navio.getCodigo());
        assertEquals(dest, navio.getDestino());
        assertEquals(tempoSaida, navio.getTempoSaida());
        assertEquals(velocidade, navio.getVelocidadeLinearPretendida());
        assertNull(navio.getRota());
        assertNull(navio.getPosicaoAtual());
        assertEquals(0, navio.getTempoNavegando());
        assertTrue(navio.getEstado() instanceof EstadoEspera);
    }

    // teste para o atualizar
    @Test
    void deveDelegarAtualizacaoParaOEstado() {
        Vetor corrente = new Vetor(1.0, 1.0);
        Simulador simulador = Simulador.getInstance(corrente);

        Navio navio = new Navio("A1", "C", 0, 5.0);
        double[] coords = {0, 0, 100, 0};
        Route rota = new Route(coords);

        navio.setRota(rota);
        int tempoAntes = navio.getTempoNavegando();

        navio.atualizar(simulador);

        assertEquals(tempoAntes + 1, navio.getTempoNavegando());
        assertNotNull(navio.getPosicaoAtual());
    }

    //teste para o Setestado
    @Test
    void deveAlterarOEstadoDoNavio() {
        Navio navio = new Navio("A1", "B", 0, 10.0);

        EstadoNavio novoEstado = new EstadoNavegando();
        navio.setEstado(novoEstado);

        assertTrue(navio.getEstado() instanceof EstadoNavegando);

        navio.setEstado(new EstadoAguardando());
        assertTrue(navio.getEstado() instanceof EstadoAguardando);
    }
    // teste para getestado
    @Test
    void deveRetornarEstadoAtual() {
        Navio navio = new Navio("TESTE", "Destino", 0, 10.0);

        EstadoNavio estadoInicial = navio.getEstado();
        assertTrue(estadoInicial instanceof EstadoEspera);

        EstadoNavio novoEstado = new EstadoNavegando();
        navio.setEstado(novoEstado);

        EstadoNavio estadoObtido = navio.getEstado();
        assertEquals(novoEstado, estadoObtido);
        assertTrue(estadoObtido instanceof EstadoNavegando);
    }

    // teste para gettempo navegando
    @Test
    void deveRetornarTempoNavegando() {
        Navio navio = new Navio("TESTE01", "Porto A", 0, 10.0);

        // O tempo deve começar em 0
        assertEquals(0, navio.getTempoNavegando());

        // Simula o avanço do tempo
        navio.incrementarTempoNavegando();
        navio.incrementarTempoNavegando();

        assertEquals(2, navio.getTempoNavegando());
    }

    // incrementar tempo navegando
    @Test
    void deveIncrementarTempoNavegando() {
        Navio navio = new Navio("A1", "Porto B", 0, 10.0);

        assertEquals(0, navio.getTempoNavegando());

        navio.incrementarTempoNavegando();
        assertEquals(1, navio.getTempoNavegando());

        navio.incrementarTempoNavegando();
        assertEquals(2, navio.getTempoNavegando());
    }
    @Test
    void deveBuscarCodigoDoNavioCorreto() {
        String cod = "SHIP001";
        Navio navio = new Navio(cod, "Porto B", 5, 20.0);

        assertEquals(cod, navio.getCodigo());
    }

    @Test
    void deveBuscarDestinoDoNavioCorreto() {
        String destino = "Porto Rio";
        Navio navio = new Navio("S001", destino, 0, 15.0);

        assertEquals(destino, navio.getDestino());
    }

    @Test
    void deveBuscarTempoSaidaCorreto() {
        int tempoSaida = 25;
        Navio navio = new Navio("S001", "Porto A", tempoSaida, 12.0);

        assertEquals(tempoSaida, navio.getTempoSaida());
    }

    @Test
    void deveBuscarVelocidadeLinearPretendidaCorreta() {
        double velocidade = 25.5;
        Navio navio = new Navio("S001", "Porto B", 0, velocidade);

        assertEquals(velocidade, navio.getVelocidadeLinearPretendida());
    }

    @Test
    void deveMudarTempoNavegandoParaValorEspecifico() {
        Navio navio = new Navio("S001", "Porto C", 0, 10.0);

        navio.setTempoNavegando(50);

        assertEquals(50, navio.getTempoNavegando());
    }

    @Test
    void deveMudarTempoNavegandoParaZero() {
        Navio navio = new Navio("S001", "Porto C", 0, 10.0);
        navio.incrementarTempoNavegando();
        navio.incrementarTempoNavegando();

        navio.setTempoNavegando(0);

        assertEquals(0, navio.getTempoNavegando());
    }

    @Test
    void deveMudarPosicaoAtualDoNavio() {
        Navio navio = new Navio("S001", "Porto D", 0, 15.0);
        Ponto novaPosicao = new Ponto(50.0, 75.0);

        navio.setPosicaoAtual(novaPosicao);

        assertEquals(novaPosicao, navio.getPosicaoAtual());
    }

    @Test
    void deveRetornarPosicaoAtualQuandoNaoDefinida() {
        Navio navio = new Navio("S001", "Porto E", 0, 15.0);

        assertNull(navio.getPosicaoAtual());
    }

    @Test
    void deveRetornarPosicaoAtualAposSetarNovaPosicao() {
        Navio navio = new Navio("S001", "Porto F", 0, 15.0);
        Ponto posicao1 = new Ponto(10.0, 20.0);
        Ponto posicao2 = new Ponto(30.0, 40.0);

        navio.setPosicaoAtual(posicao1);
        navio.setPosicaoAtual(posicao2);

        assertEquals(posicao2, navio.getPosicaoAtual());
    }

    @Test
    void deveDefinirRotaEAlterarEstadoParaNavegando() {
        Navio navio = new Navio("S001", "Porto G", 0, 15.0);
        double[] coords = {0, 0, 100, 100};
        Route rota = new Route(coords);

        navio.setRota(rota);

        assertEquals(rota, navio.getRota());
        assertTrue(navio.getEstado() instanceof EstadoNavegando);
    }

    @Test
    void deveDefinirPosicaoAtualComoInicioDaRotaAoSetarRota() {
        Navio navio = new Navio("S001", "Porto H", 0, 15.0);
        double[] coords = {25.0, 35.0, 100, 100};
        Route rota = new Route(coords);

        navio.setRota(rota);

        Ponto posicaoEsperada = rota.getPontos()[0];
        assertEquals(posicaoEsperada.getX(), navio.getPosicaoAtual().getX());
        assertEquals(posicaoEsperada.getY(), navio.getPosicaoAtual().getY());
    }

    @Test
    void deveAceitarRotaNulaComSemAlterarEstado() {
        Navio navio = new Navio("S001", "Porto I", 0, 15.0);

        navio.setRota(null);

        assertNull(navio.getRota());
        assertTrue(navio.getEstado() instanceof EstadoEspera);
    }

    @Test
    void deveRetornarRotaDefinida() {
        Navio navio = new Navio("S001", "Porto J", 0, 15.0);
        double[] coords = {0, 0, 50, 50};
        Route rota = new Route(coords);

        navio.setRota(rota);

        assertEquals(rota, navio.getRota());
    }

    @Test
    void deveRetornarRotaNulaAteSerDefinida() {
        Navio navio = new Navio("S001", "Porto K", 0, 15.0);

        assertNull(navio.getRota());
    }

    @Test
    void deveCriarAreaIntersecaoCentradaEmPosicaoAtual() {
        Navio navio = new Navio("S001", "Porto N", 0, 15.0);
        Ponto posicao = new Ponto(50.0, 75.0);
        navio.setPosicaoAtual(posicao);

        Circulo area = navio.getAreaIntersecao();

        assertEquals(posicao.getX(), area.getCentro().getX());
        assertEquals(posicao.getY(), area.getCentro().getY());
    }

}