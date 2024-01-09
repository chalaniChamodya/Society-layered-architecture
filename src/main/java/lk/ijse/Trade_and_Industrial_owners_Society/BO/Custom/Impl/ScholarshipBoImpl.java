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
    public Scholarship getData(String id) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.getData(id);
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
