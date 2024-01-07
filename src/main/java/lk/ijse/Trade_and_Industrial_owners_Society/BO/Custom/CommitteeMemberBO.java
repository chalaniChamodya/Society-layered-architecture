package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.CommitteeMemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommitteeMemberBO extends SuperBO {
    CommitteeMemberDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveCommitteeMember(CommitteeMemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCommitteeMember(CommitteeMemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCommitteeMember(String id) throws SQLException, ClassNotFoundException;
    String generateNewCommitteeMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCommitteeMemberId() throws SQLException, ClassNotFoundException;

}
