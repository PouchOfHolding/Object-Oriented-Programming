import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NavioTest {

    private Navio navio;
    private final String CODIGO_TESTE = "NAV01";
    private final String DESTINO_TESTE = "B"; // Ponto de A a Z
    private final int TEMPO_TESTE = 1500;
    private final double VEL_TESTE = 18.5;

    @BeforeEach
    void setUp() {

        navio = new Navio(CODIGO_TESTE, DESTINO_TESTE, TEMPO_TESTE, VEL_TESTE);
    }

    @Test
    void testGetCodigo() {
        assertEquals(CODIGO_TESTE, navio.getCodigo());
    }

    @Test
    void testGetDestino() {

        assertEquals(DESTINO_TESTE, navio.getDestino());
    }

    @Test
    void testGetTempoSaida() {
        assertEquals(TEMPO_TESTE, navio.getTempoSaida());
    }

    @Test
    void testGetPosicaoAtual() {

        assertNotNull(navio.getPosicaoAtual());
    }


    @Test
    void testIsEmEspera() {
        assertFalse(navio.isEmEspera(), "O navio deve começar fora de espera por defeito");
    }

    @Test
    void testSetEmEspera() {
        navio.setEmEspera(true);
        assertTrue(navio.isEmEspera());

        navio.setEmEspera(false);
        assertFalse(navio.isEmEspera());
    }

    @Test
    void testGetAreaIntersecao() {
        assertNotNull(navio.getAreaIntersecao());
    }

    @Test
    void testSetRota() {
        Route r = new Route();
        navio.setRota(r);

    }

    @Test
    void testAtualizarPosicao() {
        Vetor v = new Vetor(2, 0);
        double deltaT = 1.0;

        Ponto antes = navio.getPosicaoAtual();
        navio.atualizarPosicao(deltaT, v);
        Ponto depois = navio.getPosicaoAtual();

    }
}