package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;


import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.SpecialScholarshipDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SpecialScholDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.SpecialSchol;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SpecialScholarshipDaoImpl implements SpecialScholarshipDAO {
    @Override
    public SpecialSchol getData(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT * FROM special_schol WHERE schol_id = ?", id);

        SpecialSchol donationTm = new SpecialSchol();

        if(resultSet.next()){
            donationTm.setSchol_id(resultSet.getString("schol_id"));
            donationTm.setDate(resultSet.getString("date"));
            donationTm.setAmount(resultSet.getString("amount"));
        }
        return donationTm;
    }

    @Override
    public ArrayList<SpecialSchol> getAllDetail() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(SpecialSchol dto) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("INSERT INTO special_schol VALUES(?, ?, ?, ?, ?)",
                dto.getSchol_id(),
                dto.getMember_id(),
                dto.getMember_name(),
                dto.getDate(),
                dto.getAmount()
        );
    }

    @Override
    public boolean update(SpecialSchol dto) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("DELETE FROM special_schol WHERE schol_id = ?", id);
    }

    @Override
    public ResultSet generateNewId() throws SQLException, ClassNotFoundException {
        return SQLUtill.execute("SELECT schol_id FROM special_schol ORDER BY schol_id DESC LIMIT 1");

    }


    @Override
    public ArrayList<String> getAllId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = SQLUtill.execute("SELECT schol_id FROM special_schol ORDER BY LENGTH(schol_id),schol_id");

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ResultSet getAttendance() throws SQLException, ClassNotFoundException {
       return SQLUtill.execute("SELECT member_id, COUNT(*) AS total_meetings FROM general_attendance GROUP BY member_id");
    }
}
