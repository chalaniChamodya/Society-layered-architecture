package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.UserBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.UserDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.UserDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.UserDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserBoImpl implements UserBO {
    UserDAO userDAO = new UserDaoImpl();

    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(new User(dto.getUser_id(), dto.getCom_mem_id(), dto.getRole(), dto.getUsername(), dto.getPassword()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = userDAO.generateNewId();

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
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        return userDAO.checkUsernameAndPassword(userName,password);
    }
}
