package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.CommitteeMeetingDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMeeting;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMeetingBoImpl implements CommitteeMeetingBO {
    CommitteeMeetingDAO committeeMeetingDAO = new CommitteeMeetingDaoImpl();

    @Override
    public CommitteeMeeting getData(String id) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.getData(id);
    }

    @Override
    public boolean saveCommitteeMeting(CommitteeMeetingDto dto) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.save(new CommitteeMeeting(dto.getCommittee_meeting_id(),dto.getDate(), dto.getTime(), dto.getDescription(), dto.getLocation()));
    }

    @Override
    public boolean updateCommitteeMeeting(CommitteeMeetingDto dto) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.update(new CommitteeMeeting(dto.getCommittee_meeting_id(),dto.getDate(), dto.getTime(), dto.getDescription(), dto.getLocation()));
    }

    @Override
    public boolean deleteCommitteeMeeting(String id) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.delete(id);
    }

    @Override
    public String generateNewCommitteeMetingId() throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllCommitteeMeetingId() throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.getAllId();
    }
}
