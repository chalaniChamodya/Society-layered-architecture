package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.ScholarshipDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Scholarship;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ScholarshipBO extends SuperBO {
    ScholarshipDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSchol(ScholarshipDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSchol(ScholarshipDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSchol(String id) throws SQLException, ClassNotFoundException;
    String generateNewScolId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllScholId() throws SQLException, ClassNotFoundException;
}
