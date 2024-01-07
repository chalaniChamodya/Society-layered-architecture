package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.SubscriptionFeeDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.SubscriptionFeeDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionFeeDaoImpl implements SubscriptionFeeDAO {
    @Override
    public SubscriptionFeeDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM subscription_fee WHERE subscription_fee_id = ?",id);
        SubscriptionFeeDTO dto = new SubscriptionFeeDTO();

        if (resultSet.next()) {
            dto.setSubscription_fee_id(resultSet.getString(1));
            dto.setMember_name(resultSet.getString(3));
            dto.setDate(resultSet.getString(4));
        }
        return dto;
    }

    @Override
    public ArrayList<SubscriptionFeeDTO> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SubscriptionFeeDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO subscription_fee VALUES (?,?,?,?,?)",
                dto.getSubscription_fee_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(SubscriptionFeeDTO dto) throws SQLException, ClassNotFoundException {
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
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT subscription_fee_id FROM subscription_fee ORDER BY subscription_fee_id DESC LIMIT 1");

        String currentSubscriptionFeeId = null;

        if(resultSet.next()){
            currentSubscriptionFeeId = resultSet.getString(1);
            return splitSubscriptionFeeId(currentSubscriptionFeeId);
        }
        return splitSubscriptionFeeId(null);
    }

    private static String splitSubscriptionFeeId(String currentSubscriptionFeeId) {
        if(currentSubscriptionFeeId != null){
            String[] split = currentSubscriptionFeeId.split("SF");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "SF00" + id;
            }else if(id < 100){
                id++;
                return "SF0" + id;
            }else{
                id++;
                return "SF"+id;
            }
        }
        return "SF001";
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
}
