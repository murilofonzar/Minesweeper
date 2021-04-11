import javax.swing.*;

public class bSweep extends JButton {
    private boolean isBomb;
    private int qtdBombas;
    public static ImageIcon imgBomba = new ImageIcon("img/bomba.png");

    bSweep(boolean isBomb) {
        super();
        this.isBomb = isBomb;
        qtdBombas = 0;
    }

    public boolean getBomb() {
        return isBomb;
    }

    public void setBomb(boolean bomb) {
        isBomb = bomb;
    }

    public void incrementaBombas() {
        qtdBombas++;
    }

    public int getQtdBombas() {
        return qtdBombas;
    }

}