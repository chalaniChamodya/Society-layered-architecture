package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.DeathBenefitBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.DeathBenefitDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.DeathBenefitDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.FamilyMemberDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeathBenefitBoImpl implements DeathBenefitBO {
    DeathBenefitDAO deathBenefitDAO = new DeathBenefitDaoImpl();

    @Override
    public DonationDto getData(String id) throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.getData(id);
    }

    @Override
    public boolean saveTransaction(DonationDto dto) throws SQLException, ClassNotFoundException {
        FamilyMemberDAO familyMemberDAO = new FamilyMemberDaoImpl();

        Connection connection = null;

        boolean result = false;
        try {
            connection.setAutoCommit(false);

            boolean isSavedDonation = saveDonation(dto);
            // System.out.println(isSavedDonation);
            connection.commit();
            if(isSavedDonation){
                boolean isUpdated = familyMemberDAO.updateIsAlive(dto.getFamily_member_id());
                if(isUpdated){
                    connection.commit();
                    result = true;
                }
            }
        }catch (SQLException e){
            connection.rollback();
        }finally {
            connection.setAutoCommit(true);
        }
        return result;
    }

    @Override
    public boolean saveDonation(DonationDto donationDto) throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.save(donationDto);
    }

    @Override
    public boolean update(DonationDto dto) throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.update(dto);
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.getAllId();
    }
}
