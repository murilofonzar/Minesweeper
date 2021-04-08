import javax.swing.*;

public class botao extends JButton {
    private boolean EhBomba;
    private int qtdBombas = 0;
    public static ImageIcon imgBomba = new ImageIcon("img/bomba.jpg");

    botao(boolean ehBomba)
    {
        super();
        this.EhBomba = ehBomba;
    }

    public boolean getEhBomba() {
        return EhBomba;
    }

    public void setEhBomba(boolean ehBomba) {
        EhBomba = ehBomba;
    }

    public void incrementaBombas()
    {
        qtdBombas++;
    }

    public int getQtdBombas()
    {
        return qtdBombas;
    }
}
