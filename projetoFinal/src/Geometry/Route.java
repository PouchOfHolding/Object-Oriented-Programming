package Geometry;

import java.util.*;

/**
 * Representa uma rota definida por uma sequência ordenada de pontos.
 * O percurso entre cada dois pontos consecutivos é retilíneo.
 * @author Grupo 7 PL1
 * @version 08/05/2026
 * @inv A rota tem pelo menos 2 pontos e dois pontos consecutivos nunca são iguais
 * @see https://youtu.be/4ET_3VUqYJQ?si=u3DpYcKHV7Fz5Amx (caminho mais curto)
 */
public class Route {

    private Ponto[] pontos;

    /**
     * Constrói uma rota a partir de um array de pontos ordenados
     * @param pontos Array de pontos da rota, do início ao fim
     * @pre pontos != null && pontos.length >= 2
     */
    public Route(Ponto[] pontos) {
        this.pontos = pontos;
    }

    /**
     * Constroi uma rota a partir de array de coordenadas
     * O array deve conter um número par de valores double representando pares de coordenadas x e y para cada ponto
     * @param coords array de coordenadas, onde cada para consecutivo define um ponto(x, y)
     */
    public Route(double[] coords) {
        if (coords.length % 2 != 0) {
            System.out.println("Rota:iv");
            System.exit(0);
        }
        this.pontos = new Ponto[coords.length / 2];
        for (int i = 0; i < coords.length; i += 2)
            pontos[i/2] = new Ponto(coords[i], coords[i+1]);
    }

    /**
     * Calcula o comprimento total da rota, somando as distâncias entre pontos consecutivos
     * @return O comprimento total da rota
     */
    public double length() {
        double total = 0;
        for (int i = 0; i < pontos.length - 1; i++) {
            double dx = pontos[i + 1].getX() - pontos[i].getX();
            double dy = pontos[i + 1].getY() - pontos[i].getY();
            total += Math.sqrt(dx * dx + dy * dy);
        }
        return total;
    }

    /**
     * Retorna os pontos da rota
     * @return Array de Geometry.Ponto com os pontos da rota
     */
    public Ponto[] getPontos() {
        return pontos;
    }

    /**
     * Verifica se um ponto já existe na lista (evita repetições)
     * @param p      O ponto a verificar
     * @param lista  A lista de pontos já encontrados
     * @return true se o ponto já existe na lista, false caso contrário
     */
    private boolean jaExiste(Ponto p, List<Ponto> lista) {
        for (Ponto existente : lista)
            if (Math.abs(existente.getX() - p.getX()) < 1e-9 && Math.abs(existente.getY() - p.getY()) < 1e-9)
                return true;
        return false;
    }

    /**
     * Calcula o(s) ponto(s) de interseção da rota com um segmento de reta, ordenados desde o início da rota
     * @param sr O segmento de reta a intersetar com a rota
     * @return Lista de pontos de interseção, ou lista vazia se não houver nenhum
     */
    public List<Ponto> intersect(SegmentoReta sr) {
        List<Ponto> intersecoes = new ArrayList<>();

        for (int i = 0; i < pontos.length - 1; i++) {
            SegmentoReta segmento = new SegmentoReta(pontos[i], pontos[i + 1]);
            Ponto intersecao = segmento.intersect(sr);
            if (intersecao != null && !jaExiste(intersecao, intersecoes))
                intersecoes.add(intersecao);
        }

        return intersecoes;
    }

    /**
     * Calcula a posição do avião no instante t
     * @param t tempo desde o início da viagem
     * @param vl velocidade linear
     * @return O Geometry.Ponto onde o avião se encontra no instante t
     */
    public Ponto position(double t, double vl) {
        double distpercorrida = t * vl;

        for (int i = 0; i < pontos.length - 1; i++) {
            double dx = pontos[i+1].getX() - pontos[i].getX();
            double dy = pontos[i+1].getY() - pontos[i].getY();
            double segLen = Math.sqrt(dx*dx + dy*dy);

            if (distpercorrida <= segLen + 1e-9) {
                // está neste segmento
                double ratio = distpercorrida / segLen;
                double px = pontos[i].getX() + ratio * dx;
                double py = pontos[i].getY() + ratio * dy;
                return new Ponto(px, py);
            }
            distpercorrida -= segLen;
        }
        // se t >= tempo total, retorna o último ponto
        return pontos[pontos.length - 1];
    }

    /**
     * Calcula os vetores velocidade para cada segmento da rota
     * @param w vetor vento
     * @param vl velocidade linear
     * @return array de Geometry.Vetor com a velocidade para cada segmento
     */
    public Vetor[] speeds(Vetor w, double vl) {
        Vetor[] result = new Vetor[pontos.length - 1];
        for (int i = 0; i < pontos.length - 1; i++) {
            AutoPilot ap = new AutoPilot(pontos[i], pontos[i+1]);
            double t = ap.time(vl);
            result[i] = ap.speed(w, t);
        }
        return result;
    }


    //PARTE DO ALGORITMO DO CAMINHO MAIS CURTO

    /**
     * Calcula a rota mais curta entre dois nós usando o Algoritmo de Dijkstra.
     *
     * @param todosPontos Array contendo todos os objetos Geometry.Ponto que representam os nós do grafo.
     * @param matrizPesos Matriz de adjacência com os custos/distâncias (0 ou negativo significa sem ligação).
     * @param noOrigem    O índice do nó de partida na matriz.
     * @param noDestino   O índice do nó de chegada na matriz.
     * @return Um novo objeto Geometry.Route representando o caminho mais curto, ou null se não houver caminho.
     */
     public static Route calcularRotaMaisCurta(Ponto[] todosPontos, int[][] matrizPesos, int noOrigem, int noDestino) {
        int numVertices = matrizPesos.length;
        int[] custo = new int[numVertices];
        int[] antecedente = new int[numVertices];
        Set<Integer> naoVisitados = new HashSet<>();

        // Inicialização
        for (int v = 0; v < numVertices; v++) {
            custo[v] = Integer.MAX_VALUE;
            antecedente[v] = -1;
            naoVisitados.add(v);
        }
        custo[noOrigem] = 0;

        while (!naoVisitados.isEmpty()) {
            // Encontra o nó não visitado com a distância mínima
            int noMaisProximo = -1;
            int minDistancia = Integer.MAX_VALUE;
            for (int i : naoVisitados) {
                if (custo[i] < minDistancia) {
                    minDistancia = custo[i];
                    noMaisProximo = i;
                }
            }

            // Se não houver mais caminhos alcançáveis, termina
            if (noMaisProximo == -1) {
                break;
            }

            naoVisitados.remove(noMaisProximo);

            // Chegámos ao destino precocemente
            if (noMaisProximo == noDestino) {
                break;
            }

            // Verifica e atualiza o custo dos vizinhos do nó selecionado
            for (int vizinho = 0; vizinho < numVertices; vizinho++) {
                int pesoDaAresta = matrizPesos[noMaisProximo][vizinho];

                // Se existe uma aresta e o vizinho ainda não foi visitado
                if (pesoDaAresta > 0 && naoVisitados.contains(vizinho)) {
                    int custoTotal = custo[noMaisProximo] + pesoDaAresta;

                    // Substitui caso o novo caminho seja mais curto
                    if (custoTotal < custo[vizinho]) {
                        custo[vizinho] = custoTotal;
                        antecedente[vizinho] = noMaisProximo;
                    }
                }
            }
        }

        // Se o destino continuar inatingível (e não for a própria origem), falhou
        if (antecedente[noDestino] == -1 && noOrigem != noDestino) {
            return null;
        }

        // Reconstrói o caminho de trás para a frente
        List<Integer> caminhoIndices = new ArrayList<>();
        int atual = noDestino;
        caminhoIndices.add(atual);

        while (antecedente[atual] != -1) {
            caminhoIndices.add(antecedente[atual]);
            atual = antecedente[atual];
        }

        Collections.reverse(caminhoIndices);

        // Mapeia os índices de volta para os objetos Geometry.Ponto e cria a Rota final
        Ponto[] pontosDoCaminho = new Ponto[caminhoIndices.size()];
        for (int i = 0; i < caminhoIndices.size(); i++) {
            pontosDoCaminho[i] = todosPontos[caminhoIndices.get(i)];
        }

        return new Route(pontosDoCaminho);
    }

    public static Route obterRotaMaisCurta(Geometry.figuras.Circulo obstaculo, Route... rotas) {
        Route melhor = null;
        for (Route r : rotas) {
            if (obstaculo.intersect(r).isEmpty()) {
                if (melhor == null || r.length() < melhor.length()) {
                    melhor = r;
                }
            }
        }
        return melhor;
    }
}

