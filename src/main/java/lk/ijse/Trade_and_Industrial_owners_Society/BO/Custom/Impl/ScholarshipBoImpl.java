package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.ScholarshipBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.ScholarshipDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.ScholarshipDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.ScholarshipDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Scholarship;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScholarshipBoImpl implements ScholarshipBO {
    ScholarshipDAO scholarshipDAO = new ScholarshipDaoImpl();

    @Override
    public ScholarshipDto getData(String id) throws SQLException, ClassNotFoundException {
        Scholarship entity = scholarshipDAO.getData(id);
        ScholarshipDto dto = new ScholarshipDto();

        dto.setDonation_id(entity.getDonation_id());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());
        dto.setFamily_member_id(entity.getFamily_member_id());

        return dto;
    }

    @Override
    public boolean saveSchol(ScholarshipDto dto) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.save(new Scholarship(dto.getDonation_id(), dto.getDate(), dto.getAmount(), dto.getFamily_member_id()));
    }

    @Override
    public boolean updateSchol(ScholarshipDto dto) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.update(new Scholarship(dto.getDonation_id(), dto.getDate(), dto.getAmount(), dto.getFamily_member_id()));
    }

    @Override
    public boolean deleteSchol(String id) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.delete(id);
    }

    @Override
    public String generateNewScolId() throws SQLException, ClassNotFoundException {
        return scholarshipDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllScholId() throws SQLException, ClassNotFoundException {
        return scholarshipDAO.getAllId();
    }
}
