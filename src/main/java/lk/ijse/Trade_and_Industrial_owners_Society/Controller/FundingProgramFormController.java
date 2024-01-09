package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FundingProgramBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.FundingProgramBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FundingProgramDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class FundingProgramFormController {
    public AnchorPane pagingPane;
    public JFXButton btnSponsor;
    public JFXButton btnFundingProgram;
    public VBox vBox;
    public Label lblProgramId;
    public TextField txtProgramName;
    public DatePicker dateDate;
    public TextField txtLocation;
    public TextField txtIncome;
    public TextField txtExpenditure;
    public TextField txtDescription;
    public JFXButton btnAdd;
    public JFXButton btnCancel;

    private static  FundingProgramFormController controller;

    FundingProgramBO programBO = new FundingProgramBoImpl();

    public FundingProgramFormController(){controller = this;}

    public static  FundingProgramFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        generateNextFundingProgramId();
        ChangeButton.btnSelected(btnFundingProgram);
    }

    public void generateNextFundingProgramId() {
        try {
            lblProgramId.setText(programBO.generateNewFundingProgramId());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = programBO.getAllFundingProgramId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(FundingProgramFormController.class.getResource("/View/FundingProgramBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            FundingProgramBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSponsorOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"SponsorForm.fxml");
    }

    public void btnFundingProgramOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnFundingProgram);
        ChangeButton.btnUnselected(btnSponsor);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
        ChangeButton.btnSelected(btnFundingProgram);
        ChangeButton.btnUnselected(btnSponsor);

        String program_id = lblProgramId.getText();
        String program_name = txtProgramName.getText();
        String description = txtDescription.getText();
        String date = String.valueOf(dateDate.getValue());
        String location = txtLocation.getText();
        String income = txtIncome.getText();
        String expenditure = txtExpenditure.getText();

        FundingProgramDto dto = new FundingProgramDto(program_id, program_name, description, date, location, income, expenditure);

        boolean isSaved = programBO.saveFundingProgram(dto);

        if(isSaved){
            clearFields();
            Navigation.switchPaging(pagingPane,"FundingProgramForm.fxml");
        }else{
            new Alert(Alert.AlertType.ERROR, "Doesn't saved yet !");
        }
    }

    private void clearFields() {
        txtProgramName.setText("");
        txtDescription.setText("");
        txtIncome.setText("");
        txtExpenditure.setText("");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnCancel);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnSelected(btnFundingProgram);
        ChangeButton.btnUnselected(btnSponsor);

        clearFields();
    }
}
