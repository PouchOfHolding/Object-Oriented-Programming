package Geometry.figuras;

import Geometry.Ponto;
import Geometry.Route;

import java.util.List;

/**
 * Classe abstrata que representa uma figura geométrica
 * @author Grupo 7 PL1
 * @version 23/03/2026
 */
public abstract class Figura {

    /**
     * Calcula os pontos de interseção da figura com uma rota
     * @param r A rota a intersetar com a figura
     * @return Lista de pontos de interseção ordenados desde o início da rota
     */
    public abstract List<Ponto> intersect(Route r);
}