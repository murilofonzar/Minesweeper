import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.*;

public class minefield extends JFrame {

    private static final int rowDificil = 16;
    private static final int columnDificil = 18;
    private static final int rowMedio = 12;
    private static final int columnMedio = 14;
    private static final int rowFacil = 8;
    private static final int columnFacil = 10;
    private JButton[] campo = new JButton[16 * 18];
    private JPanel p1 = new JPanel();

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

        p1.setEnabled(true);


        nivelFacil.addActionListener(new ActionListener(){
                                         public void actionPerformed(ActionEvent e) {
                                             minefieldClear();
                                             p1.setLayout(new GridLayout(rowFacil, columnFacil));
                                             p1.setSize(800, 600);
                                             for(int i = 0; i < rowFacil*columnFacil; i++) {
                                                 campo[i] = new JButton();
                                                 campo[i].setBackground(Color.lightGray);
                                             }
                                             for (int i = 0; i < rowFacil; i++) {
                                                 for (int j = 0; j < columnFacil; j++) {
                                                     p1.add(campo[columnFacil * i + j]);
                                                 }
                                             }
                                             p1.setVisible(true);
                                             p1.updateUI();
                                         }
                                     }
        );

        nivelMedio.addActionListener(new ActionListener(){
                                         public void actionPerformed(ActionEvent e) {

                                             p1.setLayout(new GridLayout(rowMedio, columnMedio));
                                             p1.setSize(800, 600);
                                             for(int i = 0; i < rowMedio*columnMedio; i++) {
                                                 campo[i] = new JButton();
                                                 campo[i].setBackground(Color.lightGray);
                                             }
                                             for (int i = 0; i < rowMedio; i++) {
                                                 for (int j = 0; j < columnMedio; j++) {
                                                     p1.add(campo[columnMedio * i + j]);
                                                 }
                                             }
                                             p1.setVisible(true);
                                             p1.updateUI();
                                         }
                                     }
        );

        nivelDificil.addActionListener(new ActionListener(){
                                           public void actionPerformed(ActionEvent e) {
                                               p1.setLayout(new GridLayout(rowDificil, columnDificil));
                                               p1.setSize(800, 600);
                                               for(int i = 0; i < rowDificil*columnDificil; i++) {
                                                   campo[i] = new JButton();
                                                   campo[i].setBackground(Color.lightGray);
                                               }
                                               for (int i = 0; i < rowDificil; i++) {
                                                   for (int j = 0; j < columnDificil; j++) {
                                                       p1.add(campo[columnDificil * i + j]);
                                                   }
                                               }
                                               p1.setVisible(true);
                                               p1.updateUI();
                                           }
                                       }
        );

        add(p1);

        /**
         * @param args the command line arguments
         */
    }

    public void minefieldClear(){
        for (int i=0; i<campo.length; i++){
            Arrays.fill(campo,null);
        }
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