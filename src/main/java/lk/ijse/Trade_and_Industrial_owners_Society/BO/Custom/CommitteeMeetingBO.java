package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.CommitteeMeetingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommitteeMeetingBO extends SuperBO {
    CommitteeMeetingDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveCommitteeMeting(CommitteeMeetingDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateCommitteeMeeting(CommitteeMeetingDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteCommitteeMeeting(String id) throws SQLException, ClassNotFoundException;
    String generateNewCommitteeMetingId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCommitteeMeetingId() throws SQLException, ClassNotFoundException;
}
