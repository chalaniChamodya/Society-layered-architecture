package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MeetingAttendanceDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MeetingAttendance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public interface MeetingAttendanceBO extends SuperBO {
    MeetingAttendance getData(String id) throws SQLException, ClassNotFoundException;
    boolean save(MeetingAttendanceDto dto) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
    boolean deleteAttendance(String id, String mem_id) throws SQLException, ClassNotFoundException;
    Map<String, Double> calculateMeetingAttendance() throws SQLException, ClassNotFoundException;
    ResultSet getAttendance() throws SQLException, ClassNotFoundException;
    int generalMeetingAttendanceCount() throws SQLException, ClassNotFoundException;
    int committeeMeetingAttendanceCount() throws SQLException, ClassNotFoundException;
}
