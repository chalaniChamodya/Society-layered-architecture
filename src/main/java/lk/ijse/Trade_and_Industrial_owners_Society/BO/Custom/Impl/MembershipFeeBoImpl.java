package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MembershipFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.MembershipFeeDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MembershipFeeDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.QueryDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.MembershipFee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipFeeBoImpl implements MembershipFeeBO {
    MembershipFeeDAO membershipFeeDAO = (MembershipFeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MEMBERSHIP_FEE);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public MembershipFeeDto getData(String id) throws SQLException, ClassNotFoundException {
        MembershipFee entity = membershipFeeDAO.getData(id);
        MembershipFeeDto dto = new MembershipFeeDto();

        dto.setMember_fee_id(entity.getMember_fee_id());
        dto.setMember_id(entity.getMember_id());
        dto.setMember_name(entity.getMember_name());
        dto.setDate(entity.getDate());
        dto.setAmount(entity.getAmount());

        return dto;
    }

    @Override
    public boolean saveMemFee(MembershipFeeDto dto) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.save(new MembershipFee(dto.getMember_fee_id(), dto.getMember_id(), dto.getMember_name(), dto.getDate(), dto.getAmount()));
    }

    @Override
    public boolean updateMemFee(MembershipFeeDto dto) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.update(new MembershipFee(dto.getMember_fee_id(), dto.getMember_id(), dto.getMember_name(), dto.getDate(), dto.getAmount()));
    }

    @Override
    public boolean deleteMemFee(String id) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.delete(id);
    }

    @Override
    public String generateNewMemFeeId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = membershipFeeDAO.generateNewId();

        String currentMemberFeeId = null;

        if(resultSet.next()){
            currentMemberFeeId = resultSet.getString(1);
            return splitMemberFeeId(currentMemberFeeId);
        }
        return splitMemberFeeId(null);
    }

    private static String splitMemberFeeId(String currentMemberFeeId) {
        if(currentMemberFeeId != null){
            String[] split = currentMemberFeeId.split("MF");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "MF00" + id;
            }else if(id < 100){
                id++;
                return "MF0" + id;
            }else{
                id++;
                return "MF"+id;
            }
        }
        return "MF001";
    }

    @Override
    public ArrayList<String> getAllMemFeeId() throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.getAllId();
    }

    @Override
    public int unPaidMembershipFeeCount() throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.unPaidMembershipFeeCount();
    }

    @Override
    public ArrayList<String> getAllUnpaidMembershipFeeId() throws SQLException, ClassNotFoundException {
        return queryDAO.getAllUnpaidMembershipFeeId();
    }
}
