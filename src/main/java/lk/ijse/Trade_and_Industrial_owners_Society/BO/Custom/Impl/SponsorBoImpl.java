package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SponsorBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.SponsorDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SponsorDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SponsorDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Sponsor;

import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorBoImpl implements SponsorBO {
    SponsorDAO sponsorDAO = new SponsorDaoImpl();

    @Override
    public SponsorDto getData(String id) throws SQLException, ClassNotFoundException {
        Sponsor entity = sponsorDAO.getData(id);
        SponsorDto dto = new SponsorDto();

        dto.setSponsor_id(entity.getSponsor_id());
        dto.setProgram_id(entity.getProgram_id());
        dto.setSponsor_name(entity.getSponsor_name());
        dto.setDescription(entity.getDescription());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());

        return dto;
    }

    @Override
    public boolean saveSponsor(SponsorDto dto) throws SQLException, ClassNotFoundException {
        return sponsorDAO.save(new Sponsor(dto.getSponsor_id(),dto.getProgram_id(), dto.getSponsor_name(), dto.getDescription(), dto.getDate(), dto.getAmount()));
    }

    @Override
    public boolean updateSponsor(SponsorDto dto) throws SQLException, ClassNotFoundException {
        return sponsorDAO.update(new Sponsor(dto.getSponsor_id(),dto.getProgram_id(), dto.getSponsor_name(), dto.getDescription(), dto.getDate(), dto.getAmount()));
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
