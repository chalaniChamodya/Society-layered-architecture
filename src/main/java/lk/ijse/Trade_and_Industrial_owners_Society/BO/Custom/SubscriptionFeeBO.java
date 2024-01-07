package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.SubscriptionFeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubscriptionFeeBO extends SuperBO {
    SubscriptionFeeDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSubFee(SubscriptionFeeDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSubFee(SubscriptionFeeDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSubFee(String id) throws SQLException, ClassNotFoundException;
    String generateNewSubFeeId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSubFeeId() throws SQLException, ClassNotFoundException;
}
