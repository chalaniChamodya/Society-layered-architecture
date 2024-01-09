package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.DeathBenefitBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.DeathBenefitDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.DeathBenefitDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.FamilyMemberDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Donation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeathBenefitBoImpl implements DeathBenefitBO {
    DeathBenefitDAO deathBenefitDAO = new DeathBenefitDaoImpl();

    @Override
    public DonationDto getData(String id) throws SQLException, ClassNotFoundException {
        Donation entity = deathBenefitDAO.getData(id);
        DonationDto dto = new DonationDto();

        dto.setDonation_id(entity.getDonation_id());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());
        dto.setFamily_member_id( entity.getFamily_member_id());

        return dto;
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
        return deathBenefitDAO.save(new Donation(donationDto.getDonation_id(), donationDto.getDate(), donationDto.getAmount(), donationDto.getFamily_member_id()));
    }

    @Override
    public boolean update(DonationDto dto) throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.update(new Donation(dto.getDonation_id(), dto.getDate(), dto.getAmount(), dto.getFamily_member_id()));
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.delete(id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = deathBenefitDAO.generateNewId();

        String currentDonationId = null;

        if(resultSet.next()){
            currentDonationId = resultSet.getString(1);
            return splitDeathBenefitId(currentDonationId);
        }
        return splitDeathBenefitId(null);
    }

    private String splitDeathBenefitId(String currentDonationId) {
        if(currentDonationId != null){
            String[] split = currentDonationId.split("DB");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "DB00" + id;
            }else if(id < 100){
                id++;
                return "DB0" + id;
            }else{
                id++;
                return "DB"+id;
            }
        }
        return "DB001";
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        return deathBenefitDAO.getAllId();
    }
}
