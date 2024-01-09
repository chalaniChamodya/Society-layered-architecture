package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FamilyMemberDAO extends CrudDao<FamilyMemberDto> {
    boolean updateIsAlive(String id) throws SQLException, ClassNotFoundException;
    FamilyMemberDto getAllData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException;
}
