package Interface;

import navPort.Simulador;

import java.awt.event.ActionEvent; //ActionEvent tem a haver com o Java a interpretar "cliques" do utilizador
import java.awt.event.ActionListener; //Fica á "escuta de inputs"
import javax.swing.JLabel; //serve para mostrar o texto

//A Classe Interface.Controller é o que fica á espera do input do utilizador e depois interpreta-o de acordo com o "Model" e a "Interface.View"
//"implements ActionListener" é obrigatório para poder "ouvir os botões"
public class Controller implements ActionListener {

    private Simulador simulador;
    private JLabel label; //o texto do tempo
    private PainelSimulador painel;


    public Controller(Simulador s, JLabel l, PainelSimulador p){
        this.simulador = s;
        this.label = l;
        this.painel = p;
    }


    @Override
    public void actionPerformed(ActionEvent e){
        int novoTempo = simulador.avancarTempo(); //manda o Model fazer as contas (neste caso, avançar o tempo)
        label.setText("Tempo atual: " + novoTempo);

        painel.repaint();
    }
}
