package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.TradeAndIndustryOwners.BO.SuperBO;
import lk.ijse.TradeAndIndustryOwners.DTO.FamilyMemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FamilyMemberBO extends SuperBO {
    FamilyMemberDTO getData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<FamilyMemberDTO> getAllDetail() throws SQLException, ClassNotFoundException;
    boolean saveFamilyMember(FamilyMemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean updateFamilyMember(FamilyMemberDTO dto) throws SQLException, ClassNotFoundException;
    boolean deleteFamilyMember(String id) throws SQLException, ClassNotFoundException;
    String generateNewFamilyMemberId() throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllFamilyMemberId() throws SQLException, ClassNotFoundException;
    boolean updateIsAlive(String id) throws SQLException, ClassNotFoundException;
    FamilyMemberDTO getAllFamilyMemberData(String id) throws SQLException, ClassNotFoundException;
    ArrayList<String> getAllAliveFamMemberId() throws SQLException, ClassNotFoundException;
}
