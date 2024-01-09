package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SubscriptionFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.SubscriptionFeeDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SubscriptionFeeDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SubscriptionFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.SubscriptionFee;

import java.sql.SQLException;
import java.util.ArrayList;

public class SubscriptionFeeBoImpl implements SubscriptionFeeBO {
    SubscriptionFeeDAO subscriptionFeeDAO = new SubscriptionFeeDaoImpl();

    @Override
    public SubscriptionFee getData(String id) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.getData(id);
    }

    @Override
    public boolean saveSubFee(SubscriptionFeeDto dto) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.save(new SubscriptionFee(dto.getSubscription_fee_id(), dto.getMember_id(), dto.getMember_name(),dto.getDate() , dto.getAmount()));
    }

    @Override
    public boolean updateSubFee(SubscriptionFeeDto dto) throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.update(new SubscriptionFee(dto.getSubscription_fee_id(), dto.getMember_id(), dto.getMember_name(),dto.getDate() , dto.getAmount()));
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

    @Override
    public int unPaidSubscriptionFeeCount() throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.unPaidSubscriptionFeeCount();
    }

    @Override
    public ArrayList<String> getAllUnpaidSubscriptionFeeId() throws SQLException, ClassNotFoundException {
        return subscriptionFeeDAO.getAllUnpaidSubscriptionFeeId();
    }
}
