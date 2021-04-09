import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class Informacoes extends JFrame {
    JPanel panelPrinc;
    ImageIcon mine = new ImageIcon("img/logo.jpg");

    public Informacoes() {
        panelPrinc = new JPanel(new GridLayout(3,1));

        JPanel panelTitulo = new JPanel(new GridLayout(2,1));
        JLabel titulo = new JLabel("Campo Minado");
        titulo.setFont(new Font("Verdana", Font.BOLD, 25));
        panelTitulo.add(titulo, LEFT_ALIGNMENT);

        JLabel img = new JLabel(mine);
        panelTitulo.add(img);

        panelPrinc.add(panelTitulo);

        JPanel panelTexto = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextArea texto = new JTextArea(10, 15);
        texto.setBackground(Color.white);
        texto.setEditable(false);
        Font font = new Font("Verdana", Font.PLAIN, 16);

        texto.setFont(font);

        texto.setText("A área de jogo consiste num campo de quadrados retangular. Cada\r\n" +
                "quadrado pode ser revelado clicando sobre ele, e se o quadrado clicado\r\n" +
                "contiver uma mina, então o jogo acaba. Se, por outro lado, o quadrado não\r\n" +
                "contiver uma mina, uma de duas coisas poderá acontecer:\r\n" +
                "   1. Um número aparece, indicando a qunatidade de quadrados \r\n" +
                "      adjacentes que contêm minas;\r\n" +
                "   2. Nenhum número aparece. Neste caso, o jogo revela\r\n" +
                "      automaticamente os quadrados que se encontram adjacentes ao\r\n" +
                "      quadrado vazio, já que não podem conter minas;\r\n\n" +
                "O jogo é ganho quando todos os quadrados que não têm minas são revelados.");

        panelTexto.add(texto);

        panelPrinc.add(panelTexto);

        JPanel fechar = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        JButton btFechar = new JButton("Fechar");
        fechar.add(btFechar);
        panelPrinc.add(fechar);
        add(panelPrinc);
    }
}
