package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.ScholarshipBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.ScholarshipDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.ScholarshipDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.ScholarshipDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class ScholarshipBoImpl implements ScholarshipBO {
    ScholarshipDAO scholarshipDAO = new ScholarshipDaoImpl();

    @Override
    public ScholarshipDTO getData(String id) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.getData(id);
    }

    @Override
    public boolean saveSchol(ScholarshipDTO dto) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.save(dto);
    }

    @Override
    public boolean updateSchol(ScholarshipDTO dto) throws SQLException, ClassNotFoundException {
        return scholarshipDAO.update(dto);
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
