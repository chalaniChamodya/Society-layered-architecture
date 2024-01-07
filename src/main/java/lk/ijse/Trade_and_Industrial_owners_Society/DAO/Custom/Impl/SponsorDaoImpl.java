package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.SponsorDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.SponsorDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorDaoImpl implements SponsorDAO {
    @Override
    public SponsorDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM sponsor WHERE sponsor_id = ?", id);

        SponsorDTO sponsorTm = new SponsorDTO();

        if(resultSet.next()){
            sponsorTm.setSponsor_id(resultSet.getString(1));
            sponsorTm.setProgram_id(resultSet.getString(2));
            sponsorTm.setSponsor_name(resultSet.getString(3));
        }
        return sponsorTm;
    }

    @Override
    public ArrayList<SponsorDTO> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SponsorDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO sponsor VALUES (?, ?, ?, ?, ?, ?)",
                dto.getSponsor_id(),
                dto.getProgram_id(),
                dto.getSponsor_name(),
                dto.getDescription(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(SponsorDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE sponsor SET " +
                "program_id = ?, " +
                "name = ?, " +
                "description = ?, " +
                "date = ?, " +
                "amount = ? " +
                "WHERE sponsor_id = ?",
                dto.getProgram_id(),
                dto.getSponsor_name(),
                dto.getDescription(),
                dto.getDate(),
                dto.getAmount(),
                dto.getSponsor_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM sponsor WHERE sponsor_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT sponsor_id FROM sponsor ORDER BY sponsor_id DESC LIMIT 1");

        String currentSponsorId = null;

        if(resultSet.next()){
            currentSponsorId = resultSet.getString(1);
            return splitSponsorId(currentSponsorId);
        }
        return splitSponsorId(null);
    }

    private String splitSponsorId(String currentSponsorId) {
        if(currentSponsorId != null){
            String[] split = currentSponsorId.split("S");
            int id = Integer.parseInt(split[1]);

            if(id < 10){
                id++;
                return "S00" + id;
            }else if(id < 100){
                id++;
                return "S0" + id;
            }else{
                id++;
                return "S" + id;
            }
        }
        return "S001";
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT sponsor_id FROM sponsor ORDER BY LENGTH(sponsor_id),sponsor_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
