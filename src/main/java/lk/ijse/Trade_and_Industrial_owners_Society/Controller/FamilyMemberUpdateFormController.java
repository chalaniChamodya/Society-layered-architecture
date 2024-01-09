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
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class FamilyMemberUpdateFormController {
    private static String id;
    public AnchorPane pagingPane;
    public JFXButton btnFamily;
    public JFXButton btnMember;
    public JFXButton btnCommittee;
    public VBox vBox;
    public Label lblFamMemId;
    public DatePicker dateDateOfBirth;
    public TextField txtName;
    public TextField txtOccupation;
    public Label lblIsAlive;
    public ComboBox cmbMemberId;
    public ComboBox cmbRelationship;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;
    private static FamilyMemberUpdateFormController controller;

    FamilyMemberBO familyMemberBO = new FamilyMemberBoImpl();
    MemberBO memberBO = new MemberBoImpl();

    public FamilyMemberUpdateFormController(){controller = this;}

    public static FamilyMemberUpdateFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        setData();
        setFamilyMemberId();
        getAllId();
        setDataInRelationComboBox();
        setDataInMemberIdComboBox();
        lblIsAlive.setText("Yes");
    }

    private void setDataInMemberIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> memberId = memberBO.getAllMemberId();
        cmbMemberId.getItems().addAll(memberId);
    }

    public void setDataInRelationComboBox(){
        ArrayList<String> relationships = new ArrayList<>();
        relationships.add("Wife/Husband");
        relationships.add("Son");
        relationships.add("Daughter");
        relationships.add("Father");
        relationships.add("Mother");
        cmbRelationship.getItems().addAll(relationships);
    }

    public static void getId(String id) {
        FamilyMemberUpdateFormController.id = id;
    }

    public void  setFamilyMemberId(){
        lblFamMemId.setText(id);
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = familyMemberBO.getAllFamilyMemberId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(FamilyMemberUpdateFormController.class.getResource("/View/FamilyMemberBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            FamilyMemberBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setData() {
        FamilyMemberDto memberDto = null;

        try {
            memberDto = familyMemberBO.getAllFamilyMemberData(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        lblFamMemId.setText(id);
        lblFamMemId.setText(memberDto.getFamily_mem_id());
        cmbMemberId.setValue(memberDto.getMember_id());
        txtName.setText(memberDto.getName());
        dateDateOfBirth.setValue(LocalDate.parse(memberDto.getDate_of_birth()));
        txtOccupation.setText(memberDto.getOccupation());
        cmbRelationship.setValue(memberDto.getRelationship());
        lblIsAlive.setText(memberDto.getIsAlive());
    }

    public void btnFamilyOnAction(ActionEvent actionEvent) {
    }

    public void btnMemberOnAction(ActionEvent actionEvent) {
    }

    public void btnCommitteeOnAction(ActionEvent actionEvent) {
    }

    public void cmbMemberIdOnAction(ActionEvent actionEvent) {
    }

    public void cmbRrelationshipOnAction(ActionEvent actionEvent) {
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        FamilyMemberDto memberDto = new FamilyMemberDto();
        memberDto.setFamily_mem_id(id);
        memberDto.setMember_id(String.valueOf(cmbMemberId.getValue()));
        memberDto.setIsAlive(lblIsAlive.getText());
        memberDto.setName(txtName.getText());
        memberDto.setRelationship(String.valueOf(cmbRelationship.getSelectionModel().getSelectedItem()));
        memberDto.setOccupation(txtOccupation.getText());

        boolean isUpdated = familyMemberBO.updateFamilyMember(memberDto);

        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION," Family Member Updated !").show();
            Navigation.switchPaging(pagingPane,"FamilyMemberForm.fxml");
            FamilyMemberFormController.getInstance().getAllId();
        }else{
            new Alert(Alert.AlertType.ERROR," Family Member doesn't Updated !").show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"FamilyMemberForm.fxml");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"FamilyMemberForm.fxml");
    }
}
