package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.UserDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.UserDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDaoImpl implements UserDAO {

    @Override
    public UserDto getData(String id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<UserDto> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO user VALUES (?, ?, ?, ?, ?)",
                dto.getUser_id(),
                dto.getCom_mem_id(),
                dto.getRole(),
                dto.getUsername(),
                dto.getPassword()
        );
    }

    @Override
    public boolean update(UserDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM user WHERE user_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();

        String sql = "SELECT user_id FROM user ORDER BY user_id DESC LIMIT 1";
        ResultSet resultSet = connection.prepareStatement(sql).executeQuery();

        String currentUserId = null;

        if(resultSet.next()){
            currentUserId = resultSet.getString(1);
            return splitUserId(currentUserId);
        }
        return splitUserId(null);
    }

    private static String splitUserId(String currentUserId) {
        if(currentUserId != null){
            String[] split = currentUserId.split("U");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "U00" + id;
            }else if(id < 100){
                id++;
                return "U0" + id;
            }else{
                id++;
                return "U"+id;
            }
        }
        return "U001";
    }


    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        ResultSet set = SQLUtill.execute("SELECT role FROM user WHERE username=? AND password=?", userName, password);

        if (set.next()) {
            return set.getString(1);
        } else {
            return "No";
        }
    }
}
