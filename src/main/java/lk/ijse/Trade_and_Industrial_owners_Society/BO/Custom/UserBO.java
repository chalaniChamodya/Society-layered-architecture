package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.UserDto;

import java.sql.SQLException;

public interface UserBO extends SuperBO {
    boolean save(UserDto dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    String checkUsernameAndPassword(String userName, String password) throws SQLException, ClassNotFoundException;
}
