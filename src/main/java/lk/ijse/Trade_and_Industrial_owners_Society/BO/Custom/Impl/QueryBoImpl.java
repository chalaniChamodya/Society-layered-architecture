package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.QueryBO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.QueryDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.QueryDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.DAOFactory;

import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBoImpl implements QueryBO {
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public ArrayList<String> getCommitteeMemberMailAddress() throws SQLException, ClassNotFoundException {
        return queryDAO.getCommitteeMemberMailAddress();
    }

    @Override
    public ObservableList<PieChart.Data> getFundDataForPieChart() throws SQLException, ClassNotFoundException {
        return queryDAO.getFundDataForPieChart();
    }
}
