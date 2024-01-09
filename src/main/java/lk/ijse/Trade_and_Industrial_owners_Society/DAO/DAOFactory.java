package lk.ijse.Trade_and_Industrial_owners_Society.DAO;

import lk.ijse.Trade_and_Industrial_owners_Society.DAO.Custom.Impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory(){
    }

    public static DAOFactory getDaoFactory(){
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes{
        MEMBER, COMMITTEE_MEMBER, FAMILY_MEMBER, GENERAL_MEETING, COMMITTEE_MEETING, SUBSCRIPTION_FEE, MEMBERSHIP_FEE, FUNDING_PROGRAM, SPONSOR, DEATH_BENEFIT, SCHOLARSHIP, SPECIAL_SCHOLARSHIP, QUERY
    }

    public SuperDAO getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case MEMBER:
                return new MemberDaoImpl();
            case COMMITTEE_MEMBER:
                return new CommitteeMemberDaoImpl();
            case FAMILY_MEMBER:
                return new FamilyMemberDaoImpl();
            case GENERAL_MEETING:
                return new GeneralMeetingDaoImpl();
            case COMMITTEE_MEETING:
                return new CommitteeMeetingDaoImpl();
            case SUBSCRIPTION_FEE:
                return new SubscriptionFeeDaoImpl();
            case MEMBERSHIP_FEE:
                return new MembershipFeeDaoImpl();
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
