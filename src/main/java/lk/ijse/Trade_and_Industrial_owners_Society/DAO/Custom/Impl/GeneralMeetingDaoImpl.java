package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.GeneralMeetingDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.GeneralMeeting;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class GeneralMeetingDaoImpl implements GeneralMeetingDAO {
    @Override
    public GeneralMeeting getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM general_meeting WHERE gen_meeting_id = ?", id);

        GeneralMeeting dto = new GeneralMeeting();

        if(resultSet.next()){
            dto.setGeneral_meeting_id(resultSet.getString(1));
            dto.setDate(resultSet.getString(2));
            //meetingTm.setTime(resultSet.getString(4));
            dto.setLocation(resultSet.getString(5));
        }
        return dto;
    }

    @Override
    public ArrayList<GeneralMeeting> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(GeneralMeeting dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO general_meeting VALUES (?, ?, ?, ?, ?)",
                dto.getGeneral_meeting_id(),
                dto.getDate(),
                dto.getTime(),
                dto.getDescription(),
                dto.getLocation()
        );
    }

    @Override
    public boolean update(GeneralMeeting dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE general_meeting SET date = ?, time = ?, description = ?, location = ? WHERE gen_meeting_id = ?",
                dto.getDate(),
                dto.getTime(),
                dto.getDescription(),
                dto.getLocation(),
                dto.getGeneral_meeting_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM general_meeting WHERE gen_meeting_id = ?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("SELECT gen_meeting_id FROM general_meeting ORDER BY gen_meeting_id DESC LIMIT 1");
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT gen_meeting_id FROM general_meeting ORDER BY LENGTH(gen_meeting_id),gen_meeting_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
            System.out.println(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getTodayGeneralMeetingId() throws SQLException, ClassNotFoundException {
        String id = null;
        LocalDate date = LocalDate.now();

        ResultSet resultSet  = SQLUtill.execute("SELECT gen_meeting_id FROM general_meeting WHERE date = ?", date);

        if(resultSet.next()){
            id = resultSet.getString(1);
        }
        return id;
    }

    @Override
    public ResultSet getUpComingMeetingId() throws SQLException, ClassNotFoundException {
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String date = currentMonth.format(formatter);

        return SQLUtill.execute("SELECT * FROM general_meeting WHERE DATE_FORMAT(date, '%Y-%m') = ?", date);
    }
}
