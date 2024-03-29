package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMeeting;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CommitteeMeetingDaoImpl implements CommitteeMeetingDAO {
    @Override
    public CommitteeMeeting getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM committee_meeting WHERE com_meeting_id = ?", id);

        CommitteeMeeting dto = new CommitteeMeeting();

        if(resultSet.next()){
            dto.setCommittee_meeting_id(resultSet.getString(1));
            dto.setDate(resultSet.getString(2));
            // meetingTm.setTime(resultSet.getString(4));
            dto.setLocation(resultSet.getString(5));
        }
        return dto;
    }

    @Override
    public ArrayList<CommitteeMeeting> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CommitteeMeeting dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO committee_meeting VALUES (?, ?, ?, ?, ?)",
                dto.getCommittee_meeting_id(),
                dto.getDate(),
                dto.getTime(),
                dto.getDescription(),
                dto.getLocation()
        );
    }

    @Override
    public boolean update(CommitteeMeeting dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE committee_meeting SET " +
                "date = ?, " +
                "time = ?, " +
                "description = ?, " +
                "location = ? " +
                "WHERE com_meeting_id = ?",
                dto.getDate(),
                dto.getTime(),
                dto.getDescription(),
                dto.getLocation(),
                dto.getCommittee_meeting_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM committee_meeting WHERE com_meeting_id = ?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT com_meeting_id FROM committee_meeting ORDER BY com_meeting_id DESC LIMIT 1");
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT com_meeting_id FROM committee_meeting ORDER BY LENGTH(com_meeting_id),com_meeting_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ResultSet getUpComingMeetingId() throws SQLException, ClassNotFoundException {
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String date = currentMonth.format(formatter);

        return SQLUtill.execute("SELECT * FROM committee_meeting WHERE DATE_FORMAT(date, '%Y-%m') = ?", date);
    }
}
