import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Arrays;
import javax.swing.*;

public class minefield extends JFrame {

    private static final int tamanhoCelula = 15;
    private static final int rowDificil = 16;
    private static final int columnDificil = 18;
    private static final int rowMedio = 12;
    private static final int columnMedio = 14;
    private static final int rowFacil = 8;
    private static final int columnFacil = 10;
    private JButton[] campo = new JButton[16 * 18];

    public minefield() {

        // Menu
        JMenu fileMenu = new JMenu("Inicio");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuBar.add(fileMenu);
        JMenu novoJogo = new JMenu("Novo Jogo");
        JMenuItem nivelFacil = new JMenuItem("Fácil");
        JMenuItem nivelMedio = new JMenuItem("Médio");
        JMenuItem nivelDificil = new JMenuItem("Difícil");
        JMenuItem historico = new JMenuItem("Histórico");
        JMenuItem sobre = new JMenuItem("Sobre");

        fileMenu.add(novoJogo);
        fileMenu.add(historico);
        fileMenu.add(sobre);

        novoJogo.add(nivelFacil);
        novoJogo.add(nivelMedio);
        novoJogo.add(nivelDificil);

        nivelFacil.addActionListener(new ActionListener(){
                                         public void actionPerformed(ActionEvent e) {
                                             minefieldClear();
                                             minefieldNew(rowFacil,columnFacil);
                                         }
                                     }
        );

        nivelMedio.addActionListener(new ActionListener(){
                                         public void actionPerformed(ActionEvent e) {
                                             minefieldClear();
                                             minefieldNew(rowMedio,columnMedio);
                                         }
                                     }
        );

        nivelDificil.addActionListener(new ActionListener(){
                                           public void actionPerformed(ActionEvent e) {
                                               minefieldClear();
                                               minefieldNew(rowDificil,columnDificil);
                                           }
                                       }
        );

        /**
         * @param args the command line arguments
         */
    }

    public void minefieldClear(){
        new minefield();
    }

    public void minefieldNew(int row, int column){
        JPanel field = new JPanel();
        field.setLayout(new GridLayout(row, column));
        field.setSize(column*tamanhoCelula+1, row*tamanhoCelula+1);
        field.setPreferredSize(new Dimension(column*tamanhoCelula+1, row*tamanhoCelula+1));
        for(int i = 0; i < row*column; i++) {
            campo[i] = new JButton();
            campo[i].setSize(15,15);
            campo[i].setBackground(Color.lightGray);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                field.add(campo[column * i + j]);
            }
        }
        add(field);
        field.setVisible(true);
        field.updateUI();
    }

    public static void main (String[]args){

        minefield frame = new minefield();
        frame.setTitle("Campo Minado");
        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}