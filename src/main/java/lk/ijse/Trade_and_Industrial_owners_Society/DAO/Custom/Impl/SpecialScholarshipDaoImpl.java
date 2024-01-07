package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.SpecialScholarshipDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.SpecialScholDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpecialScholarshipDaoImpl implements SpecialScholarshipDAO {
    @Override
    public SpecialScholDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM special_schol WHERE schol_id = ?", id);

        SpecialScholDTO donationTm = new SpecialScholDTO();

        if(resultSet.next()){
            donationTm.setSchol_id(resultSet.getString("schol_id"));
            donationTm.setDate(resultSet.getString("date"));
            donationTm.setAmount(resultSet.getString("amount"));
        }
        return donationTm;
    }

    @Override
    public ArrayList<SpecialScholDTO> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SpecialScholDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO special_schol VALUES(?, ?, ?, ?, ?)",
                dto.getSchol_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(SpecialScholDTO dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM special_schol WHERE schol_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT schol_id FROM special_schol ORDER BY schol_id DESC LIMIT 1");

        String currentDonationId = null;

        if(resultSet.next()){
            currentDonationId = resultSet.getString(1);
            return splitScholId(currentDonationId);
        }
        return splitScholId(null);
    }

    private String splitScholId(String currentSScholId) {
        if(currentSScholId != null){
            String[] split = currentSScholId.split("SS");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "SS00" + id;
            }else if(id < 100){
                id++;
                return "SS0" + id;
            }else{
                id++;
                return "SS"+id;
            }
        }
        return "SS001";
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT schol_id FROM special_schol ORDER BY LENGTH(schol_id),schol_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public Map<String, LocalDate> calculateMemberDuration() throws SQLException, ClassNotFoundException {
        Map<String, LocalDate> memberJoinDates = new HashMap<>();

        ResultSet resultSet = SQLUtill.execute("SELECT member_id, joined_date FROM member");

        if (resultSet.next()){
            String MemberId = resultSet.getString("member_id");
            LocalDate joinedDate = resultSet.getDate("joined_date").toLocalDate();
            memberJoinDates.put(MemberId, joinedDate);
        }
        return memberJoinDates;
    }

    @Override
    public ResultSet getAttendance() throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("SELECT member_id, COUNT(*) AS total_meetings FROM general_attendance GROUP BY member_id");
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
            if (attendanceResultSet.next()){
                String member_id = attendanceResultSet.getString("member_id");
                int member_meetings = attendanceResultSet.getInt("total_meetings");
                System.out.println(member_id + "  " + member_meetings);

                double attendancePercentage = (double) member_meetings/totalMeeting * 100;
                System.out.println(attendancePercentage);
                meetingAttendance.put(member_id, attendancePercentage);
            }
       
        return meetingAttendance;
    }
}
