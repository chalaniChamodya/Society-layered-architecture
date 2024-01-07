package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.DeathBenefitDAO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.FamilyMemberDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.DonationDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.DeathBenefitDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DeathBenefitDaoImpl implements DeathBenefitDAO {
    @Override
    public DonationDto getData(String id) throws SQLException, ClassNotFoundException {
       ResultSet resultSet = SQLUtill.execute("SELECT * FROM death_benefit WHERE death_benefit_id = ?",id);

        DonationDto donationTm = new DonationDto();

        if(resultSet.next()){
            donationTm.setDonation_id(resultSet.getString(1));
            donationTm.setDate(resultSet.getString(2));
            donationTm.setAmount(resultSet.getString(3));
        }
        return donationTm;
    }

    @Override
    public ArrayList<DonationDto> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(DonationDto dto) throws SQLException, ClassNotFoundException {
        FamilyMemberDAO familyMemberDAO = new FamilyMemberDaoImpl();

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

    public boolean saveDonation(DonationDto donationDto) throws SQLException {
        System.out.println(donationDto);

        try {
            return SQLUtill.execute("INSERT INTO death_benefit VALUES(?, ?, ?, ?)",
                    donationDto.getDonation_id(),
                    donationDto.getDate(),
                    donationDto.getAmount(),
                    donationDto.getFamily_member_id()
            );
        }catch (Exception e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public boolean update(DonationDto dto) throws SQLException, ClassNotFoundException {
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
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT death_benefit_id FROM death_benefit ORDER BY death_benefit_id DESC LIMIT 1");

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
        ResultSet resultSet = SQLUtill.execute("SELECT death_benefit_id FROM death_benefit ORDER BY LENGTH(death_benefit_id),death_benefit_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
