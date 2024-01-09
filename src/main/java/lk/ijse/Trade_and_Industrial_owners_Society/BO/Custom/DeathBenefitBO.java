package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Donation;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DeathBenefitBO extends SuperBO {
    Donation getData(String id) throws SQLException, ClassNotFoundException;
    boolean saveTransaction(DonationDto dto) throws SQLException, ClassNotFoundException;
    boolean saveDonation(DonationDto donationDto) throws SQLException, ClassNotFoundException;
    boolean update(DonationDto dto) throws SQLException, ClassNotFoundException;
    boolean delete(String id) throws SQLException, ClassNotFoundException;
    String generateNewId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllId() throws SQLException, ClassNotFoundException;
}
