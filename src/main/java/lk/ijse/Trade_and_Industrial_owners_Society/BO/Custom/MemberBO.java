package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.MemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MemberBO extends SuperBO {
    MemberDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;
    String generateNewMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberId() throws SQLException, ClassNotFoundException;
    String getMemberName(String id) throws SQLException, ClassNotFoundException;
    String getMemberEmailAddress(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmailAddress_sub() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberEmailAddress_mem() throws SQLException, ClassNotFoundException;
}
