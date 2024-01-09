package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SpecialScholDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SpecialScholarshipBO extends SuperBO {
    SpecialScholDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSpecialSchol(SpecialScholDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSpecialSchol(SpecialScholDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSpecialSchol(String id) throws SQLException, ClassNotFoundException;
    String generateNewSpecialScholId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSpecialScholId() throws SQLException, ClassNotFoundException;

}
