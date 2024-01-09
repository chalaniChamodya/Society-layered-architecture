package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.GeneralMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.GeneralMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.GeneralMeetingDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.GeneralMeeting;

import java.sql.SQLException;
import java.util.ArrayList;

public class GeneralMeetingBoImpl implements GeneralMeetingBO {
    GeneralMeetingDAO generalMeetingDAO = new GeneralMeetingDaoImpl();

    @Override
    public GeneralMeeting getData(String id) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.getData(id);
    }

    @Override
    public boolean saveGeneralMeeting(GeneralMeetingDto dto) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.save(new GeneralMeeting(dto.getGeneral_meeting_id(), dto.getDate(), dto.getTime(),dto.getDescription(), dto.getLocation()));
    }

    @Override
    public boolean updateGeneralMeeting(GeneralMeetingDto dto) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.update(new GeneralMeeting(dto.getGeneral_meeting_id(), dto.getDate(), dto.getTime(),dto.getDescription(), dto.getLocation()));
    }

    @Override
    public boolean deleteGeneralMeeting(String id) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.delete(id);
    }

    @Override
    public String generateNewGeneralMeetingId() throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllGeneralMeetingId() throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.getAllId();
    }

    @Override
    public String getTodayGeneralMeetingId() throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.getTodayGeneralMeetingId();
    }
}