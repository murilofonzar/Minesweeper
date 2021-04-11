import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistoricoPanel extends JFrame {

    HistoricoJDBC abc = new HistoricoJDBC();

    private JPanel historicoPanel;
    private JPanel historicoPanel2;
    private JPanel historicoPanel3;
    private JLabel nivelLabel = new JLabel("Nível:");
    private JComboBox <String> dificuldadeCombo;
    private String[] opcoes = {"Fácil", "Médio", "Difícil"};
    private JTextArea resultado = new JTextArea(8,12);
    private JButton okButton = new JButton("OK");
    private JScrollPane resultadoPane;

    public HistoricoPanel(){
        historicoPanel = new JPanel(new GridLayout(3,1));
        historicoPanel2 = new JPanel(new GridLayout(1,1));
        historicoPanel3 = new JPanel(new GridLayout(1,1));

        JPanel primalPane = new JPanel();
        JPanel panelOk = new JPanel();
        JPanel panelNivel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JPanel panelResultado = new JPanel();

        dificuldadeCombo = new JComboBox<>(opcoes);
        resultadoPane = new JScrollPane(abc.readBD(dificuldadeCombo.getSelectedItem().toString()));

        panelNivel.add(nivelLabel);
        panelNivel.add(dificuldadeCombo);
        historicoPanel.add(panelNivel);

        panelResultado.add(resultadoPane);
        historicoPanel2.add(panelResultado);

        panelOk.add(okButton);
        historicoPanel3.add(panelOk);

        primalPane.add(historicoPanel);
        primalPane.add(historicoPanel2);
        primalPane.add(historicoPanel3);
        add(primalPane);

        dificuldadeCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelResultado.remove(resultadoPane);
                resultadoPane = new JScrollPane(abc.readBD(dificuldadeCombo.getSelectedItem().toString()));
                panelResultado.add(resultadoPane);
                revalidate();
                repaint();
            }
        });
        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

}
