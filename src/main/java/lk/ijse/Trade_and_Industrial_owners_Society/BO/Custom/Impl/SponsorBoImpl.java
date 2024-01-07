package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.SponsorBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.SponsorDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.SponsorDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.SponsorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorBoImpl implements SponsorBO {
    SponsorDAO sponsorDAO = new SponsorDaoImpl();

    @Override
    public SponsorDTO getData(String id) throws SQLException, ClassNotFoundException {
        return sponsorDAO.getData(id);
    }

    @Override
    public boolean saveSponsor(SponsorDTO dto) throws SQLException, ClassNotFoundException {
        return sponsorDAO.save(dto);
    }

    @Override
    public boolean updateSponsor(SponsorDTO dto) throws SQLException, ClassNotFoundException {
        return sponsorDAO.update(dto);
    }

    @Override
    public boolean deleteSponsor(String id) throws SQLException, ClassNotFoundException {
        return sponsorDAO.delete(id);
    }

    @Override
    public String generateNewSponsorId() throws SQLException, ClassNotFoundException {
        return sponsorDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllSponsorId() throws SQLException, ClassNotFoundException {
        return sponsorDAO.getAllId();
    }
}
