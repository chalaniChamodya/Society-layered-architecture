package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.BOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.CommitteeMemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CommitteeMemberFormController {
    public AnchorPane pagingPane;
    public JFXButton btnMember;
    public JFXButton btnFamily;
    public JFXButton btnCommittee;
    public VBox vBox;
    public Label lblComMemId;
    public DatePicker dateDate;
    public ComboBox cmbPosition;
    public ComboBox cmbMemberId;
    public Label lblName;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public AnchorPane crudPane;
    private static CommitteeMemberFormController controller;

    CommitteeMemberBO committeeMemberBO = (CommitteeMemberBO) BOFactory.getBoFactory().getTypes(BOFactory.BOTypes.COMMITTEE_MEMBER);
    MemberBO memberBO = (MemberBO) BOFactory.getBoFactory().getTypes(BOFactory.BOTypes.MEMBER);

    public CommitteeMemberFormController(){controller = this;}

    public static CommitteeMemberFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        generateNextCommitteeMemberId();
        setDataInComboBox();
        setDataInMemberIdComboBox();
        ChangeButton.btnSelected(btnCommittee);
    }

    private void setDataInMemberIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> memberId = memberBO.getAllMemberId();
        cmbMemberId.getItems().addAll(memberId);
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = committeeMemberBO.getAllCommitteeMemberId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(CommitteeMemberFormController.class.getResource("/View/CommitteeMemberBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            CommitteeMemberBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateNextCommitteeMemberId() throws SQLException, ClassNotFoundException {
        lblComMemId.setText(committeeMemberBO.generateNewCommitteeMemberId());
    }

    public void setDataInComboBox(){
        ArrayList<String> positions = new ArrayList<>();
        positions.add("Chairman");
        positions.add("Vice Chairman");
        positions.add("Secretary");
        positions.add("Vice Secretary");
        positions.add("Treasurer");
        positions.add("Vice Treasurer");
        positions.add("Normal Committee Member");
        cmbPosition.getItems().addAll(positions);
    }

    public String getPosition(){return String.valueOf(cmbPosition.getSelectionModel().getSelectedItem());}

    public void btnMemberOnAction(ActionEvent actionEvent) throws IOException {
        ChangeButton.btnSelected(btnMember);
        Navigation.switchPaging(pagingPane,"MembersForm.fxml");
    }

    public void btnFamilyOnAction(ActionEvent actionEvent) throws IOException {
        ChangeButton.btnSelected(btnFamily);
        Navigation.switchPaging(pagingPane,"FamilyMemberForm.fxml");
    }

    public void btnCommitteeOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnCommittee);
        ChangeButton.btnUnselected(btnMember);
        ChangeButton.btnUnselected(btnFamily);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void cmbMemberIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        lblName.setText(memberBO.getMemberName(String.valueOf(cmbMemberId.getValue())));
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnSelected(btnCommittee);
        ChangeButton.btnUnselected(btnMember);
        ChangeButton.btnUnselected(btnCancel);
        ChangeButton.btnUnselected(btnFamily);

        String com_mem_id = lblComMemId.getText();
        String member_id = cmbMemberId.getId();
        String name = lblName.getText();
        String position = getPosition();
        String date = String.valueOf(dateDate.getValue());

        CommitteeMemberDto committeeMemberDto = new CommitteeMemberDto(com_mem_id,member_id, name, position, date);

        boolean isSaved = committeeMemberBO.saveCommitteeMember(committeeMemberDto);

        if(isSaved){
            clearFeilds();
            generateNextCommitteeMemberId();
            getAllId();
        }else{
            new Alert(Alert.AlertType.ERROR,"Committee Member Doesn't Saved !");
        }
    }

    private void clearFeilds() {
       // txtMemberId.setText("");
       // txtName.setText("");
        cmbPosition.setValue("");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnCancel);
        ChangeButton.btnSelected(btnCommittee);
        ChangeButton.btnUnselected(btnMember);
        ChangeButton.btnUnselected(btnFamily);
        ChangeButton.btnUnselected(btnAdd);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"MembersForm.fxml");
    }
}
