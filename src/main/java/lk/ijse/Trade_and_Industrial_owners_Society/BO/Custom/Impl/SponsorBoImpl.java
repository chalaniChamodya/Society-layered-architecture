package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SponsorBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.SponsorDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SponsorDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SponsorDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Sponsor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorBoImpl implements SponsorBO {
    SponsorDAO sponsorDAO = (SponsorDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SPONSOR);

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
        ResultSet resultSet = sponsorDAO.generateNewId();

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
    public ArrayList<String> getAllSponsorId() throws SQLException, ClassNotFoundException {
        return sponsorDAO.getAllId();
    }
}
