package lk.ijse.Trade_and_Industrial_owners_Society.BO;

import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory(){}

    public static BOFactory getBoFactory(){
        return (boFactory == null) ? boFactory = new BOFactory() :boFactory;
    }

    public enum BOTypes{
        MEMBER, COMMITTEE_MEMBER, FAMILY_MEMBER, GENERAL_MEETING, COMMITTEE_MEETING, SUBSCRIPTION_FEE, MEMBERSHIP_FEE,DEATH_DONATION, SCHOLARSHIP, SPECIAL_SCHOLARSHIP, QUERY
    }

    public SuperBO getTypes(BOTypes boTypes){
        switch (boTypes){
            case MEMBER:
                return new MemberBoImpl();
            case COMMITTEE_MEMBER:
                return new CommitteeMemberBoImpl();
            case FAMILY_MEMBER:
                return new FamilyMemberBoImpl();
            case GENERAL_MEETING:
                return new GeneralMeetingBoImpl();
            case COMMITTEE_MEETING:
                return new CommitteeMeetingBoImpl();
            case SUBSCRIPTION_FEE:
                return new SubscriptionFeeBoImpl();
            case MEMBERSHIP_FEE:
                return new MembershipFeeBoImpl();
            case DEATH_DONATION:
                return null;
            case SCHOLARSHIP:
                return new ScholarshipBoImpl();
            case SPECIAL_SCHOLARSHIP:
                return new SpecialScholarshipBoImpl();
            case QUERY:
                return null;
            default:
                return null;
        }
    }
}
