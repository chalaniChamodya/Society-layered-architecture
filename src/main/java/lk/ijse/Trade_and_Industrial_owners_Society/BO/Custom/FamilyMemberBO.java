package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.FamilyMember;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FamilyMemberBO extends SuperBO {
    FamilyMemberDto getData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<FamilyMember> getAllDetail() throws SQLException, ClassNotFoundException;
    boolean saveFamilyMember(FamilyMemberDto dto) throws SQLException, ClassNotFoundException;
    boolean updateFamilyMember(FamilyMemberDto dto) throws SQLException, ClassNotFoundException;
    boolean deleteFamilyMember(String id) throws SQLException, ClassNotFoundException;
    String generateNewFamilyMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllFamilyMemberId() throws SQLException, ClassNotFoundException;
    boolean updateIsAlive(String id) throws SQLException, ClassNotFoundException;
    FamilyMemberDto getAllFamilyMemberData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException;
}
