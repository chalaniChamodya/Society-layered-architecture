package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.CommitteeMemberBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.CommitteeMemberDao;
import lk.ijse.TradeAndIndustryOwners.DAO.DAOFactory;
import lk.ijse.TradeAndIndustryOwners.DTO.CommitteeMemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMemberBoImpl implements CommitteeMemberBO {
    CommitteeMemberDao committeeMemberDao = (CommitteeMemberDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMMITTEE_MEMBER);

    @Override
    public CommitteeMemberDTO getData(String id) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.getData(id);
    }

    @Override
    public boolean saveCommitteeMember(CommitteeMemberDTO dto) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.save(dto);
    }

    @Override
    public boolean updateCommitteeMember(CommitteeMemberDTO dto) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.update(dto);
    }

    @Override
    public boolean deleteCommitteeMember(String id) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.delete(id);
    }

    @Override
    public String generateNewCommitteeMemberId() throws SQLException, ClassNotFoundException {
        return committeeMemberDao.generateNewId();
    }

    @Override
    public ArrayList<String> getAllCommitteeMemberId() throws SQLException, ClassNotFoundException {
        return committeeMemberDao.getAllId();
    }
}
