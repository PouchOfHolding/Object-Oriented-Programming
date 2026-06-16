package Interface.padraoState;

import navPort.*;


/**
 * Este é o estado de quando o navio ainda não saiu do Porto de origem, assim como o do Porto de chegada
 * Ou seja quando ele literalmente não se move mais, importante não confundir com o EstadoAguardando
 * @author Grupo 7 PL1
 * @version 10/05/2026
 */
public class EstadoEspera implements EstadoNavio{

    /**
     * Como não é um "estado passivo" não executamos nenhuns calculos, pois o Navio não se move
     * @param navio O navio que está no Porto (tanto o de Origem, como o de Destino)
     * @param simulador A instância global do Simulador
     */
    @Override
    public void atualizar(Navio navio, Simulador simulador){
        //deixamos vazio, pois não precisamos de calcular colisões nem nada desse género
        //só saímos deste método quando o Simulador chama setRota, que força a mudar para o EstadoNavegando

    }
}
