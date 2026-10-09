package Interface.padraoState;

import navPort.*;
import Geometry.*;

import java.util.List;

/**
 * Este é o estado de quando o navio ainda não saiu do Porto de origem, assim como o do Porto de chegada
 * Ou seja quando ele literalmente não se move mais, importante não confundir com o EstadoAguardando
 */
public class EstadoEspera implements EstadoNavio{

    /**
     * Como não é um "estado passivo" não executamos nenhuns calculos, pois o Navio não se move
     * @param navio O navio que está em repouso
     * @param frota A lista de Navios no "mar"
     */
    @Override
    public void atualizar(Navio navio, List<Navio> frota){
        //deixamos vazio, pois não precisamos de calcular colisões nem nada desse género
        //só saímos deste método quando o Simulador chama setRota, que força a mudar para o EstadoNavegando

    }
}
