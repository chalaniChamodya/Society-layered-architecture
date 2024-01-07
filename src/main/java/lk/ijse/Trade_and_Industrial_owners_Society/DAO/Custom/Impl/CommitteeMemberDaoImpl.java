package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMemberDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMemberDaoImpl implements CommitteeMemberDao {
    @Override
    public CommitteeMemberDto getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM committee_member WHERE committee_mem_id = ?", id);

        CommitteeMemberDto dto = new CommitteeMemberDto();

        if(resultSet.next()){
            dto.setCom_mem_id(resultSet.getString(1));
            dto.setName(resultSet.getString(3));
            dto.setPosition(resultSet.getString(4));
        }
        return dto;
    }

    @Override
    public ArrayList<CommitteeMemberDto> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CommitteeMemberDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO committee_member VALUES(?, ?, ?, ?, ?)",
                dto.getCom_mem_id(),
                dto.getMember_id(),
                dto.getName(),
                dto.getPosition(),
                dto.getDate()
        );
    }

    @Override
    public boolean update(CommitteeMemberDto dto) throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("UPDATE committee_member SET " +
               "member_id =?, " +
               "name = ?, " +
               "position = ?, " +
               "date = ? " +
               "WHERE committee_mem_id =?",
               dto.getMember_id(),
               dto.getName(),
               dto.getPosition(),
               dto.getDate(),
               dto.getCom_mem_id()
       );
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM commitee_member WHERE committee_mem_id = ?", id);
    }

    @Override
    public String generateNewId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT committee_mem_id FROM committee_member ORDER BY committee_mem_id DESC LIMIT 1");

        String currentCommitteeMemberId = null;

        if(resultSet.next()){
            currentCommitteeMemberId = resultSet.getString(1);
            return splitCommitteeMemberId(currentCommitteeMemberId);
        }
        return splitCommitteeMemberId(null);
    }

    private String splitCommitteeMemberId(String currentCommitteeMemberId) {
        if(currentCommitteeMemberId != null){
            String[] split = currentCommitteeMemberId.split("C");
            int id = Integer.parseInt(split[1]);
            if(id < 10){
                id++;
                return "C00" + id;
            }else if(id < 100){
                id++;
                return "C0" + id;
            }else{
                id++;
                return "C"+id;
            }
        }
        return "C001";
    }

    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT committee_mem_id FROM committee_member ORDER BY LENGTH(committee_mem_id),committee_mem_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
