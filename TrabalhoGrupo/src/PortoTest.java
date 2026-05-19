import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

class PortoTest {
    private Porto porto;
    private Ponto localOriginal;
    private String nomeOriginal;

    @BeforeEach
    void setUp() {
        nomeOriginal = "A";
        localOriginal = new Ponto(10, 20);
        porto = new Porto(nomeOriginal, localOriginal);
    }

    @Test
    void testConstrutor() {
        Porto novoPorto = new Porto("B", new Ponto(0, 0));
        assertNotNull(novoPorto, "O objeto Porto deve ser criado");

        assertNotNull(novoPorto.removeNavio(999), "A lista interna deve ser iniciada (mesmo que vazia)");
    }

    @Test
    void testAddNavio() {
        Navio n1 = new Navio("NAV01", "B", 100, 10.0);
        porto.addNavio(n1);

        List<Navio> lista = porto.removeNavio(100);
        assertTrue(lista.contains(n1), "O navio deve ter sido adicionado à lista interna");
    }

    @Test
    void testRemoveNavio() {
        Navio n1 = new Navio("NAV01", "C", 500, 15.0);
        porto.addNavio(n1);

        List<Navio> removidos = porto.removeNavio(500);
        assertEquals(1, removidos.size(), "Deve remover exatamente 1 navio");
        assertEquals(n1, removidos.get(0));
    }

    @Test
    void testGetNomePorto() {
        assertEquals("A", porto.getnomePorto());
    }

    @Test
    void testGetLocalPorto() {
        assertEquals(localOriginal, porto.getlocalporto());
    }
}