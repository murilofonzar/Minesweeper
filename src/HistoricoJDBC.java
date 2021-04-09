import java.sql.*;
import java.util.ArrayList;

public class HistoricoJDBC {
    private JDBCUtil banco;

    public HistoricoJDBC() {
        this.banco = new JDBCUtil();
    }

    public void Inserir(Historico hist)
    {
        String sql = "INSERT INTO HISTORICO(DATAPARTIDA, DURACAO, DIFICULDADE) VALUES(?,?,?)";

        try {
            PreparedStatement comando = banco.getConnection().prepareStatement(sql);

            comando.setDate(1, (Date) hist.getDataPartida());
            comando.setDate(2, (Date) hist.getDuracao());
            comando.setString(3, hist.getDificuldade());

            comando.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Consultar(String Dificuldade)
    {
        String sql = "SELECT * FROM HISTORICO WHERE DIFICULDADE = '" + Dificuldade + "'";
        ArrayList<Historico> historicos = new ArrayList<Historico>();

        try {
            Statement consulta = banco.getConnection().createStatement();
            ResultSet resultado = consulta.executeQuery(sql);

            while(resultado.next())
            {
                Historico historico = new Historico();

                historico.setDataPartida(resultado.getDate("DataPartida"));
                historico.setDuracao(resultado.getDate("Duracao"));
                historico.setDificuldade(resultado.getString("Dificuldade"));

                historicos.add(historico);
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
    }

}
