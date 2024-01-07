package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.CommitteeMeetingBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.CommitteeMeetingDAO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.CommitteeMeetingDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DTO.CommitteeMeetingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMeetingBoImpl implements CommitteeMeetingBO {
    CommitteeMeetingDAO committeeMeetingDAO = new CommitteeMeetingDaoImpl();

    @Override
    public CommitteeMeetingDTO getData(String id) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.getData(id);
    }

    @Override
    public boolean saveCommitteeMeting(CommitteeMeetingDTO dto) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.save(dto);
    }

    @Override
    public boolean updateCommitteeMeeting(CommitteeMeetingDTO dto) throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.update(dto);
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
