import java.util.Date;

public class Historico
{
    private Date DataPartida;
    private String Duracao;
    private String Dificuldade;

    public Date getDataPartida() {
        return DataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        DataPartida = dataPartida;
    }

    public String getDuracao() {
        return Duracao;
    }

    public void setDuracao(String duracao) {
        Duracao = duracao;
    }

    public String getDificuldade() {
        return Dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        Dificuldade = dificuldade;
    }
}
