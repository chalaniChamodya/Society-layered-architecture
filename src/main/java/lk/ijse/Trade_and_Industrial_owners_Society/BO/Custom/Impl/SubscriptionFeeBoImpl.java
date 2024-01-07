package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.SubscriptionFeeBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.SubscriptionFeeDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.SubscriptionFeeDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.SubscriptionFeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionFeeBoImpl implements SubscriptionFeeBO {
    SubscriptionFeeDAO subscriptionFeeDAO = new SubscriptionFeeDaoImpl();

    @Override
    public SubscriptionFeeDTO getData(String id) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.getData(id);
    }

    @Override
    public boolean saveSubFee(SubscriptionFeeDTO dto) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.save(dto);
    }

    @Override
    public boolean updateSubFee(SubscriptionFeeDTO dto) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.update(dto);
    }

    @Override
    public boolean deleteSubFee(String id) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.delete(id);
    }

    @Override
    public String generateNewSubFeeId() throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllSubFeeId() throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.getAllId();
    }
}
