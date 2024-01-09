package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MembershipFee;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MembershipFeeBO extends SuperBO {
    MembershipFee getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveMemFee(MembershipFeeDto dto) throws SQLException, ClassNotFoundException;
    boolean updateMemFee(MembershipFeeDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteMemFee(String id) throws SQLException, ClassNotFoundException;
    String generateNewMemFeeId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemFeeId() throws SQLException, ClassNotFoundException;
    int unPaidMembershipFeeCount() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllUnpaidMembershipFeeId() throws SQLException, ClassNotFoundException;
}
