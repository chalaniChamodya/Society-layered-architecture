package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.GeneralMeeting;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GeneralMeetingBO extends SuperBO {
    GeneralMeeting getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveGeneralMeeting(GeneralMeetingDto dto) throws SQLException, ClassNotFoundException;
    boolean updateGeneralMeeting(GeneralMeetingDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteGeneralMeeting(String id) throws SQLException, ClassNotFoundException;
    String generateNewGeneralMeetingId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllGeneralMeetingId() throws SQLException, ClassNotFoundException;
    String getTodayGeneralMeetingId() throws SQLException, ClassNotFoundException;
}
