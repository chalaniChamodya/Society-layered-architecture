package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.FundingProgramDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.FundingProgramDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FundingProgramDaoImpl implements FundingProgramDAO {
    @Override
    public FundingProgramDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM funding_program WHERE program_id = ?", id);

        FundingProgramDTO programTm = new FundingProgramDTO();

        if(resultSet.next()){
            programTm.setProgram_id(resultSet.getString(1));
            programTm.setProgram_name(resultSet.getString(2));
            programTm.setDate(resultSet.getString(4));
        }
        return programTm;
    }

    @Override
    public ArrayList<FundingProgramDTO> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(FundingProgramDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO funding_program VALUES(?, ?, ?, ?, ?, ?, ?)",
                dto.getProgram_id(),
                dto.getProgram_name(),
                dto.getDescription(),
                dto.getDate(),
                dto.getLocation(),
                dto.getIncome(),
                dto.getExpenditure()
        );
    }

    @Override
    public boolean update(FundingProgramDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE funding_program SET " +
                "name =?, " +
                "description = ?, " +
                "date = ?, " +
                "location = ?, " +
                "income =?, " +
                "expenditure = ? " +
                "WHERE program_id = ?",
                dto.getProgram_name(),
                dto.getDescription(),
                dto.getDate(),
                dto.getLocation(),
                dto.getIncome(),
                dto.getExpenditure(),
                dto.getProgram_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM funding_program WHERE program_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT program_id FROM funding_program ORDER BY program_id DESC LIMIT 1");

        String currentFundingProgramId = null;

        if(resultSet.next()){
            currentFundingProgramId = resultSet.getString(1);
            return splitFundingProgramId(currentFundingProgramId);
        }
        return splitFundingProgramId(null);
    }

    private String splitFundingProgramId(String currentFundingProgramId) {
        if(currentFundingProgramId != null){
            String[] split = currentFundingProgramId.split("FP");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "FP00" + id;
            }else if(id < 100){
                id++;
                return "FP0" + id;
            }else{
                id++;
                return "FP"+id;
            }
        }
        return "FP001";
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT program_id FROM funding_program ORDER BY LENGTH(program_id),program_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
