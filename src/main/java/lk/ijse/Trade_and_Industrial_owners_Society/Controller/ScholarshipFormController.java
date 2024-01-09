package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FamilyMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.FamilyMemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.ScholarshipBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.ScholarshipBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.ScholarshipDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ScholarshipFormController {
    public AnchorPane pagingPane;
    public JFXButton btnSpecialSchol;
    public JFXButton btnScholarship;
    public JFXButton btnDeathBenefit;
    public VBox vBox;
    public Label lblBenefitId;
    public DatePicker dateDate;
    public TextField txtAmount;
    public ComboBox txtFamilyMemberId;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    private static ScholarshipFormController controller;

    ScholarshipBO scholarshipBO = new ScholarshipBoImpl();
    FamilyMemberBO familyMemberBO = new FamilyMemberBoImpl();

    public ScholarshipFormController(){controller = this;}

    public static ScholarshipFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        generateNextScholarshipId();
        ChangeButton.btnSelected(btnScholarship);
        setDataInFamilyMemComboBox();
    }

    private void setDataInFamilyMemComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> famMemberId = familyMemberBO.getAllFamilyMemberId();
        txtFamilyMemberId.getItems().addAll(famMemberId);
    }

    private void generateNextScholarshipId() throws SQLException, ClassNotFoundException {
        lblBenefitId.setText(scholarshipBO.generateNewScolId());
    }

    private void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = scholarshipBO.getAllScholId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(ScholarshipFormController.class.getResource("/View/DonationBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            DonationBarFormController controller = loader.getController();
            controller.setDataOfSchol(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnSpecialScholOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"SpecialScholarshipForm.fxml");
    }

    public void btnScholarshipOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnScholarship);
        ChangeButton.btnUnselected(btnDeathBenefit);
        ChangeButton.btnUnselected(btnSpecialSchol);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void btnDeathBenefitOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"DeathBenefitForm.fxml");
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnSelected(btnScholarship);
        ChangeButton.btnUnselected(btnDeathBenefit);
        ChangeButton.btnUnselected(btnCancel);
        ChangeButton.btnUnselected(btnSpecialSchol);

        String scholId = lblBenefitId.getText();
        String date = String.valueOf(dateDate.getValue());
        String amonut = txtAmount.getText();
        String fam_mem_id = String.valueOf(txtFamilyMemberId.getValue());

        ScholarshipDto scholarshipDto = new ScholarshipDto(scholId, date, amonut, fam_mem_id);

        try {
            boolean isSaved = scholarshipBO.saveSchol(scholarshipDto);
            if(isSaved){
                clearFields();
                generateNextScholarshipId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't Saved !").show();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void clearFields() {
        dateDate.setValue(LocalDate.parse(""));
        txtFamilyMemberId.setValue("");
        txtAmount.setText("");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnSelected(btnScholarship);
        ChangeButton.btnUnselected(btnDeathBenefit);
        ChangeButton.btnSelected(btnCancel);
        ChangeButton.btnUnselected(btnSpecialSchol);

        clearFields();
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"DeathBenefitForm.fxml");
    }
}
