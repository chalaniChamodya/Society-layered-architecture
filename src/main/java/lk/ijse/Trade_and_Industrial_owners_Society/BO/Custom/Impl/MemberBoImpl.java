package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class MemberBoImpl implements MemberBO {
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);


    @Override
    public MemberDto getData(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.getData(id);
    }

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(dto);
    }

    @Override
    public boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException {
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

    @Override
    public ArrayList<String> search(String searchTerm) throws SQLException, ClassNotFoundException {
        return memberDAO.search(searchTerm);
    }

    @Override
    public Map<String, LocalDate> calculateMemberDuration() throws SQLException, ClassNotFoundException {
        return memberDAO.calculateMemberDuration();
    }
}
