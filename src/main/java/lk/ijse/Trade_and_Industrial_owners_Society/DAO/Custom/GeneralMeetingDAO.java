package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.GeneralMeeting;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface GeneralMeetingDAO extends CrudDao<GeneralMeeting> {
    String getTodayGeneralMeetingId() throws SQLException, ClassNotFoundException;
    ResultSet getUpComingMeetingId() throws SQLException, ClassNotFoundException;
}
