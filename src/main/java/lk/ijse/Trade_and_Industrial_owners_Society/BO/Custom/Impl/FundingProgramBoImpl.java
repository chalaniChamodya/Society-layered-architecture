package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.FundingProgramBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.FundingProgramDAO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.FundingProgramDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DTO.FundingProgramDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class FundingProgramBoImpl implements FundingProgramBO {
    FundingProgramDAO fundingProgramDAO = new FundingProgramDaoImpl();

    @Override
    public FundingProgramDTO getData(String id) throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.getData(id);
    }

    @Override
    public boolean saveFundingProgram(FundingProgramDTO dto) throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.save(dto);
    }

    @Override
    public boolean updateFundingProgram(FundingProgramDTO dto) throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.update(dto);
    }

    @Override
    public boolean deleteFundingProgram(String id) throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.delete(id);
    }

    @Override
    public String generateNewFundingProgramId() throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllFundingProgramId() throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.getAllId();
    }
}
