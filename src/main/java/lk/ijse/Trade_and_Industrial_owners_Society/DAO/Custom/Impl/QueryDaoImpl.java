package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.QueryDAO;
import lk.ijse.Trade_and_Industrial_owners_Society.DbConnection.DBConnection;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.SQLUtill;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryDaoImpl implements QueryDAO {
    @Override
    public ArrayList<String> getCommitteeMemberMailAddress() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = new ArrayList<>();

        ResultSet resultSet = SQLUtill.execute("SELECT m.email FROM committee_member cm JOIN member m ON cm.member_id = m.member_id");

        if(resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ObservableList<PieChart.Data> getFundDataForPieChart() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<XYChart.Data<String, Number>> monthlyFund() throws SQLException {
        return null;
    }

    @Override
    public ObservableList<XYChart.Data<String, Number>> monthlyExpense() throws SQLException {
        return null;
    }
}
