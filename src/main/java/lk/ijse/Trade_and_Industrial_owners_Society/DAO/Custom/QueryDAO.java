package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.SuperDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryDAO extends SuperDAO {
    ArrayList<String> getCommitteeMemberMailAddress() throws SQLException, ClassNotFoundException;
    ObservableList<PieChart.Data> getFundDataForPieChart() throws SQLException;
    ObservableList<XYChart.Data<String, Number>> monthlyFund() throws SQLException;
    ObservableList<XYChart.Data<String, Number>> monthlyExpense() throws SQLException;
}
