import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javax.swing.*;

public class Minesweeper extends JFrame implements ActionListener {

    private HistoricoPanel historicoPanel = new HistoricoPanel();
    private static Informacoes telaInfo = new Informacoes();
    private static final int tamanhoCelula = 60;
    private static final int rowDificil = 16;
    private static final int columnDificil = 18;
    private static final int qtdBombasDificil = 15;
    private static final int rowMedio = 12;
    private static final int columnMedio = 14;
    private static final int qtdBombasMedio = 10;
    private static final int rowFacil = 8;
    private static final int columnFacil = 10;
    private static final int qtdBombasFacil = 10;
    private int rows = 0;
    private int columns = 0;
    private int contador = 0;
    private static Color backgroundFieldColor = new Color(179,177,177);
    private static Color openFieldColor = new Color(204,202,202);
    Historico insert = new Historico();
    HistoricoJDBC insertData = new HistoricoJDBC();
    private Date d1;
    private Date d2;

    private bSweep[][] campo = new bSweep[16][18];
    Random random = new Random();

    public Minesweeper() {

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
        JMenuItem sair = new JMenuItem("Sair");
        JMenuItem sobre = new JMenuItem("Sobre");


        fileMenu.add(novoJogo);
        fileMenu.add(historico);
        fileMenu.add(sobre);
        fileMenu.add(sair);

        novoJogo.add(nivelFacil);
        novoJogo.add(nivelMedio);
        novoJogo.add(nivelDificil);

        sobre.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                telaInfo.setTitle("Sobre");
                telaInfo.setSize(675, 600);
                telaInfo.setLocationRelativeTo(null);
                telaInfo.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                telaInfo.setVisible(true);
                telaInfo.setResizable(false);
            }
        });

        nivelFacil.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             minefieldClear();
                                             setSize((rowFacil+2) * tamanhoCelula, columnFacil * tamanhoCelula);
                                             minefieldNew(rowFacil, columnFacil, qtdBombasFacil);
                                             insertData("Fácil");
                                         }
                                     }
        );

        nivelMedio.addActionListener(new ActionListener() {
                                         public void actionPerformed(ActionEvent e) {
                                             minefieldClear();
                                             setSize((rowMedio+2) * tamanhoCelula, columnMedio * tamanhoCelula);
                                             minefieldNew(rowMedio, columnMedio, qtdBombasMedio);
                                             insertData("Médio");
                                         }
                                     }
        );

        nivelDificil.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent e) {
                                               minefieldClear();
                                               setSize((rowDificil+2) * tamanhoCelula, columnDificil * tamanhoCelula);
                                               minefieldNew(rowDificil, columnDificil, qtdBombasDificil);
                                               insertData("Difícil");
                                           }
                                       }
        );

        sair.addActionListener(new ActionListener() {
                                           public void actionPerformed(ActionEvent e) {
                                               System.exit(1);
                                           }
                                       }
        );

        historico.addActionListener(new ActionListener() {
                                   public void actionPerformed(ActionEvent e) {
                                       abrirHistorico();
                                   }
                               }
        );

    }

    public void minefieldClear() {
        getContentPane().removeAll();
        getContentPane().repaint();
    }

    public void minefieldNew(int row, int column, int qtdBombas) {
        JPanel field = new JPanel();
        field.setLayout(new GridLayout(row, column));
        this.rows = row;
        this.columns = column;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                campo[i][j] = new bSweep(false);
                campo[i][j].setBackground(backgroundFieldColor);
                campo[i][j].addActionListener(this);
                campo[i][j].setActionCommand("Campo-" + i + "-" + j);
            }
        }
        setLocationRelativeTo(null);
        setResizable(false);
        gerarBombas(row, column, qtdBombas);
        montarDicas();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                field.add(campo[i][j]);
            }
        }
        add(field);
        field.setVisible(true);
        field.updateUI();
    }

    public static void main(String[] args) {

        Minesweeper frame = new Minesweeper();
        frame.setTitle("Campo Minado");
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().contains("Campo")) {
            String[] sp = e.getActionCommand().split("-");
            int linha = Integer.parseInt(sp[1]);
            int coluna = Integer.parseInt(sp[2]);

            isBomba(linha, coluna);
        }
    }

    private void gerarBombas(int row, int column, int qtdBombas) {
        boolean sorteado;
        int linhaSort, colSort;

        for (int i = 0; i < qtdBombas + 1; i++) {
            do {
                linhaSort = random.nextInt(row);
                colSort = random.nextInt(column);

                if (campo[linhaSort][colSort].getBomb() == true)
                    sorteado = true;
                else
                    sorteado = false;
            } while (sorteado);
            campo[linhaSort][colSort].setBomb(true);
        }
    }

    private void isBomba(int row, int column) {
        if (campo[row][column].getBomb()) {
            mostrarBombas();
            JOptionPane.showMessageDialog(null, "Perdeu!");
            d2 = new Date();
            insert.setDuracao(duracao(d1,d2));
            insertData.Inserir(insert);
            minefieldClear();
        } else if (!campo[row][column].getBomb()) {
            abrirVizinhas(row, column);
        }
    }

    private void mostrarBombas() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (campo[i][j].getBomb()) {
                    campo[i][j].setIcon(bSweep.imgBomba);
                }
            }
        }
    }

    public static String duracao(Date date1, Date date2) {
        String duracaoJogo = "";
        long diferenca = date2.getTime() - date1.getTime();
        long difHora = TimeUnit.MILLISECONDS.toHours(diferenca);
        long difSegundo = TimeUnit.MILLISECONDS.toSeconds(diferenca);
        long difMinuto = TimeUnit.MILLISECONDS.toMinutes(diferenca);
        duracaoJogo = difHora +":"+difMinuto +":"+difSegundo;
        return duracaoJogo;
    }

    private void abrirVizinhas(int row, int column) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if ((row + i >= 0) && (row + i < rows) && (column + j >= 0) && (column + j < columns)) {
                    if (!campo[row + i][column + j].getBomb()) {
                        campo[row + i][column + j].setEnabled(false);
                        campo[row + i][column + j].setBackground(openFieldColor);
                        if (campo[row + i][column + j].getQtdBombas() > 0) {
                            campo[row + i][column + j].setText(Integer.toString(campo[row + i][column + j].getQtdBombas()));
                        }
                    }
                }
            }
        }
    }

    private void montarDicas() {
        for (int linha = 0; linha < rows; linha++) {
            for (int coluna = 0; coluna < columns; coluna++) {
                if (!campo[linha][coluna].getBomb()) {
                    for (int i = -1; i < 2; i++) {
                        for (int j = -1; j < 2; j++) {
                            if ((linha + i >= 0) && (linha + i < rows) && (coluna + j >= 0) && (coluna + j < columns)) {
                                if (campo[linha + i][coluna + j].getBomb()) {
                                    campo[linha][coluna].incrementaBombas();
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void abrirHistorico(){
        historicoPanel.setTitle("Histórico");
        historicoPanel.setSize(240, 450);
        historicoPanel.setLocationRelativeTo(null);
        historicoPanel.setVisible(true);
        historicoPanel.setResizable(true);
    }

    public void insertData(String dificuldade){
        d1 = new Date();
        insert.setDataPartida(d1);
        insert.setDificuldade(dificuldade);
    }

}