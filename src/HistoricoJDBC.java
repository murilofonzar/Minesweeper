import javax.swing.*;
import java.sql.*;
import java.util.Vector;

public class HistoricoJDBC {
    private JDBCUtil banco;

    public HistoricoJDBC() {
        this.banco = new JDBCUtil();
    }

    public void Inserir(Historico hist)
    {
        String sql = "INSERT INTO HISTORICO(DATAPARTIDA, DURACAO, DIFICULDADE) VALUES(?,?,?)";
        double diferencaSegundos = ((hist.getDataPartida().getTime() - hist.getDuracao().getTime()) / (1000)) * 60;
        try {
            PreparedStatement comando = banco.getConnection().prepareStatement(sql);

            comando.setDate(1, (Date) hist.getDataPartida());
            comando.setDouble(2, diferencaSegundos);
            comando.setString(3, hist.getDificuldade());

            comando.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable readBD(String dificuldade){
        Vector coluna = new Vector();
        Vector data = new Vector();
        String sql = "SELECT Datapartida AS Data, Duracao as Duracao FROM HISTORICO where dificuldade like '%"+dificuldade+"%'";

        try {
            Statement comando = banco.getConnection().createStatement();
            ResultSet rs = comando.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int colunas = md.getColumnCount();
            for (int i = 1;i<=colunas;i++){
                coluna.addElement(md.getColumnName(i));
            }
            while(rs.next()){
                Vector row = new Vector(colunas);
                for (int i = 1;i<=colunas;i++){
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }
            JTable table = new JTable(data,coluna){
                public Class getColumnClass(int coluna){
                    for(int row = 0;row<getRowCount();row++){
                        Object o = getValueAt(row,coluna);
                        if(o!=null){
                            return o.getClass();
                        }
                    }
                    return Object.class;
                }
            };
            return table;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void dbTable() {

    }

}
