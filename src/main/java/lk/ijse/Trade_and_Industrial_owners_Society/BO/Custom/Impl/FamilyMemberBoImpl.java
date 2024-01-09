package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FamilyMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.FamilyMemberDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class FamilyMemberBoImpl implements FamilyMemberBO {
    FamilyMemberDAO familyMemberDAO = new FamilyMemberDaoImpl();


    @Override
    public FamilyMemberDto getData(String id) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getData(id);
    }

    @Override
    public ArrayList<FamilyMemberDto> getAllDetail() throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getAllDetail();
    }

    @Override
    public boolean saveFamilyMember(FamilyMemberDto dto) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.save(dto);
    }

    @Override
    public boolean updateFamilyMember(FamilyMemberDto dto) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.update(dto);
    }

    @Override
    public boolean deleteFamilyMember(String id) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.delete(id);
    }

    @Override
    public String generateNewFamilyMemberId() throws SQLException, ClassNotFoundException {
        return familyMemberDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllFamilyMemberId() throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getAllId();
    }

    @Override
    public boolean updateIsAlive(String id) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.updateIsAlive(id);
    }

    @Override
    public FamilyMemberDto getAllFamilyMemberData(String id) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getAllData(id);
    }

    @Override
    public ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getAllAliveFamMemberId();
    }
}
