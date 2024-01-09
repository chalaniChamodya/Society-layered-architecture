package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SpecialScholarshipBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.SpecialScholarshipDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SpecialScholarshipDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SpecialScholDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.SpecialSchol;

import java.sql.SQLException;
import java.util.ArrayList;

public class SpecialScholarshipBoImpl implements SpecialScholarshipBO {
    SpecialScholarshipDAO specialScholarshipDAO = new SpecialScholarshipDaoImpl();

    @Override
    public SpecialScholDto getData(String id) throws SQLException, ClassNotFoundException {
        SpecialSchol entity = specialScholarshipDAO.getData(id);
        SpecialScholDto dto = new SpecialScholDto();

        dto.setSchol_id(entity.getSchol_id());
        dto.setMember_id(entity.getMember_id());
        dto.setMember_name(entity.getMember_name());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());

        return dto;
    }

    @Override
    public boolean saveSpecialSchol(SpecialScholDto dto) throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.save(new SpecialSchol(dto.getSchol_id(), dto.getMember_id(), dto.getMember_name(), dto.getDate(), dto.getAmount()));
    }

    @Override
    public boolean updateSpecialSchol(SpecialScholDto dto) throws SQLException, ClassNotFoundException {
        return specialScholarshipDAO.update(new SpecialSchol(dto.getSchol_id(), dto.getMember_id(), dto.getMember_name(), dto.getDate(), dto.getAmount()));
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
