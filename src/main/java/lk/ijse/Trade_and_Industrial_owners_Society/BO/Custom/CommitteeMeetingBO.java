package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMeeting;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CommitteeMeetingBO extends SuperBO {
    CommitteeMeeting getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveCommitteeMeting(CommitteeMeetingDto dto) throws SQLException, ClassNotFoundException;
    boolean updateCommitteeMeeting(CommitteeMeetingDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteCommitteeMeeting(String id) throws SQLException, ClassNotFoundException;
    String generateNewCommitteeMetingId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllCommitteeMeetingId() throws SQLException, ClassNotFoundException;
}
