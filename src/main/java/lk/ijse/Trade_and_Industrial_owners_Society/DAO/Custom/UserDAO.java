package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.UserDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDao<User> {
    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;
}
