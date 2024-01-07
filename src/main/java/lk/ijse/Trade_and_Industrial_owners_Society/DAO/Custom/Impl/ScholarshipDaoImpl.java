package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.ScholarshipDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.ScholarshipDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScholarshipDaoImpl implements ScholarshipDAO {
    @Override
    public ScholarshipDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM scholarship WHERE scholarship_id = ?",id);

        ScholarshipDTO donationTm = new ScholarshipDTO();

        if(resultSet.next()){
            donationTm.setDonation_id(resultSet.getString(1));
            donationTm.setDate(resultSet.getString(2));
            donationTm.setAmount(resultSet.getString(3));
        }
        return donationTm;
    }

    @Override
    public ArrayList<ScholarshipDTO> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(ScholarshipDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO scholarship VALUES(?, ?, ?, ?)",
                dto.getDonation_id(),
                dto.getDate(),
                dto.getAmount(),
                dto.getFamily_member_id()
        );
    }

    @Override
    public boolean update(ScholarshipDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE scholarship SET " +
                "date = ?, " +
                "amount = ?, " +
                "family_mem_id = ? " +
                "WHERE scholarship_id = ?",
                dto.getDate(),
                dto.getAmount(),
                dto.getFamily_member_id(),
                dto.getDonation_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM scholarship WHERE scholarship_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT scholarship_id FROM scholarship ORDER BY scholarship_id DESC LIMIT 1");

        String currentScholId = null;

        if(resultSet.next()){
            currentScholId = resultSet.getString(1);
            return splitScholId(currentScholId);
        }
        return splitScholId(null);
    }

    private String splitScholId(String currentScholId) {
        if(currentScholId != null){
            String[] split = currentScholId.split("S");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "S00" + id;
            }else if(id < 100){
                id++;
                return "S0" + id;
            }else{
                id++;
                return "S"+id;
            }
        }
        return "S001";
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT scholarship_id FROM scholarship ORDER BY LENGTH(scholarship_id),scholarship_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
