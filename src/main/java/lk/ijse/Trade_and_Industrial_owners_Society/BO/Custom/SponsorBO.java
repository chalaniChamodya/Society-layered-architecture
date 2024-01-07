package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.SponsorDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SponsorBO extends SuperBO {
    SponsorDTO getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveSponsor(SponsorDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateSponsor(SponsorDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteSponsor(String id) throws SQLException, ClassNotFoundException;
    String generateNewSponsorId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllSponsorId() throws SQLException, ClassNotFoundException;
}
