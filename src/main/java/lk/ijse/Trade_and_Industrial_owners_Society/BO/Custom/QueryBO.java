package lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.SuperBO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface QueryBO extends SuperBO {
    ArrayList<String> getCommitteeMemberMailAddress() throws SQLException, ClassNotFoundException;
}
