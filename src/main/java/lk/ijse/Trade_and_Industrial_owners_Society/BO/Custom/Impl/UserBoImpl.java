package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.UserBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.UserDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.UserDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.UserDto;

import java.sql.SQLException;

public class UserBoImpl implements UserBO {
    UserDAO userDAO = new UserDaoImpl();

    @Override
    public boolean save(UserDto dto) throws SQLException, ClassNotFoundException {
        return userDAO.save(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return userDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewId();
    }

    @Override
    public String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException {
        return userDAO.checkUsernameAndPassword(userName,password);
    }
}
