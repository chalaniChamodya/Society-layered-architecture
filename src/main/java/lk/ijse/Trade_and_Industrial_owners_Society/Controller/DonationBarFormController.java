package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.DeathBenefitBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.DeathBenefitBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.ScholarshipBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SpecialScholarshipBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.ScholarshipBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SpecialScholarshipBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.ScholarshipDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SpecialScholDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.TM.DonationTm;

import java.sql.SQLException;

public class DonationBarFormController {
    private static String id;
    public Label donationId;
    public Label date;
    public Label amount;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    DeathBenefitBO deathBenefitBO = new DeathBenefitBoImpl();
    ScholarshipBO scholarshipBO = new ScholarshipBoImpl();
   SpecialScholarshipBO specialScholarshipBO = new SpecialScholarshipBoImpl();

    public static void getId(String id){
        DonationBarFormController.id = id;
    }

    public void setData(String id) {
        DonationDto donationTm = null;

        try {
            donationTm = deathBenefitBO.getData(id);
            this.donationId.setText(donationTm.getDonation_id());
            date.setText(donationTm.getDate());
            amount.setText(donationTm.getAmount());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setDataOfSchol(String id) {
        ScholarshipDto scholTm = null;

        try {
            scholTm = scholarshipBO.getData(id);
            this.donationId.setText(scholTm.getDonation_id());
            date.setText(scholTm.getDate());
            amount.setText(scholTm.getAmount());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void setSpecialScholData(String id) {
        SpecialScholDto scholTm = null;

        try {
            scholTm = specialScholarshipBO.getData(id);
            this.donationId.setText(scholTm.getSchol_id());
            date.setText(scholTm.getDate());
            amount.setText(scholTm.getAmount());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) {
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) {
        new Alert(Alert.AlertType.ERROR,"Can't Update Donation Details !").show();
    }
}
