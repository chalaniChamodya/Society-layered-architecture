package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MeetingAttendanceDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MeetingAttendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public interface MeetingAttendanceDAO extends CrudDao<MeetingAttendance> {
    boolean deleteAttendance(String id, String mem_id) throws SQLException, ClassNotFoundException;
    Map<String,Double> calculateMeetingAttendance() throws SQLException, ClassNotFoundException;
    ResultSet getAttendance() throws SQLException, ClassNotFoundException;
    int  generalMeetingAttendanceCount () throws SQLException, ClassNotFoundException;
    int  committeeMeetingAttendanceCount () throws SQLException, ClassNotFoundException;
}
