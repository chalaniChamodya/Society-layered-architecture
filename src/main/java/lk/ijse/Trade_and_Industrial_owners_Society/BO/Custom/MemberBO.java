package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Member;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public interface MemberBO extends SuperBO {
    MemberDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveMember(MemberDto dto) throws SQLException, ClassNotFoundException;
    boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteMember(String id) throws SQLException, ClassNotFoundException;
    String generateNewMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberId() throws SQLException, ClassNotFoundException;
    String getMemberName(String id) throws SQLException, ClassNotFoundException;
    String getMemberEmailAddress(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllEmailAddress_sub() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemberEmailAddress_mem() throws SQLException, ClassNotFoundException;
    ArrayList<String> search(String searchTerm) throws SQLException, ClassNotFoundException;
    Map<String, LocalDate> calculateMemberDuration() throws SQLException, ClassNotFoundException;
}
