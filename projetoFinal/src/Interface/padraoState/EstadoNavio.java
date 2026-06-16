package Interface.padraoState;

import navPort.*;


/**
 * Interface para o PADRÃO STATE. Define os comportamentos possíveis de um Navio
 * @author Grupo 7 PL1
 * @version 10/05/2026
 */
public interface EstadoNavio {

    /**
     * Executa a lógica correspondente ao estado atual do Navio num determinado isntante de tempo
     * @param navio A instância do Navio que vai ser sujeito a um Estado
     * @param simulador A instância global do Simulador, é necessária para obter o contexto do que está a acontecer
     */
    void atualizar(Navio navio, Simulador simulador); //agora os estados precisam de conhecer o Simulador como todo para os obstáculos móveis
}
