package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MembershipFeeDAO extends CrudDao<MembershipFeeDto> {
    int unPaidMembershipFeeCount() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllUnpaidMembershipFeeId() throws SQLException, ClassNotFoundException;
}
