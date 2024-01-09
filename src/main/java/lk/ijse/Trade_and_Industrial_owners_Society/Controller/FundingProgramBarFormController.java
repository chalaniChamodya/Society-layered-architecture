package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FundingProgramBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.FundingProgramBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FundingProgramDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class FundingProgramBarFormController {
    private static String id;
    public Label programId;
    public Label programName;
    public Label date;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    FundingProgramBO fundingProgramBO = new FundingProgramBoImpl();

    public static void getId(String id){
        FundingProgramBarFormController.id = id;
    }

    public void setData(String id) {
        FundingProgramDto programTm = null;
        try {
            programTm = fundingProgramBO.getData(id);
            this.programId.setText(programTm.getProgram_id());
            programName.setText(programTm.getProgram_name());
            date.setText(programTm.getDate());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = programId.getText();
        boolean isDeleted = fundingProgramBO.deleteFundingProgram(id);
        if(isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Program Deleted !").show();
            FundingProgramFormController.getInstance().getAllId();
            FundingProgramFormController.getInstance().generateNextFundingProgramId();
        }
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws IOException {
        FundingProgramUpdateFormController.getId(programId.getText());
        Navigation.barPane("FundingProgramUpdateForm.fxml");
    }
}
