package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMemberDao;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMember;

import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMemberBoImpl implements CommitteeMemberBO {
    CommitteeMemberDao committeeMemberDao = (CommitteeMemberDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMMITTEE_MEMBER);

    @Override
    public CommitteeMember getData(String id) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.getData(id);
    }

    @Override
    public boolean saveCommitteeMember(CommitteeMemberDto dto) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.save(new CommitteeMember(dto.getCom_mem_id(), dto.getMember_id(), dto.getName(), dto.getPosition(), dto.getDate()));
    }

    @Override
    public boolean updateCommitteeMember(CommitteeMemberDto dto) throws SQLException, ClassNotFoundException {
        return committeeMemberDao.update(new CommitteeMember(dto.getCom_mem_id(), dto.getMember_id(), dto.getName(), dto.getPosition(), dto.getDate()));
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
