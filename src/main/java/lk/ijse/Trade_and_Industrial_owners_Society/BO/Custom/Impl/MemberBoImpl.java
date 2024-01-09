package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MemberDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.QueryDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.Member;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

public class MemberBoImpl implements MemberBO {
    MemberDAO memberDAO = (MemberDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBER);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);


    @Override
    public MemberDto getData(String id) throws SQLException, ClassNotFoundException {
        Member entity = memberDAO.getData(id);
        MemberDto dto = new MemberDto();

        dto.setMember_id(entity.getMember_id());
        dto.setName_with_initials(entity.getName_with_initials());
        dto.setFull_name(entity.getFull_name());
        dto.setBusiness_address(entity.getBusiness_address());
        dto.setPersonal_address(entity.getPersonal_address());
        dto.setBusiness_type(entity.getBusiness_type());
        dto.setNic(entity.getNic());
        dto.setEmail(entity.getEmail());
        dto.setDate_of_birth(entity.getDate_of_birth());
        dto.setPersonal_contact_num(entity.getPersonal_contact_num());
        dto.setBusiness_contact_num(entity.getBusiness_contact_num());
        dto.setAdmission_fee(entity.getAdmission_fee());
        dto.setJoined_date(entity.getJoined_date());

        return dto;
    }

    @Override
    public boolean saveMember(MemberDto dto) throws SQLException, ClassNotFoundException {
        return memberDAO.save(new Member(dto.getMember_id(), dto.getName_with_initials(), dto.getFull_name(), dto.getBusiness_address(), dto.getPersonal_address(), dto.getBusiness_type(), dto.getNic(), dto.getEmail(), dto.getDate_of_birth(), dto.getPersonal_contact_num(), dto.getBusiness_contact_num(), dto.getAdmission_fee(), dto.getJoined_date()));
    }

    @Override
    public boolean updateMember(MemberDto dto) throws SQLException, ClassNotFoundException {
        return memberDAO.update(new Member(dto.getMember_id(), dto.getName_with_initials(), dto.getFull_name(), dto.getBusiness_address(), dto.getPersonal_address(), dto.getBusiness_type(), dto.getNic(), dto.getEmail(), dto.getDate_of_birth(), dto.getPersonal_contact_num(), dto.getBusiness_contact_num(), dto.getAdmission_fee(), dto.getJoined_date()));
    }

    @Override
    public boolean deleteMember(String id) throws SQLException, ClassNotFoundException {
        return memberDAO.delete(id);
    }

    @Override
    public String generateNewMemberId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = memberDAO.generateNewId();

        String currentMemberId = null;

        if(resultSet.next()){
            currentMemberId = resultSet.getString(1);
            return splitMemberId(currentMemberId);
        }
        return splitMemberId(null);
    }

    private String splitMemberId(String currentMemberId) {
        if(currentMemberId != null){
            String[] split = currentMemberId.split("M");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "M00" + id;
            }else if(id < 100){
                id++;
                return "M0" + id;
            }else{
                id++;
                return "M"+id;
            }
        }
        return "M001";
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
        return queryDAO.getAllEmailAddress();
    }

    @Override
    public ArrayList<String> getAllMemberEmailAddress_mem() throws SQLException, ClassNotFoundException {
        return queryDAO.getAllMemberEmailAddress();
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
