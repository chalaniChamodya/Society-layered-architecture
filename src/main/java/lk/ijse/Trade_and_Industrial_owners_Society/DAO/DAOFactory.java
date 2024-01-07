package lk.ijse.Trade_and_Industrial_owners_Society.DAO;

import lk.ijse.TradeAndIndustryOwners.DAO.Custom.Impl.*;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.CommitteeMeetingDaoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.MemberDaoImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        MEMBER, COMMITTEE_MEMBER, FAMILY_MEMBER, GENERAL_MEETING, COMMITTEE_MEETING, FUNDING_PROGRAM, SPONSOR, DEATH_BENEFIT, SCHOLARSHIP, SPECIAL_SCHOLARSHIP, QUERY
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case MEMBER:
                return new MemberDaoImpl();
            case COMMITTEE_MEMBER:
                return new CommitteeMeetingDaoImpl();
            case FAMILY_MEMBER:
                return new FamilyMemberDaoImpl();
            case GENERAL_MEETING:
                return new GeneralMeetingDaoImpl();
            case COMMITTEE_MEETING:
                return new CommitteeMeetingDaoImpl();
            case FUNDING_PROGRAM:
                return new FundingProgramDaoImpl();
            case SPONSOR:
                return new SponsorDaoImpl();
            case DEATH_BENEFIT:
                return new DeathBenefitDaoImpl();
            case SCHOLARSHIP:
                return new ScholarshipDaoImpl();
            case SPECIAL_SCHOLARSHIP:
                return new SpecialScholarshipDaoImpl();
            case QUERY:
                return new QueryDaoImpl();
        }
        return null;
    }
}
