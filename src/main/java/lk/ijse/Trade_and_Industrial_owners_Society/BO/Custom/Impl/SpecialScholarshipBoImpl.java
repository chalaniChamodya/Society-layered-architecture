package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.SpecialScholarshipBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.SpecialScholarshipDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.SpecialScholarshipDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.SpecialScholDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class SpecialScholarshipBoImpl implements SpecialScholarshipBO {
    SpecialScholarshipDAO specialScholarshipDAO = new SpecialScholarshipDaoImpl();

    @Override
    public SpecialScholDTO getData(String id) throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.getData(id);
    }

    @Override
    public boolean saveSpecialSchol(SpecialScholDTO dto) throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.save(dto);
    }

    @Override
    public boolean updateSpecialSchol(SpecialScholDTO dto) throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.update(dto);
    }

    @Override
    public boolean deleteSpecialSchol(String id) throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.delete(id);
    }

    @Override
    public String generateNewSpecialScholId() throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllSpecialScholId() throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.getAllId();
    }
}
