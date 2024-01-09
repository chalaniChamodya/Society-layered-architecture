package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Member;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public interface MemberDAO extends CrudDao<Member> {
    String getName(String id) throws SQLException, ClassNotFoundException;
    String getEmailAddress(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> search(String searchTerm) throws SQLException, ClassNotFoundException;
    Map<String, LocalDate> calculateMemberDuration() throws SQLException, ClassNotFoundException;
}
