package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.DeathBenefitDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Donation;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeathBenefitDaoImpl implements DeathBenefitDAO {
    @Override
    public Donation getData(String id) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtill.execute("SELECT * FROM death_benefit WHERE death_benefit_id = ?",id);

        Donation donationTm = new Donation();

        if(resultSet.next()){
            donationTm.setDonation_id(resultSet.getString(1));
            donationTm.setDate(resultSet.getString(2));
            donationTm.setAmount(resultSet.getString(3));
        }
        return donationTm;
    }

    @Override
    public ArrayList<Donation> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Donation dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO death_benefit VALUES(?, ?, ?, ?)",
                dto.getDonation_id(),
                dto.getDate(),
                dto.getAmount(),
                dto.getFamily_member_id()
        );
    }

    @Override
    public boolean update(Donation dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE death_benefit SET " +
                "date = ?, " +
                "amount = ?, " +
                "family_mem_id = ? " +
                "WHERE death_benefit_id = ?",
                dto.getDate(),
                dto.getAmount(),
                dto.getFamily_member_id(),
                dto.getDonation_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM death_benefit WHERE death_benefit_id = ?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT death_benefit_id FROM death_benefit ORDER BY death_benefit_id DESC LIMIT 1");

    }


    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT death_benefit_id FROM death_benefit ORDER BY LENGTH(death_benefit_id),death_benefit_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
