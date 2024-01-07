package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.FundingProgramDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FundingProgramBO extends SuperBO {
    FundingProgramDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveFundingProgram(FundingProgramDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateFundingProgram(FundingProgramDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteFundingProgram(String id) throws SQLException, ClassNotFoundException;
    String generateNewFundingProgramId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllFundingProgramId() throws SQLException, ClassNotFoundException;
}
