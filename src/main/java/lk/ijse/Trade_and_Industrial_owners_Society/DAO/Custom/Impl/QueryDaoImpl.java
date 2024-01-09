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
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
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

    @Override
    public ArrayList<String> getAllEmailAddress() throws SQLException, ClassNotFoundException {
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String date = currentMonth.format(formatter);
        ResultSet resultSet = SQLUtill.execute("SELECT m.email FROM member m LEFT JOIN subscription_fee sf ON m.member_id = sf.member_id WHERE sf.date IS NULL OR DATE_FORMAT(sf.date,'%Y-%m') != ?", date);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllMemberEmailAddress() throws SQLException, ClassNotFoundException {
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String date = currentMonth.format(formatter);
        ResultSet resultSet = SQLUtill.execute("SELECT m.email FROM member m LEFT JOIN member_fee mf ON m.member_id = mf.member_id WHERE mf.date IS NULL OR DATE_FORMAT(mf.date,'%Y-%m') != ?", date);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllUnpaidMembershipFeeId() throws SQLException, ClassNotFoundException {
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String date = currentMonth.format(formatter);

        ResultSet resultSet = SQLUtill.execute("SELECT m.member_id FROM member m LEFT JOIN member_fee sf ON m.member_id = sf.member_id WHERE sf.date IS NULL OR DATE_FORMAT(sf.date,'%Y-%m') != ?", date);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }

    @Override
    public ArrayList<String> getAllUnpaidSubscriptionFeeId() throws SQLException, ClassNotFoundException {
        YearMonth currentMonth = YearMonth.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");

        String date = currentMonth.format(formatter);

        ResultSet resultSet = SQLUtill.execute("SELECT m.member_id FROM member m LEFT JOIN subscription_fee sf ON m.member_id = sf.member_id WHERE sf.date IS NULL OR DATE_FORMAT(sf.date,'%Y-%m') != ?", date);

        ArrayList<String> list = new ArrayList<>();

        while (resultSet.next()){
            list.add(resultSet.getString(1));
        }
        return list;
    }
}
