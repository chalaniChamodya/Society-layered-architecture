package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.FamilyMemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.FamilyMember;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FamilyMemberDaoImpl implements FamilyMemberDAO {

    @Override
    public FamilyMember getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM member_family WHERE family_mem_id = ?", id);

        FamilyMember dto = new FamilyMember();

        if(resultSet.next()){
            dto.setMember_id(resultSet.getString("member_id"));
            dto.setFamily_mem_id(resultSet.getString("Family_mem_id"));
            dto.setRelationship(resultSet.getString("relationship"));
            dto.setIsAlive(resultSet.getString("isAlive"));
        }
        return dto;
    }

    @Override
    public ArrayList<FamilyMember> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(FamilyMember dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO member_family VALUES(?, ?, ?, ?, ?, ?, ?)",
                dto.getFamily_mem_id(),
                dto.getMember_id(),
                dto.getName(),
                dto.getRelationship(),
                dto.getOccupation(),
                dto.getDate_of_birth(),
                dto.getIsAlive()
        );
    }

    @Override
    public boolean update(FamilyMember dto) throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("UPDATE member_family SET " +
               "member_id =?, " +
               "name = ?, " +
               "relationship = ?, " +
               "occupation = ?, " +
               "date_of_birth = ?, " +
               "isAlive = ? " +
               "WHERE family_mem_id =?",
               dto.getMember_id(),
               dto.getName(),
               dto.getRelationship(),
               dto.getOccupation(),
               dto.getDate_of_birth(),
               dto.getIsAlive(),
               dto.getFamily_mem_id()
       );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM member_family WHERE family_mem_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT family_mem_id FROM member_family ORDER BY family_mem_id DESC LIMIT 1");

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
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT family_mem_id FROM member_family ORDER BY LENGTH(family_mem_id),family_mem_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public boolean updateIsAlive(String id) throws SQLException, ClassNotFoundException {
        String isAlive = "No";

        return SQLUtill.execute("UPDATE member_family SET isAlive = ? WHERE family_mem_id = ?", isAlive, id);
    }

    @Override
    public FamilyMember getAllData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM member_family WHERE family_mem_id = ?");

        FamilyMember memberDto = new FamilyMember();
        if(resultSet.next()){
            memberDto.setFamily_mem_id(id);
            memberDto.setMember_id(resultSet.getString(2));
            memberDto.setName(resultSet.getString(3));
            memberDto.setRelationship(resultSet.getString(4));
            memberDto.setOccupation(resultSet.getString(5));
            memberDto.setDate_of_birth(resultSet.getString(6));
            memberDto.setIsAlive(resultSet.getString(7));
        }
        return memberDto;
    }

    @Override
    public ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException {
        ArrayList<String> famMemId = new ArrayList<>();
        String isAlive = "yes";

        ResultSet resultSet = SQLUtill.execute("SELECT family_mem_id FROM member_family WHERE isAlive = ?", isAlive);

        while (resultSet.next()){
            famMemId.add(resultSet.getString(1));
        }

        return famMemId;
    }
}
