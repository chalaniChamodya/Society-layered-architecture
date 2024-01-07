package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.BO.Custom.MembershipFeeBO;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.MembershipFeeDaoImpl;
import lk.ijse.TradeAndIndustryOwners.DAO.Custom.MembershipFeeDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.MembershipFeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipFeeBoImpl implements MembershipFeeBO {
    MembershipFeeDAO membershipFeeDAO = new MembershipFeeDaoImpl();

    @Override
    public MembershipFeeDTO getData(String id) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.getData(id);
    }

    @Override
    public boolean saveMemFee(MembershipFeeDTO dto) throws SQLException, ClassNotFoundException {
        return membershipFeeDAO.save(dto);
    }

    @Override
    public boolean updateMemFee(MembershipFeeDTO dto) throws SQLException, ClassNotFoundException {
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
}
