
import Geometry.Ponto;
import Geometry.Vetor;
import Interface.padraoState.EstadoAguardando;
import Interface.padraoState.EstadoEspera;
import Interface.padraoState.EstadoNavegando;
import navPort.Navio;
import navPort.Simulador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EstadoAguardandoTest {

    private Simulador simulador;
    private EstadoAguardando estadoAguardando;
    private Navio navioPrincipal;
    private Ponto posicaoInicial;

    @BeforeEach
    void setUp() {

        simulador = Simulador.getInstance(new Vetor(1.0, 1.0));

        estadoAguardando = new EstadoAguardando();
        navioPrincipal = new Navio("N1", "Destino", 1, 10.0);
        posicaoInicial = new Ponto(50.0, 50.0);

        navioPrincipal.setPosicaoAtual(posicaoInicial);
        navioPrincipal.setEstado(estadoAguardando);

        simulador.getNaviosNoMar().add(navioPrincipal);
    }

    @Test
    void testContinuaAguardandoSeCaminhoBloqueado() {
        Navio navioAdversario = new Navio("N2", "Destino2", 5, 10.0);
        navioAdversario.setPosicaoAtual(new Ponto(60.0, 50.0));
        navioAdversario.setEstado(new EstadoNavegando());
        simulador.getNaviosNoMar().add(navioAdversario);

        estadoAguardando.atualizar(navioPrincipal, simulador);

        assertTrue(navioPrincipal.getEstado() instanceof EstadoAguardando);
        assertEquals(posicaoInicial.getX(), navioPrincipal.getPosicaoAtual().getX(), "Não se moveu");
    }


    @Test
    void testMudaParaNavegandoSeTiverPrioridade() {
        simulador.getNaviosNoMar().clear();

        Navio n3 = new Navio("N3", "Destino", 10, 10.0);
        n3.setPosicaoAtual(posicaoInicial);
        n3.setEstado(estadoAguardando);
        simulador.getNaviosNoMar().add(n3);

        Navio n4 = new Navio("N4", "Destino2", 2, 10.0);
        n4.setPosicaoAtual(new Ponto(60.0, 50.0));
        n4.setEstado(new EstadoNavegando());
        simulador.getNaviosNoMar().add(n4);

        estadoAguardando.atualizar(n3, simulador);

        assertTrue(n3.getEstado() instanceof EstadoNavegando, "Deve avançar pois tem maior tempo de saída");
    }

    @Test
    void testMudaParaNavegandoSeOutroNavioEstiverEmEspera() {

        Navio navioAncorado = new Navio("N2", "Destino2", 5, 10.0);
        navioAncorado.setPosicaoAtual(new Ponto(60.0, 50.0));
        navioAncorado.setEstado(new EstadoEspera());
        simulador.getNaviosNoMar().add(navioAncorado);

        estadoAguardando.atualizar(navioPrincipal, simulador);


        assertTrue(navioPrincipal.getEstado() instanceof EstadoNavegando);
    }
}