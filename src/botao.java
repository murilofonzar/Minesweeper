import javax.swing.*;

public class botao extends JButton {
    private boolean EhBomba;
    private ImageIcon bomba = new ImageIcon("img/bomba.png");

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
}
