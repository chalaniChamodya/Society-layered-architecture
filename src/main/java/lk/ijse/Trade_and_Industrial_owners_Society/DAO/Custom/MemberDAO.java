package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.TradeAndIndustryOwners.DAO.CrudDao;
import lk.ijse.TradeAndIndustryOwners.DTO.MemberDTO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberDAO extends CrudDao<MemberDto> {
    String getName(String id) throws SQLException, ClassNotFoundException;
    String getEmailAddress(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmailAddress() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberEmailAddress() throws SQLException, ClassNotFoundException;
}
