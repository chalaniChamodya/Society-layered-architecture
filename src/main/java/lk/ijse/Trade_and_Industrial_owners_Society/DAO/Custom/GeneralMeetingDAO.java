package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;

import java.sql.SQLException;

public interface GeneralMeetingDAO extends CrudDao<GeneralMeetingDto> {
    String getTodayGeneralMeetingId() throws SQLException, ClassNotFoundException;
}
