package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SubscriptionFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.SubscriptionFee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SubscriptionFeeDAO extends CrudDao<SubscriptionFee> {
    int unPaidSubscriptionFeeCount() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllUnpaidSubscriptionFeeId() throws SQLException, ClassNotFoundException;
}
