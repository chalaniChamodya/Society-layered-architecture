package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.ScholarshipDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScholarshipBO extends SuperBO {
    ScholarshipDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSchol(ScholarshipDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSchol(ScholarshipDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSchol(String id) throws SQLException, ClassNotFoundException;
    String generateNewScolId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllScholId() throws SQLException, ClassNotFoundException;
}
