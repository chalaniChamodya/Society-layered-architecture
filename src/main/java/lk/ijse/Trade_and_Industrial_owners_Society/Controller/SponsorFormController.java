package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FundingProgramBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.FundingProgramBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SponsorBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SponsorBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SponsorDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SponsorFormController {
    public AnchorPane pagingPane;
    public JFXButton btnSponsor;
    public JFXButton btnFundingProgram;
    public VBox vBox;
    public Label lblSponsorId;
    public DatePicker txtDate;
    public TextField txtSponsorName;
    public TextField txtDescription;
    public TextField txtAmount;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public ComboBox txtProgramId;

    private static SponsorFormController controller;

   SponsorBO sponsorBO = new SponsorBoImpl();
   FundingProgramBO programBO = new FundingProgramBoImpl();

    public SponsorFormController(){controller = this;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        generateNextSponsorId();
        ChangeButton.btnSelected(btnSponsor);
        setDataInProgramIdComboBox();
    }

    private void setDataInProgramIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> programId = programBO.getAllFundingProgramId();
        txtProgramId.getItems().addAll(programId);
    }

    private void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = sponsorBO.getAllSponsorId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SponsorFormController.class.getResource("/View/SponsorBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            SponsorBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateNextSponsorId() {
        try {
            lblSponsorId.setText(sponsorBO.generateNewSponsorId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnSponsorOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnSponsor);
        ChangeButton.btnUnselected(btnFundingProgram);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void btnFundingProgramOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"FundingProgramForm.fxml");
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ChangeButton.btnSelected(btnSponsor);
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
        ChangeButton.btnUnselected(btnFundingProgram);

        String sponsor_id = lblSponsorId.getText();
        String program_id = String.valueOf(txtProgramId.getValue());
        String sponsor_name = txtSponsorName.getText();
        String description = txtDescription.getText();
        String date = String.valueOf(txtDate.getValue());
        String amount = txtAmount.getText();

        SponsorDto sponsorDto = new SponsorDto(sponsor_id, program_id, sponsor_name, description, date, amount);

        boolean isSaved = sponsorBO.saveSponsor(sponsorDto);

        if(isSaved){
            clearFeilds();
            //Navigation.switchNavigation("SponsorsForm.fxml", actionEvent);
            generateNextSponsorId();
            getAllId();
        }else{
            new Alert(Alert.AlertType.ERROR,"Doesn't saved sponsor yet !");
        }
    }

    private void clearFeilds() {
        lblSponsorId.setText("");
        txtProgramId.setValue("");
        txtSponsorName.setText("");
        txtDescription.setText("");
        //dateDate.
        txtAmount.setText("");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnSponsor);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnSelected(btnCancel);
        ChangeButton.btnUnselected(btnFundingProgram);

        clearFeilds();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"FundingProgramForm.fxml");
    }
}
