package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MeetingAttendanceBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.MeetingAttendanceDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MeetingAttendanceDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MeetingAttendanceDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class MeetingAttendanceBoImpl implements MeetingAttendanceBO {
    MeetingAttendanceDAO meetingAttendanceDAO = new MeetingAttendanceDaoImpl();

    @Override
    public MeetingAttendanceDto getData(String id) throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.getData(id);
    }

    @Override
    public boolean save(MeetingAttendanceDto dto) throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.save(dto);
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.getAllId();
    }

    @Override
    public boolean deleteAttendance(String id, String mem_id) throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.deleteAttendance(id, mem_id);
    }

    @Override
    public Map<String, Double> calculateMeetingAttendance() throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.calculateMeetingAttendance();
    }

    @Override
    public ResultSet getAttendance() throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.getAttendance();
    }

    @Override
    public int generalMeetingAttendanceCount() throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.generalMeetingAttendanceCount();
    }

    @Override
    public int committeeMeetingAttendanceCount() throws SQLException, ClassNotFoundException {
        return meetingAttendanceDAO.committeeMeetingAttendanceCount();
    }
}
