package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FundingProgramDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FundingProgramBO extends SuperBO {
    FundingProgramDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveFundingProgram(FundingProgramDto dto) throws SQLException, ClassNotFoundException;
    boolean updateFundingProgram(FundingProgramDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteFundingProgram(String id) throws SQLException, ClassNotFoundException;
    String generateNewFundingProgramId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllFundingProgramId() throws SQLException, ClassNotFoundException;
}
