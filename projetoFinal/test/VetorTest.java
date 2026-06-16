import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import Geometry.*;

public class VetorTest {

    @Test
    void constroiVetorComCoordenadasValidas() {
        Vetor v = new Vetor(3.0, 4.0);
        assertEquals(3.0, v.getX());
        assertEquals(4.0, v.getY());
    }

    @Test
    void constroiVetorNuloGeraExcecao() {

        assertThrows(IllegalArgumentException.class, () -> new Vetor(0.0, 0.0));
    }

    @Test
    void constroiVetorAPartirDePonto() {
        Ponto p = new Ponto(5.0, 12.0);
        Vetor v = new Vetor(p);
        assertEquals(5.0, v.getX());
        assertEquals(12.0, v.getY());
    }



    @Test
    void calculaDistanciaDoVetor() {
        Vetor v = new Vetor(3.0, 4.0);

        assertEquals(5.0, v.dist(), 1e-9);
    }

    @Test
    void calculaProdutoInterno() {
        Vetor v1 = new Vetor(1.0, 2.0);
        Vetor v2 = new Vetor(3.0, 4.0);

        assertEquals(11.0, v1.prodint(v2), 1e-9);
    }

    @Test
    void calculaCossenoSimilaridade() {
        Vetor v1 = new Vetor(1.0, 0.0);
        Vetor v2 = new Vetor(0.0, 1.0);

        assertEquals(0.0, v1.cossimi(v2), 1e-9);
        Vetor v3 = new Vetor(2.0, 0.0);
        assertEquals(1.0, v1.cossimi(v3), 1e-9);
    }

    @Test
    void multiplicaVetorPorEscalar() {
        Vetor v = new Vetor(2.0, -3.0);
        Vetor resultado = v.mult(2.0);

        assertEquals(4.0, resultado.getX());
        assertEquals(-6.0, resultado.getY());
    }

    @Test
    void vetorAdicaoComOutroVetor() {
        Vetor vetor1 = new Vetor(2.0, 3.0);
        Vetor vetor2 = new Vetor(1.0, -1.0);
        Vetor resultado = vetor1.add(vetor2);

        assertEquals(new Vetor(3.0, 2.0).toString(), resultado.toString());
    }

}