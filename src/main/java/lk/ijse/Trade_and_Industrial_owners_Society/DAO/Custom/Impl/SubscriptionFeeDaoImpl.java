package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SubscriptionFeeDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SubscriptionFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.SubscriptionFee;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class SubscriptionFeeDaoImpl implements SubscriptionFeeDAO {
    @Override
    public SubscriptionFee getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM subscription_fee WHERE subscription_fee_id = ?",id);

        SubscriptionFee dto = new SubscriptionFee();

        if (resultSet.next()) {
            dto.setSubscription_fee_id(resultSet.getString(1));
            dto.setMember_name(resultSet.getString(3));
            dto.setDate(resultSet.getString(4));
        }
        return dto;
    }

    @Override
    public ArrayList<SubscriptionFee> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SubscriptionFee dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO subscription_fee VALUES (?,?,?,?,?)",
                dto.getSubscription_fee_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(SubscriptionFee dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE subscription_fee SET " +
                "member_id = ?, " +
                "member_name = ?, " +
                "date = ?, " +
                "amount = ? " +
                "WHERE subscription_fee_id = ?",
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount(),
                dto.getSubscription_fee_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM subscription_fee WHERE subscription_fee_id = ?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT subscription_fee_id FROM subscription_fee ORDER BY subscription_fee_id DESC LIMIT 1");

    }


    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT subscription_fee_id FROM subscription_fee ORDER BY LENGTH(subscription_fee_id),subscription_fee_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public int unPaidSubscriptionFeeCount() throws SQLException, ClassNotFoundException {
        int unPaidCount = 0;
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        ResultSet resultSet = SQLUtill.execute("SELECT COUNT(*) AS unpaid_count FROM member WHERE member_id NOT IN (SELECT member_id FROM subscription_fee WHERE DATE_FORMAT(date, '%Y-%m') = ?)", currentMonth.format(formatter));

        if(resultSet.next()){
            unPaidCount = resultSet.getInt("unpaid_count");
            System.out.println("Number of members with unpaid Subscription fee : "+ unPaidCount);
        }
        return unPaidCount;
    }


}
