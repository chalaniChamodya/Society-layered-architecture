package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MembershipFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.MembershipFeeDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.MembershipFeeDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipFeeBoImpl implements MembershipFeeBO {
    MembershipFeeDAO membershipFeeDAO = new MembershipFeeDaoImpl();

    @Override
    public MembershipFeeDto getData(String id) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.getData(id);
    }

    @Override
    public boolean saveMemFee(MembershipFeeDto dto) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.save(dto);
    }

    @Override
    public boolean updateMemFee(MembershipFeeDto dto) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.update(dto);
    }

    @Override
    public boolean deleteMemFee(String id) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.delete(id);
    }

    @Override
    public String generateNewMemFeeId() throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.generateNewId();
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
        return membershipFeeDAO.getAllUnpaidMembershipFeeId();
    }
}
