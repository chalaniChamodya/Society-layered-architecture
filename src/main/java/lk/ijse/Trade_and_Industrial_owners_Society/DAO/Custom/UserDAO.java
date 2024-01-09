package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.UserDto;

import java.sql.SQLException;

public interface UserDAO extends CrudDao<UserDto> {
    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;
}
