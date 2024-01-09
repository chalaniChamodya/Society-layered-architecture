package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.CommitteeMeetingDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMeeting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMeetingBoImpl implements CommitteeMeetingBO {
    CommitteeMeetingDAO committeeMeetingDAO = new CommitteeMeetingDaoImpl();

    @Override
    public CommitteeMeetingDto getData(String id) throws SQLException, ClassNotFoundException {
        CommitteeMeetingDto dto = new CommitteeMeetingDto();
        CommitteeMeeting entity = committeeMeetingDAO.getData(id);
        dto.setCommittee_meeting_id(entity.getCommittee_meeting_id());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());

        return dto;
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
        ResultSet resultSet = committeeMeetingDAO.generateNewId();

        String currentCommitteeMeetingId = null;

        if(resultSet.next()){
            currentCommitteeMeetingId = resultSet.getString(1);
            return splitCommitteeMeetingId(currentCommitteeMeetingId);
        }
        return splitCommitteeMeetingId(null);
    }

    private static String splitCommitteeMeetingId(String currentCommitteeMeetingId) {
        if(currentCommitteeMeetingId != null){
            String[] split = currentCommitteeMeetingId.split("C");
            int id = Integer.parseInt(split[1]);

            if(id<10){
                id++;
                return "C00" + id;
            }else if(id<100){
                id++;
                return "C0" + id;
            }else{
                id++;
                return "C" + id;
            }
        }
        return "C001";
    }

    @Override
    public ArrayList<String> getAllCommitteeMeetingId() throws SQLException, ClassNotFoundException {
        return committeeMeetingDAO.getAllId();
    }
}
