package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.FamilyMember;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FamilyMemberDAO extends CrudDao<FamilyMember> {
    boolean updateIsAlive(String id) throws SQLException, ClassNotFoundException;
    FamilyMember getAllData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException;
}
