package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public interface MemberDAO extends CrudDao<Member> {
    String getName(String id) throws SQLException, ClassNotFoundException;
    String getEmailAddress(String id) throws SQLException, ClassNotFoundException;
    ResultSet search(String searchTerm) throws SQLException, ClassNotFoundException;
    ResultSet calculateMemberDuration() throws SQLException, ClassNotFoundException;
}
