package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMemberDao;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMemberBoImpl implements CommitteeMemberBO {
    CommitteeMemberDao committeeMemberDao = (CommitteeMemberDao) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMMITTEE_MEMBER);

    @Override
    public CommitteeMemberDto getData(String id) throws SQLException, ClassNotFoundException {
        CommitteeMemberDto dto = new CommitteeMemberDto();
        CommitteeMember entity = committeeMemberDao.getData(id);
        dto.setCom_mem_id(entity.getCom_mem_id());
        dto.setMember_id(entity.getMember_id());
        dto.setName(entity.getName());
        dto.setPosition(entity.getPosition());
        dto.setDate(entity.getDate());

        return dto;
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
        ResultSet resultSet = committeeMemberDao.generateNewId();

        String currentCommitteeMemberId = null;

        if(resultSet.next()){
            currentCommitteeMemberId = resultSet.getString(1);
            return splitCommitteeMemberId(currentCommitteeMemberId);
        }
        return splitCommitteeMemberId(null);
    }

    private String splitCommitteeMemberId(String currentCommitteeMemberId) {
        if(currentCommitteeMemberId != null){
            String[] split = currentCommitteeMemberId.split("C");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "C00" + id;
            }else if(id < 100){
                id++;
                return "C0" + id;
            }else{
                id++;
                return "C"+id;
            }
        }
        return "C001";
    }

    @Override
    public ArrayList<String> getAllCommitteeMemberId() throws SQLException, ClassNotFoundException {
        return committeeMemberDao.getAllId();
    }
}
