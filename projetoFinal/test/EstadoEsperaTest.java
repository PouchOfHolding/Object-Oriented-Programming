
import Geometry.Ponto;
import Geometry.Vetor;
import Interface.padraoState.EstadoEspera;
import navPort.Navio;
import navPort.Simulador;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class EstadoEsperaTest {

    private Simulador simulador;
    private EstadoEspera estadoEspera;
    private Navio navio;
    private Ponto posicaoInicial;

    @BeforeEach
    void setUp() throws Exception {
        // Reset ao Singleton do Simulador (Boa prática mantida dos testes anteriores)
        Field instanciaUnicaField = Simulador.class.getDeclaredField("instanciaUnica");
        instanciaUnicaField.setAccessible(true);
        instanciaUnicaField.set(null, null);

        simulador = Simulador.getInstance(new Vetor(1.0, 1.0));
        estadoEspera = new EstadoEspera();

        // Configuração de um navio no porto (Estado Espera)
        navio = new Navio("N1", "Destino", 1, 10.0);
        posicaoInicial = new Ponto(10.0, 20.0);

        // Forçamos valores iniciais para garantir que não são mexidos
        navio.setPosicaoAtual(posicaoInicial);
        navio.setEstado(estadoEspera);
        navio.setTempoNavegando(5);
    }

    @Test
    void testAtualizarNaoAlteraAtributosDoNavio() {
        // Ação: Invocamos o atualizar() do EstadoEspera
        estadoEspera.atualizar(navio, simulador);

        // Verificações:

        // 1. O estado interno do Navio deve continuar a ser EstadoEspera
        assertTrue(navio.getEstado() instanceof EstadoEspera, "O estado não deve sofrer transições espontâneas durante a espera");

        // 2. O tempo de navegação não deve ter sido incrementado (como aconteceria no EstadoNavegando)
        assertEquals(5, navio.getTempoNavegando(), "O tempo de navegação tem de permanecer intacto no EstadoEspera");

        // 3. A posição do navio tem de ser exatamente a mesma de quando estava ancorado
        assertEquals(posicaoInicial, navio.getPosicaoAtual(), "A instância do Ponto de posição não deve mudar");
        assertEquals(10.0, navio.getPosicaoAtual().getX(), 1e-9, "A coordenada X não deve sofrer alterações");
        assertEquals(20.0, navio.getPosicaoAtual().getY(), 1e-9, "A coordenada Y não deve sofrer alterações");
    }
}