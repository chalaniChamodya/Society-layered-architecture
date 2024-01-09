package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MeetingAttendanceDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MeetingAttendanceDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MeetingAttendance;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.*;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MeetingAttendanceDaoImpl implements MeetingAttendanceDAO {

    @Override
    public MeetingAttendance getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM general_attendance WHERE gen_meeting_id = ?", id);

        MeetingAttendance attendanceTm = new MeetingAttendance();

        if(resultSet.next()){
            attendanceTm.setMeeting_id(resultSet.getString(1));
            attendanceTm.setMember_id(resultSet.getString(2));
            attendanceTm.setMember_name(resultSet.getString(3));
        }
        return attendanceTm;
    }

    @Override
    public ArrayList<MeetingAttendance> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(MeetingAttendance dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO general_attendance VALUES(?, ?, ?, ?, ?)",
                dto.getMeeting_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getTime()
        );
    }

    @Override
    public boolean update(MeetingAttendance dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT gen_meeting_id FROM general_attendance ORDER BY LENGTH(gen_meeting_id),gen_meeting_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public boolean deleteAttendance(String id, String mem_id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM general_attendance WHERE gen_meeting_id = ? AND member_id = ?", id, mem_id);
    }

    @Override
    public Map<String, Double> calculateMeetingAttendance() throws SQLException, ClassNotFoundException {
        Map<String, Double> meetingAttendance = new HashMap<>();
        ResultSet totalMeetingResultSet = SQLUtill.execute("SELECT COUNT(*) AS total_meetings FROM general_meeting");

            int totalMeeting = 0;
            if(totalMeetingResultSet.next()){
                totalMeeting = totalMeetingResultSet.getInt("total_meetings");
                System.out.println("Total meetings " + totalMeeting);
            }

            ResultSet attendanceResultSet = getAttendance();
            if (attendanceResultSet.next()) {
                String member_id = attendanceResultSet.getString("member_id");
                int member_meetings = attendanceResultSet.getInt("total_meetings");
                System.out.println(member_id + "  " + member_meetings);

                double attendancePercentage = (double) member_meetings / totalMeeting * 100;
                System.out.println(attendancePercentage);
                meetingAttendance.put(member_id, attendancePercentage);
            }
        return meetingAttendance;
    }

    @Override
    public ResultSet getAttendance() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT member_id, COUNT(*) AS total_meetings FROM general_attendance GROUP BY member_id");
    }

    @Override
    public int generalMeetingAttendanceCount() throws SQLException, ClassNotFoundException {
        int attendanceCount = 0;

        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS Attendance_count FROM general_attendance WHERE DATE_FORMAT(date, '%Y-%m') = ?", currentMonth.format(formatter));

        if(resultSet.next()){
            attendanceCount = resultSet.getInt("Attendance_count");
            System.out.println("Monthly Attendance" + attendanceCount);
        }
        return attendanceCount;
    }

    @Override
    public int committeeMeetingAttendanceCount() throws SQLException, ClassNotFoundException {
        int attendanceCount = 0;

        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS Attendance_count FROM committee_attendance WHERE DATE_FORMAT(date, '%Y-%m') = ?", currentMonth.format(formatter));

        if(resultSet.next()){
            attendanceCount = resultSet.getInt("Attendance_count");
            System.out.println("Monthly Attendance" + attendanceCount);
        }
        return attendanceCount;
    }
}
