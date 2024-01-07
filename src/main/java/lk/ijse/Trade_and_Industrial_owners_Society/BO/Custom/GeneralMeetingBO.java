package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.GeneralMeetingDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GeneralMeetingBO extends SuperBO {
    GeneralMeetingDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveGeneralMeeting(GeneralMeetingDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateGeneralMeeting(GeneralMeetingDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteGeneralMeeting(String id) throws SQLException, ClassNotFoundException;
    String generateNewGeneralMeetingId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllGeneralMeetingId() throws SQLException, ClassNotFoundException;
}
