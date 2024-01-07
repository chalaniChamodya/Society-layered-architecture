package lk.ijse.Trade_and_Industrial_owners_Society.Utill;

import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLUtill {
    public static <T> T execute(String sql , Object... args) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        PreparedStatement pstm = connection.prepareStatement(sql);

        for (int i = 0; i < args.length; i++) {
            pstm.setObject(i + 1, args[i]);
        }

        if (sql.startsWith("SELECT")) {
            return (T) pstm.executeQuery();
        } else {
            return (T) (Boolean) (pstm.executeUpdate() > 0);
        }
    }
}
