package Interface;

import Geometry.*;
import Geometry.figuras.Circulo;
import Geometry.figuras.Retangulo;
import navPort.Porto;
import navPort.Navio;
import navPort.Simulador;

import javax.swing.*;
import javax.swing.Timer; //como o próprio nome diz é um Timer

//A classe Interface.View é o que nós vamos ver (user interface)
public class View {

    private JFrame frame;  //isto vai ser a "janela" c nome de frame
    private PainelSimulador panel; //JPanel faz parte do Swing que já tem incluido botões, labels e textos para usarmos
    private Simulador simulador; //adicionamos o Modelo

    public View(){

        frame = new JFrame("Simualdor de Navegação"); //criamos a janela que fica com o nome "navPort.Simulador de Navegação"

        Vetor corrente = new Vetor(1,2);

        simulador = Simulador.getInstance(corrente); //Usamos o método do Singleton


        //MOSTRAR OS PORTOS

            //PORTO A
        Ponto localPortoA = new Ponto(100, 100); // da mos as coordenadas do navio_porto.Porto
        Porto portoA = new Porto("A", localPortoA); //damos o nome do navio_porto.Porto, com as coordenadas


                //ADICIONAR OS NAVIOS NO PORTO A
        Navio navio1 = new Navio("A2", "C", 2, 5.0);

        portoA.addNavio(navio1);
        simulador.addPorto(portoA); //adicionamos esse navio_porto.Porto

            //PORTO B
        Ponto localPortoB = new Ponto(700,100);
        Porto portoB = new Porto("B", localPortoB);
        simulador.addPorto(portoB);


            //PORTO C
        Ponto localPortoC = new Ponto(100,500);
        Porto portoC = new Porto("C", localPortoC);

                //ADICIONAR OS NAVIOS NO PORTO C
        Navio navio2 = new Navio("C2", "B", 2, 5.0);
        portoC.addNavio(navio2);
        simulador.addPorto(portoC);



            //PORTO D
        Ponto localPortoD = new Ponto(700,500);
        Porto portoD = new Porto("D", localPortoD);
        simulador.addPorto(portoD);


        //MOSTRAR AS ROTAS

        //Rota 1 (vermelha): navio_porto.Porto A ao navio_porto.Porto D
        double[] coordsR1 = {100, 100, 400, 200, 400, 400, 700, 500};
        simulador.addRota(new Route(coordsR1));

        //Rota 2 (verde): navio_porto.Porto C ao B
        double[] coordsR2 = {100, 500, 300, 300, 500, 300, 700, 100};
        simulador.addRota(new Route(coordsR2));

        //Rota 3 (azul): navio_porto.Porto A ao B
        double[] coordsR3 = {100, 100, 300, 300, 400, 200, 700, 100};
        simulador.addRota(new Route(coordsR3));



        //OBSTÁCULOS

        //Geometry.Figuras.Circulo ("Obstáculo móvel")
        Ponto centro = new Ponto(500,200);
        Circulo movel = new Circulo(centro, 40);
        simulador.addObstaculo(movel);

        //Geometry.Figuras.Retangulo ("Obstáculo fixo")
        Ponto[] pontosRetangulo = {
                new Ponto(200, 400), new Ponto(300,400),
                new Ponto(300, 450), new Ponto(200,450)
        };

        Retangulo obstaculo1 = new Retangulo(pontosRetangulo);
        simulador.addObstaculo(obstaculo1);



        panel = new PainelSimulador(simulador); //criamos o Painel

        JLabel label = new JLabel("Tempo atual: 0");
        JButton button = new JButton("Avançar relógio");

        Controller controlador = new Controller(simulador, label,panel);

        Timer relogioAutomatico = new Timer(1000, controlador);
        button.addActionListener(e -> relogioAutomatico.start());

        panel.add(label);
        panel.add(button);

        frame.add(panel); //adicionamos o panel

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //serve para quando fecharmos a janela, que o programa termine por completo

        frame.setSize(900,700); //tamanho da janela "(largura , altura)"

        frame.setVisible(true); //Serve para a janela ficar visivel


    }

    static void main() { //main temporário para experimentar
        new View();
    }
}
