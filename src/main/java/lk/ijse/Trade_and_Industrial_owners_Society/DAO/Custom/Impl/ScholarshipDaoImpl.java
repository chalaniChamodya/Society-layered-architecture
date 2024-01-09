package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.ScholarshipDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.ScholarshipDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Scholarship;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScholarshipDaoImpl implements ScholarshipDAO {
    @Override
    public Scholarship getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM scholarship WHERE scholarship_id = ?",id);

        Scholarship donationTm = new Scholarship();

        if(resultSet.next()){
            donationTm.setDonation_id(resultSet.getString(1));
            donationTm.setDate(resultSet.getString(2));
            donationTm.setAmount(resultSet.getString(3));
        }
        return donationTm;
    }

    @Override
    public ArrayList<Scholarship> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Scholarship dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO scholarship VALUES(?, ?, ?, ?)",
                dto.getDonation_id(),
                dto.getDate(),
                dto.getAmount(),
                dto.getFamily_member_id()
        );
    }

    @Override
    public boolean update(Scholarship dto) throws SQLException, ClassNotFoundException {
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
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT scholarship_id FROM scholarship ORDER BY scholarship_id DESC LIMIT 1");

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
