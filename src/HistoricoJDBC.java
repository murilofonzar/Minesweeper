import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

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

            comando.setObject(1, hist.getDataPartida());
            comando.setString(2,hist.getDuracao());
            comando.setString(3, hist.getDificuldade());

            comando.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public JTable readBD(String dificuldade){
        Vector coluna = new Vector();
        Vector data = new Vector();
        String sql = "SELECT Datapartida, Duracao FROM HISTORICO where dificuldade like '%"+dificuldade+"%' Order by duracao asc";

        try {
            Statement comando = banco.getConnection().createStatement();
            ResultSet rs = comando.executeQuery(sql);
            ResultSetMetaData md = rs.getMetaData();
            int colunas = md.getColumnCount();
            coluna.addElement("Data da Partida");
            coluna.addElement("Duracao");
            while(rs.next()){
                Vector row = new Vector(colunas);
                for (int i = 1;i<=colunas;i++){
                    row.addElement(rs.getObject(i));
                }
                data.addElement(row);
            }

            DefaultTableCellRenderer center = new DefaultTableCellRenderer();
            center.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);

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
            table.getColumn(coluna.get(0)).setCellRenderer(center);
            table.getColumn(coluna.get(1)).setCellRenderer(center);
            return table;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
