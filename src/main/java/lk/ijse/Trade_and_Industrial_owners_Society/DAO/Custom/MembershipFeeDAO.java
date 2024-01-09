package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MembershipFee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MembershipFeeDAO extends CrudDao<MembershipFee> {
    int unPaidMembershipFeeCount() throws SQLException, ClassNotFoundException;

}
