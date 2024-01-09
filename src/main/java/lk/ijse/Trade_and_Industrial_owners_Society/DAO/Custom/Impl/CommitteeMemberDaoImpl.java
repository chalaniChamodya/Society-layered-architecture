package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.CommitteeMemberDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMember;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMemberDaoImpl implements CommitteeMemberDao {
    @Override
    public CommitteeMember getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM committee_member WHERE committee_mem_id = ?", id);

        CommitteeMember dto = new CommitteeMember();

        if(resultSet.next()){
            dto.setCom_mem_id(resultSet.getString(1));
            dto.setName(resultSet.getString(3));
            dto.setPosition(resultSet.getString(4));
        }
        return dto;
    }

    @Override
    public ArrayList<CommitteeMember> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(CommitteeMember dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO committee_member VALUES(?, ?, ?, ?, ?)",
                dto.getCom_mem_id(),
                dto.getMember_id(),
                dto.getName(),
                dto.getPosition(),
                dto.getDate()
        );
    }

    @Override
    public boolean update(CommitteeMember dto) throws SQLException, ClassNotFoundException {
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
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT committee_mem_id FROM committee_member ORDER BY committee_mem_id DESC LIMIT 1");
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
