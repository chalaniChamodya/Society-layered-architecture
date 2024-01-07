package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.SpecialScholDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SpecialScholarshipBO extends SuperBO {
    SpecialScholDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSpecialSchol(SpecialScholDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSpecialSchol(SpecialScholDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSpecialSchol(String id) throws SQLException, ClassNotFoundException;
    String generateNewSpecialScholId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSpecialScholId() throws SQLException, ClassNotFoundException;

}
