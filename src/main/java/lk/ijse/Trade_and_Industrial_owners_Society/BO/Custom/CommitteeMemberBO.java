package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommitteeMemberBO extends SuperBO {
    CommitteeMemberDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveCommitteeMember(CommitteeMemberDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCommitteeMember(CommitteeMemberDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCommitteeMember(String id) throws SQLException, ClassNotFoundException;
    String generateNewCommitteeMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCommitteeMemberId() throws SQLException, ClassNotFoundException;

}
