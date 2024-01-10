package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Entity.CommitteeMeeting;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface CommitteeMeetingDAO extends CrudDao<CommitteeMeeting> {
    ResultSet getUpComingMeetingId() throws SQLException, ClassNotFoundException;
}
