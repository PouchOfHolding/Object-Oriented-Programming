import Geometry.Ponto;
import navPort.Navio;
import navPort.Porto;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PortoTest {

    @Test
    void deveInicializarPortoComNomeELocalizacao() {
        String nome = "Porto A";
        Ponto localizacao = new Ponto(10.0, 20.0);

        Porto porto = new Porto(nome, localizacao);

        assertEquals(nome, porto.getNomePorto());
        assertEquals(localizacao, porto.getLocalPorto());
    }

    @Test
    void deveInicializarComListaDeNaviosVazia() {
        Porto porto = new Porto("Porto B", new Ponto(5.0, 15.0));

        List<Navio> navios = porto.getNaviosEmEspera();

        assertNotNull(navios);
        assertTrue(navios.isEmpty());
    }

    @Test
    void deveAdicionarNavioALista() {
        Porto porto = new Porto("Porto C", new Ponto(0.0, 0.0));
        Navio navio = new Navio("N001", "Porto D", 10, 20.0);

        porto.addNavio(navio);

        assertEquals(1, porto.getNaviosEmEspera().size());
        assertTrue(porto.getNaviosEmEspera().contains(navio));
    }

    @Test
    void deveAdicionarMultiplosNavios() {
        Porto porto = new Porto("Porto E", new Ponto(100.0, 150.0));
        Navio navio1 = new Navio("N001", "Porto F", 5, 15.0);
        Navio navio2 = new Navio("N002", "Porto G", 10, 18.0);
        Navio navio3 = new Navio("N003", "Porto H", 15, 20.0);

        porto.addNavio(navio1);
        porto.addNavio(navio2);
        porto.addNavio(navio3);

        assertEquals(3, porto.getNaviosEmEspera().size());
        assertTrue(porto.getNaviosEmEspera().contains(navio1));
        assertTrue(porto.getNaviosEmEspera().contains(navio2));
        assertTrue(porto.getNaviosEmEspera().contains(navio3));
    }

    @Test
    void deveRemoverNavioComTempoSaidaIgualAoTempoAtual() {
        Porto porto = new Porto("Porto I", new Ponto(50.0, 75.0));
        Navio navio1 = new Navio("N001", "Porto J", 5, 15.0);
        Navio navio2 = new Navio("N002", "Porto K", 10, 18.0);
        porto.addNavio(navio1);
        porto.addNavio(navio2);

        List<Navio> naviosPartindo = porto.removeNavio(5);

        assertEquals(1, naviosPartindo.size());
        assertTrue(naviosPartindo.contains(navio1));
        assertEquals(1, porto.getNaviosEmEspera().size());
        assertTrue(porto.getNaviosEmEspera().contains(navio2));
    }

    @Test
    void deveRemoverMultiplosNaviosComMesmoTempoSaida() {
        Porto porto = new Porto("Porto L", new Ponto(25.0, 35.0));
        Navio navio1 = new Navio("N001", "Porto M", 8, 15.0);
        Navio navio2 = new Navio("N002", "Porto N", 8, 18.0);
        Navio navio3 = new Navio("N003", "Porto O", 12, 20.0);
        porto.addNavio(navio1);
        porto.addNavio(navio2);
        porto.addNavio(navio3);

        List<Navio> naviosPartindo = porto.removeNavio(8);

        assertEquals(2, naviosPartindo.size());
        assertTrue(naviosPartindo.contains(navio1));
        assertTrue(naviosPartindo.contains(navio2));
        assertEquals(1, porto.getNaviosEmEspera().size());
        assertTrue(porto.getNaviosEmEspera().contains(navio3));
    }

    @Test
    void deveRetornarListaVaziaQuandoNaoHaNavio() {
        Porto porto = new Porto("Porto P", new Ponto(5.0, 10.0));

        List<Navio> naviosPartindo = porto.removeNavio(5);

        assertTrue(naviosPartindo.isEmpty());
    }

    @Test
    void deveRetornarListaVaziaQuandoTempoNaoCoincide() {
        Porto porto = new Porto("Porto Q", new Ponto(60.0, 80.0));
        Navio navio = new Navio("N001", "Porto R", 10, 15.0);
        porto.addNavio(navio);

        List<Navio> naviosPartindo = porto.removeNavio(5);

        assertTrue(naviosPartindo.isEmpty());
        assertEquals(1, porto.getNaviosEmEspera().size());
        assertTrue(porto.getNaviosEmEspera().contains(navio));
    }

    @Test
    void deveNaoRemoverNavioComTempoSaidaDiferente() {
        Porto porto = new Porto("Porto S", new Ponto(15.0, 25.0));
        Navio navio1 = new Navio("N001", "Porto T", 5, 15.0);
        Navio navio2 = new Navio("N002", "Porto U", 10, 18.0);
        Navio navio3 = new Navio("N003", "Porto V", 15, 20.0);
        porto.addNavio(navio1);
        porto.addNavio(navio2);
        porto.addNavio(navio3);

        List<Navio> naviosPartindo = porto.removeNavio(7);

        assertTrue(naviosPartindo.isEmpty());
        assertEquals(3, porto.getNaviosEmEspera().size());
    }

    @Test
    void deveRemoverNaviosApenasComTempoExato() {
        Porto porto = new Porto("Porto W", new Ponto(20.0, 30.0));
        Navio navio1 = new Navio("N001", "Porto X", 10, 15.0);
        Navio navio2 = new Navio("N002", "Porto Y", 10, 18.0);
        Navio navio3 = new Navio("N003", "Porto Z", 20, 20.0);
        porto.addNavio(navio1);
        porto.addNavio(navio2);
        porto.addNavio(navio3);

        List<Navio> naviosPartindo = porto.removeNavio(10);

        assertEquals(2, naviosPartindo.size());
        assertTrue(naviosPartindo.contains(navio1));
        assertTrue(naviosPartindo.contains(navio2));
        assertFalse(naviosPartindo.contains(navio3));
    }

    @Test
    void deveRetornarNomePortoCorreto() {
        String nome = "Porto Principal";
        Porto porto = new Porto(nome, new Ponto(0.0, 0.0));

        assertEquals(nome, porto.getNomePorto());
    }

    @Test
    void deveRetornarLocalizacaoPortoCorreta() {
        Ponto localizacao = new Ponto(100.0, 200.0);
        Porto porto = new Porto("Porto", localizacao);

        Ponto localizacaoObtida = porto.getLocalPorto();

        assertEquals(localizacao.getX(), localizacaoObtida.getX());
        assertEquals(localizacao.getY(), localizacaoObtida.getY());
    }

    @Test
    void deveRetornarListaVaziaDeNaviosEmEsperaQuandoNenhumAdicionado() {
        Porto porto = new Porto("Porto Vazio", new Ponto(0.0, 0.0));

        List<Navio> navios = porto.getNaviosEmEspera();

        assertNotNull(navios);
        assertTrue(navios.isEmpty());
    }

    @Test
    void deveRetornarTodosOsNaviosEmEspera() {
        Porto porto = new Porto("Porto Completo", new Ponto(50.0, 50.0));
        Navio navio1 = new Navio("N001", "Porto A", 5, 15.0);
        Navio navio2 = new Navio("N002", "Porto B", 10, 18.0);
        Navio navio3 = new Navio("N003", "Porto C", 15, 20.0);
        porto.addNavio(navio1);
        porto.addNavio(navio2);
        porto.addNavio(navio3);

        List<Navio> naviosEmEspera = porto.getNaviosEmEspera();

        assertEquals(3, naviosEmEspera.size());
        assertTrue(naviosEmEspera.contains(navio1));
        assertTrue(naviosEmEspera.contains(navio2));
        assertTrue(naviosEmEspera.contains(navio3));
    }

    @Test
    void deveManterReferenciaDoMesmoNavioAposAdicionar() {
        Porto porto = new Porto("Porto", new Ponto(0.0, 0.0));
        Navio navio = new Navio("N001", "Destino", 5, 15.0);

        porto.addNavio(navio);
        List<Navio> navios = porto.getNaviosEmEspera();

        assertSame(navio, navios.get(0));
    }

}