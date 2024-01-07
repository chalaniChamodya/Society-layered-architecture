package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.MembershipFeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MembershipFeeBO extends SuperBO {
    MembershipFeeDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveMemFee(MembershipFeeDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateMemFee(MembershipFeeDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteMemFee(String id) throws SQLException, ClassNotFoundException;
    String generateNewMemFeeId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllMemFeeId() throws SQLException, ClassNotFoundException;
}
