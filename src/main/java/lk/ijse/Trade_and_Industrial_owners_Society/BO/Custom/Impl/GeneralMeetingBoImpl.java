package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.GeneralMeetingBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.GeneralMeetingDAO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.GeneralMeetingDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DTO.GeneralMeetingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class GeneralMeetingBoImpl implements GeneralMeetingBO{
    GeneralMeetingDAO generalMeetingDAO = new GeneralMeetingDaoImpl();

    @Override
    public GeneralMeetingDTO getData(String id) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.getData(id);
    }

    @Override
    public boolean saveGeneralMeeting(GeneralMeetingDTO dto) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.save(dto);
    }

    @Override
    public boolean updateGeneralMeeting(GeneralMeetingDTO dto) throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.update(dto);
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
}