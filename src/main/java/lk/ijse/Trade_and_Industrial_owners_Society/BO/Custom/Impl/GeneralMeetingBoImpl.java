package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.GeneralMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.GeneralMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.GeneralMeetingDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.GeneralMeeting;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GeneralMeetingBoImpl implements GeneralMeetingBO {
    GeneralMeetingDAO generalMeetingDAO = (GeneralMeetingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.GENERAL_MEETING);
    CommitteeMeetingDAO committeeMeetingDAO = (CommitteeMeetingDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.COMMITTEE_MEETING);

    @Override
    public GeneralMeetingDto getData(String id) throws SQLException, ClassNotFoundException {
        GeneralMeeting entity = generalMeetingDAO.getData(id);
        GeneralMeetingDto dto = new GeneralMeetingDto();

        dto.setGeneral_meeting_id(entity.getGeneral_meeting_id());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setDescription(entity.getDescription());
        dto.setLocation(entity.getLocation());

        return dto;
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
        ResultSet resultSet = generalMeetingDAO.generateNewId();

        String currentGeneralMeetingId = null;

        if(resultSet.next()){
            currentGeneralMeetingId = resultSet.getString(1);
            return splitGeneralMeetingId(currentGeneralMeetingId);
        }
        return splitGeneralMeetingId(null);
    }

    private static String splitGeneralMeetingId(String currentGeneralMeetingId) {
        if(currentGeneralMeetingId != null){
            String[] split = currentGeneralMeetingId.split("G");
            int id = Integer.parseInt(split[1]);
            if(id<10){
                id++;
                return "G00" + id;
            }else if(id<100){
                id++;
                return "G0" + id;
            }else{
                id++;
                return "G" + id;
            }
        }
        return "G001";
    }


    @Override
    public ArrayList<String> getAllGeneralMeetingId() throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.getAllId();
    }

    @Override
    public String getTodayGeneralMeetingId() throws SQLException, ClassNotFoundException {
        return generalMeetingDAO.getTodayGeneralMeetingId();
    }

    @Override
    public ArrayList<String> getUpComingMeetingId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();
        try {
            ResultSet resultSet1 = generalMeetingDAO.getUpComingMeetingId();
            while (resultSet1.next()){
                list.add(resultSet1.getString(1));
            }
        }catch (Exception e){}
        try {
            ResultSet resultSet2 = committeeMeetingDAO.getUpComingMeetingId();
            while (resultSet2.next()){
                list.add(resultSet2.getString(1));
            }
        }catch (Exception e){}

        return list;
    }
}