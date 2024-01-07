package lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom;

import lk.ijse.TradeAndIndustryOwners.DAO.CrudDao;
import lk.ijse.TradeAndIndustryOwners.DTO.SpecialScholDTO;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.CrudDao;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SpecialScholDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Map;

public interface SpecialScholarshipDAO extends CrudDao<SpecialScholDto> {
    Map<String, LocalDate> calculateMemberDuration() throws SQLException, ClassNotFoundException;
    ResultSet getAttendance() throws SQLException, ClassNotFoundException;
    Map<String,Double> calculateMeetingAttendance() throws SQLException, ClassNotFoundException;
}
