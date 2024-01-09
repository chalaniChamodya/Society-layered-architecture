package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SubscriptionFeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubscriptionFeeBO extends SuperBO {
    SubscriptionFeeDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSubFee(SubscriptionFeeDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSubFee(SubscriptionFeeDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSubFee(String id) throws SQLException, ClassNotFoundException;
    String generateNewSubFeeId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSubFeeId() throws SQLException, ClassNotFoundException;
    int unPaidSubscriptionFeeCount() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllUnpaidSubscriptionFeeId() throws SQLException, ClassNotFoundException;
}
