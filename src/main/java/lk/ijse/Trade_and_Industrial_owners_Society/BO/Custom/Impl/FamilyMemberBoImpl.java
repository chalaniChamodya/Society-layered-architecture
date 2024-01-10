package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FamilyMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.FamilyMemberDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.FamilyMember;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FamilyMemberBoImpl implements FamilyMemberBO {
    FamilyMemberDAO familyMemberDAO = (FamilyMemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FAMILY_MEMBER);


    @Override
    public FamilyMemberDto getData(String id) throws SQLException, ClassNotFoundException {
        FamilyMember entity = familyMemberDAO.getData(id);
        FamilyMemberDto dto = new FamilyMemberDto();

        dto.setFamily_mem_id(entity.getFamily_mem_id());
        dto.setMember_id(entity.getMember_id());
        dto.setName(entity.getName());
        dto.setRelationship(entity.getRelationship());
        dto.setOccupation(entity.getOccupation());
        dto.setDate_of_birth(entity.getDate_of_birth());
        dto.setIsAlive(entity.getIsAlive());

        return dto;
    }

    @Override
    public ArrayList<FamilyMember> getAllDetail() throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getAllDetail();
    }

    @Override
    public boolean saveFamilyMember(FamilyMemberDto dto) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.save(new FamilyMember(dto.getFamily_mem_id(), dto.getMember_id(), dto.getName(),dto.getRelationship(), dto.getOccupation(),dto.getDate_of_birth(), dto.getIsAlive()));
    }

    @Override
    public boolean updateFamilyMember(FamilyMemberDto dto) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.update(new FamilyMember(dto.getFamily_mem_id(), dto.getMember_id(), dto.getName(),dto.getRelationship(), dto.getOccupation(),dto.getDate_of_birth(), dto.getIsAlive()));
    }

    @Override
    public boolean deleteFamilyMember(String id) throws SQLException, ClassNotFoundException {
        return familyMemberDAO.delete(id);
    }

    @Override
    public String generateNewFamilyMemberId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = familyMemberDAO.generateNewId();

        String currentFamilyMemberId = null;

        if(resultSet.next()){
            currentFamilyMemberId = resultSet.getString(1);
            return splitFamilyMemberId(currentFamilyMemberId);
        }
        return splitFamilyMemberId(null);
    }

    private String splitFamilyMemberId(String currentFamilyMemberId) {
        if(currentFamilyMemberId != null){
            String[] split = currentFamilyMemberId.split("FM");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "FM00" + id;
            }else if(id < 100){
                id++;
                return "FM0" + id;
            }else{
                id++;
                return "FM"+id;
            }
        }
        return "FM001";
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
       // return familyMemberDAO.getAllData(id);
        return null;
    }

    @Override
    public ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException {
        return familyMemberDAO.getAllAliveFamMemberId();
    }
}
