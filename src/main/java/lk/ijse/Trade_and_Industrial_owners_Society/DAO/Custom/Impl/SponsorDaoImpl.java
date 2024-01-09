package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SponsorDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SponsorDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Sponsor;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorDaoImpl implements SponsorDAO {
    @Override
    public Sponsor getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM sponsor WHERE sponsor_id = ?", id);

        Sponsor sponsorTm = new Sponsor();

        if(resultSet.next()){
            sponsorTm.setSponsor_id(resultSet.getString(1));
            sponsorTm.setProgram_id(resultSet.getString(2));
            sponsorTm.setSponsor_name(resultSet.getString(3));
        }
        return sponsorTm;
    }

    @Override
    public ArrayList<Sponsor> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Sponsor dto) throws SQLException, ClassNotFoundException {
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
    public boolean update(Sponsor dto) throws SQLException, ClassNotFoundException {
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
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT sponsor_id FROM sponsor ORDER BY sponsor_id DESC LIMIT 1");

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
