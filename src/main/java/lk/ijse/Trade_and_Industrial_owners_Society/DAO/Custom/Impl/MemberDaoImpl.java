package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Member;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MemberDaoImpl implements MemberDAO {
    @Override
    public Member getData(String id) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtill.execute("SELECT * FROM member WHERE member_id = ?", id);

        Member memberDTO = new Member();

        if(resultSet.next()){
            memberDTO.setMember_id(resultSet.getString(1));
            memberDTO.setName_with_initials(resultSet.getString(3));
            memberDTO.setPersonal_contact_num(resultSet.getString(10));
            memberDTO.setBusiness_type(resultSet.getString(6));
            memberDTO.setNic(resultSet.getString(7));
        }
        return memberDTO;
    }

    @Override
    public ArrayList<Member> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Member dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO member VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dto.getMember_id(),
                dto.getName_with_initials(),
                dto.getFull_name(),
                dto.getBusiness_address(),
                dto.getPersonal_address(),
                dto.getBusiness_type(),
                dto.getNic(),
                dto.getEmail(),
                dto.getDate_of_birth(),
                dto.getPersonal_contact_num(),
                dto.getBusiness_contact_num(),
                dto.getAdmission_fee(),
                dto.getJoined_date()
        );
    }

    @Override
    public boolean update(Member dto) throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("UPDATE member SET name_with_initials =?, " +
               "full_name = ?, " +
               "business_address = ?, " +
               "personal_address = ?, " +
               "business_type = ?, " +
               "nic = ?, " +
               "email = ?, " +
               "date_of_birth =?, " +
               "personal_contact_num = ?, " +
               "business_contact_num =?, " +
               "admission_fee= ?, " +
               "joined_date = ? " +
               "WHERE member_id =?" ,
               dto.getName_with_initials(),
               dto.getFull_name(),
               dto.getBusiness_address(),
               dto.getPersonal_address(),
               dto.getBusiness_type(),
               dto.getNic(),
               dto.getEmail(),
               dto.getDate_of_birth(),
               dto.getBusiness_contact_num(),
               dto.getPersonal_contact_num(),
               dto.getAdmission_fee(),
               dto.getJoined_date(),
               dto.getMember_id()
       );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM member WHERE member_id = ?",id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT member_id FROM member ORDER BY member_id DESC LIMIT 1");

    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT member_id FROM member ORDER BY LENGTH(member_id),member_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public String getEmailAddress(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM member WHERE member_id = ?", id);

        String email = null;

        if(resultSet.next()){
            email = resultSet.getString(8);
        }
        return email;
    }



    @Override
    public ArrayList<String> search(String searchTerm) throws SQLException, ClassNotFoundException {
        ArrayList<String> searchList = new ArrayList<>();
        String Name = "%"+searchTerm+"%";

        ResultSet resultSet = SQLUtill.execute("SELECT * FROM member WHERE member_id = ? OR name_with_initials LIKE ?", searchTerm, Name);

        if(resultSet.next()){
            String memberId = resultSet.getString(1);
            String name = resultSet.getString(2);
            String joinedDate = resultSet.getString("joined_date");
            String businessType = resultSet.getString("business_type");

            searchList.add(memberId);
            searchList.add(name);
            searchList.add(joinedDate);
            searchList.add(businessType);
            searchList.add(resultSet.getString("business_contact_num"));
            searchList.add(resultSet.getString("business_address"));
        }
        return searchList;
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
    public String getName(String id) throws SQLException, ClassNotFoundException {
        String name = "";

        ResultSet resultSet = SQLUtill.execute("SELECT name_with_initials FROM member WHERE member_id = ?");

        while (resultSet.next()){
            name = resultSet.getString(1);
        }
        return name;
    }
}
