package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FundingProgramBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FundingProgramDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.FundingProgramDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FundingProgramDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.FundingProgram;

import java.sql.SQLException;
import java.util.ArrayList;

public class FundingProgramBoImpl implements FundingProgramBO {
    FundingProgramDAO fundingProgramDAO = new FundingProgramDaoImpl();

    @Override
    public FundingProgramDto getData(String id) throws SQLException, ClassNotFoundException {
        FundingProgram entity = fundingProgramDAO.getData(id);
        FundingProgramDto dto = new FundingProgramDto();

        dto.setProgram_id(entity.getProgram_id());
        dto.setProgram_name(entity.getProgram_name());
        dto.setDescription(entity.getDescription());
        dto.setDate(entity.getDate());
        dto.setLocation(entity.getLocation());
        dto.setIncome(entity.getIncome());
        dto.setExpenditure(entity.getExpenditure());

        return dto;
    }

    @Override
    public boolean saveFundingProgram(FundingProgramDto dto) throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.save(new FundingProgram(dto.getProgram_id(), dto.getProgram_name(), dto.getDescription(), dto.getDate(),dto.getLocation(), dto.getIncome(), dto.getExpenditure()));
    }

    @Override
    public boolean updateFundingProgram(FundingProgramDto dto) throws SQLException, ClassNotFoundException {
        return fundingProgramDAO.update(new FundingProgram(dto.getProgram_id(), dto.getProgram_name(), dto.getDescription(), dto.getDate(),dto.getLocation(), dto.getIncome(), dto.getExpenditure()));
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
