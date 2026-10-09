package Interface.padraoState;

import navPort.*;

import java.util.List;

/**
 * Interface para o PADRÃO STATE. Define os comportamentos possíveis de um Navio
 */
public interface EstadoNavio {

    /**
     * Atualiza a lógica do navio dependendo do seu estado atual
     * @param navio O navio que está a executar a ação
     */
    void atualizar(Navio navio, List<Navio> frota);
}
