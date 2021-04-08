import javax.print.attribute.DateTimeSyntax;
import java.util.Date;

public class Historico
{
    private Date DataPartida;
    private Date Duracao;
    private String Dificuldade;

    public Date getDataPartida() {
        return DataPartida;
    }

    public void setDataPartida(Date dataPartida) {
        DataPartida = dataPartida;
    }

    public Date getDuracao() {
        return Duracao;
    }

    public void setDuracao(Date duracao) {
        Duracao = duracao;
    }

    public String getDificuldade() {
        return Dificuldade;
    }

    public void setDificuldade(String dificuldade) {
        Dificuldade = dificuldade;
    }
}
