package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.DeathBenefitBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FamilyMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.DeathBenefitBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.FamilyMemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.DonationDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import static lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton.btnSelected;
import static lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton.btnUnselected;

public class DeathBenefitFormController {
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
    private static DeathBenefitFormController controller;

    DeathBenefitBO deathBenefitBO = new DeathBenefitBoImpl();
    FamilyMemberBO familyMemberBO = new FamilyMemberBoImpl();

    public DeathBenefitFormController(){controller = this;}

    public static DeathBenefitFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        generateNextDeathBenefitId();
        btnSelected(btnDeathBenefit);
        setdataInFamMemId();
    }

    private void setdataInFamMemId() throws SQLException, ClassNotFoundException {
        ArrayList<String> famMemberId = familyMemberBO.getAllFamilyMemberId();
        txtFamilyMemberId.getItems().addAll(famMemberId);
    }

    private void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = deathBenefitBO.getAllId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(DeathBenefitFormController.class.getResource("/View/DonationBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            DonationBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateNextDeathBenefitId() {
        try {
            lblBenefitId.setText(deathBenefitBO.generateNewId());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnSpecialScholOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"SpecialScholarshipForm.fxml");
    }

    public void btnScholarshipOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"ScholarshipForm.fxml");
    }

    public void btnDeathBenefitOnAction(ActionEvent actionEvent) {
        btnSelected(btnDeathBenefit);
        btnUnselected(btnScholarship);
        btnUnselected(btnSpecialSchol);
        btnUnselected(btnAdd);
        btnUnselected(btnCancel);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        btnSelected(btnDeathBenefit);
        btnUnselected(btnScholarship);
        btnUnselected(btnSpecialSchol);
        btnSelected(btnAdd);
        btnUnselected(btnCancel);

        String Death_benefit_id = lblBenefitId.getText();
        String date = String.valueOf(dateDate.getValue());
        String amount = txtAmount.getText();
        String family_member_id = String.valueOf(txtFamilyMemberId.getValue());

        DonationDto donationDto = new DonationDto(Death_benefit_id, date, amount,family_member_id);
        try {
            boolean isSaved = deathBenefitBO.saveTransaction(donationDto);
            if(isSaved){
                clearFields();
                generateNextDeathBenefitId();
                getAllId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't Saved !").show();
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }

    private void clearFields() {
        txtFamilyMemberId.setValue("");
        txtAmount.setText("");
        dateDate.setValue(LocalDate.parse(""));
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnSelected(btnDeathBenefit);
        btnUnselected(btnScholarship);
        btnUnselected(btnSpecialSchol);
        btnUnselected(btnAdd);
        btnSelected(btnCancel);

        clearFields();
    }
}
