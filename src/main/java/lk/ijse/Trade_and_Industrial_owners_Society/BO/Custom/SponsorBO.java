package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SponsorDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Sponsor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SponsorBO extends SuperBO {
    SponsorDto getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSponsor(SponsorDto dto) throws SQLException, ClassNotFoundException;
    boolean updateSponsor(SponsorDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteSponsor(String id) throws SQLException, ClassNotFoundException;
    String generateNewSponsorId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSponsorId() throws SQLException, ClassNotFoundException;
}
