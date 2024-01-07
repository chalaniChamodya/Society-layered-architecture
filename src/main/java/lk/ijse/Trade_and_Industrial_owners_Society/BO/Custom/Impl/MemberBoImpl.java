package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.MemberBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.MemberDAO;
import lk.ijse.TradeAndIndustryOwners.DAO.DAOFactory;
import lk.ijse.TradeAndIndustryOwners.DTO.MemberDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MemberBoImpl implements MemberBO {
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);


    @Override
    public MemberDTO getData(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.getData(id);
    }

    @Override
    public boolean saveMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(dto);
    }

    @Override
    public boolean updateMember(MemberDTO dto) throws SQLException, ClassNotFoundException {
        return memberDAO.update(dto);
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public String generateNewMemberId() throws SQLException, ClassNotFoundException {
        return memberDAO.generateNewId();
    }

    @Override
    public ArrayList<String> getAllMemberId() throws SQLException, ClassNotFoundException {
        return memberDAO.getAllId();
    }

    @Override
    public String getMemberName(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.getName(id);
    }

    @Override
    public String getMemberEmailAddress(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.getEmailAddress(id);
    }

    @Override
    public ArrayList<String> getAllEmailAddress_sub() throws SQLException, ClassNotFoundException {
        return memberDAO.getAllEmailAddress();
    }

    @Override
    public ArrayList<String> getAllMemberEmailAddress_mem() throws SQLException, ClassNotFoundException {
        return memberDAO.getAllMemberEmailAddress();
    }
}
