package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SponsorBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SponsorBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SponsorDto;

import java.sql.SQLException;

public class SponsorBarFormController {
    public Label SponsorId;
    public Label programId;
    public Label sponsorName;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    SponsorBO sponsorBO = new SponsorBoImpl();

    public void setData(String id) {
        SponsorDto sponsorTm = null;
        try {
            sponsorTm = sponsorBO.getData(id);
            this.SponsorId.setText(sponsorTm.getSponsor_id());
            programId.setText(sponsorTm.getProgram_id());
            sponsorName.setText(sponsorTm.getSponsor_name());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) {
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) {
    }
}
