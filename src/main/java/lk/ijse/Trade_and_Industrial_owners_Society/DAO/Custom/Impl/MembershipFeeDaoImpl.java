package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MembershipFeeDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MembershipFee;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MembershipFeeDaoImpl implements MembershipFeeDAO {

    @Override
    public MembershipFee getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM member_fee WHERE member_fee_id = ?", id);

        MembershipFee dto = new MembershipFee();

        if (resultSet.next()) {
            dto.setMember_fee_id(resultSet.getString(1));
            dto.setMember_name(resultSet.getString(3));
            dto.setDate(resultSet.getString(4));
        }
        return dto;
    }

    @Override
    public ArrayList<MembershipFee> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(MembershipFee dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO member_fee VALUES (?,?,?,?,?)",
                dto.getMember_fee_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(MembershipFee dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE member_fee SET " +
                "member_id = ?, " +
                "member_name = ?, " +
                "date = ?, " +
                "amount = ? " +
                "WHERE member_fee_id = ?",
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount(),
                dto.getMember_fee_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM member_fee WHERE member_fee_id = ?",id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT member_fee_id FROM member_fee ORDER BY member_fee_id DESC LIMIT 1");

    }


    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT member_fee_id FROM member_fee ORDER BY LENGTH(member_fee_id),member_fee_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public int unPaidMembershipFeeCount() throws SQLException, ClassNotFoundException {
        int unpaidCount = 0;
        int currentYear = Year.now().getValue();

        ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS unpaid_count FROM member WHERE member_id NOT IN (SELECT member_id FROM member_fee WHERE YEAR(date) = ?)", String.valueOf(currentYear));

        if(resultSet.next()){
            unpaidCount = resultSet.getInt("unpaid_count");
            System.out.println("Member count of unpaid membership fee : "+ unpaidCount);
        }
        return unpaidCount;
    }


}
