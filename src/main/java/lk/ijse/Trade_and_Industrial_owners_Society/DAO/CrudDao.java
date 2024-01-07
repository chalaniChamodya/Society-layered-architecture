package lk.ijse.Trade_and_Industrial_owners_Society.DAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDao<T> extends SuperDAO{
    T getData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<T> getAllDetail() throws SQLException, ClassNotFoundException;
    boolean save(T dto) throws SQLException, ClassNotFoundException;
    boolean update(T dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
}
