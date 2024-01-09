package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.QueryBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.QueryDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.QueryDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBoImpl implements QueryBO {
    QueryDAO queryDAO = new QueryDaoImpl();

    @Override
    public ArrayList<String> getCommitteeMemberMailAddress() throws SQLException, ClassNotFoundException {
        return queryDAO.getCommitteeMemberMailAddress();
    }
}
