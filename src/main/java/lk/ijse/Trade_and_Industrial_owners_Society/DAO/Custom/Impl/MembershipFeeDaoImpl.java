package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.MembershipFeeDAO;
import lk.ijse.TradeAndIndustryOwners.DTO.MembershipFeeDTO;
import lk.ijse.TradeAndIndustryOwners.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipFeeDaoImpl implements MembershipFeeDAO {

    @Override
    public MembershipFeeDTO getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM member_fee WHERE member_fee_id = ?", id);

        MembershipFeeDTO dto = new MembershipFeeDTO();

        if (resultSet.next()) {
            dto.setMember_fee_id(resultSet.getString(1));
            dto.setMember_name(resultSet.getString(3));
            dto.setDate(resultSet.getString(4));
        }
        return dto;
    }

    @Override
    public ArrayList<MembershipFeeDTO> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(MembershipFeeDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO member_fee VALUES (?,?,?,?,?)",
                dto.getMember_fee_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(MembershipFeeDTO dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("UPDATE member_fee SET " +
                "member_id = ?, " +
                "member_name = ?, " +
                "date = ?, " +
                "amount = ? " +
                "WHERE member_fee_id = ?",
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount(),
                dto.getMember_fee_id()
        );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM member_fee WHERE member_fee_id = ?",id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT member_fee_id FROM member_fee ORDER BY member_fee_id DESC LIMIT 1");

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
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT member_fee_id FROM member_fee ORDER BY LENGTH(member_fee_id),member_fee_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
