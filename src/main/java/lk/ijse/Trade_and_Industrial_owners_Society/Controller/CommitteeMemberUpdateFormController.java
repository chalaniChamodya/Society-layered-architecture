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
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.CommitteeMemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class CommitteeMemberUpdateFormController {
    private static String id;
    public AnchorPane pagingPane;
    public JFXButton btnMember;
    public JFXButton btnFamily;
    public JFXButton btnCommittee;
    public VBox vBox;
    public AnchorPane crudPane;
    public Label lblComMemId;
    public DatePicker dateDate;
    public ComboBox cmbPosition;
    public ComboBox cmbMemberId;
    public Label lblName;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;

    private static CommitteeMemberUpdateFormController controller;

    CommitteeMemberBO committeeMemberBO = new CommitteeMemberBoImpl();

    public CommitteeMemberUpdateFormController(){controller = this;}

    public static CommitteeMemberUpdateFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        setData();
        getAllId();
        ChangeButton.btnSelected(btnCommittee);
        setDataInComboBox();
    }

    private void getAllId() throws SQLException, ClassNotFoundException {
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

    private void setData() {
        CommitteeMemberDto memberDto = null;

        try {
            memberDto = committeeMemberBO.getData(id);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        lblComMemId.setText(id);
        cmbMemberId.setValue(memberDto.getMember_id());
        lblName.setText(memberDto.getName());
        cmbPosition.setValue(memberDto.getPosition());
        dateDate.setValue(LocalDate.parse(memberDto.getDate()));
    }

    public static void getId(String id){
        CommitteeMemberUpdateFormController.id = id;
    }

    public void setCommitteeMemberId(){
        lblComMemId.setText(id);
    }

    public void btnMemberOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"MembersForm.fxml");
    }

    public void btnFamilyOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"FamilyMemberForm.fxml");
    }

    public void btnCommitteeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"CommitteeMemberForm.fxml");
    }

    public void cmbMemberIdOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        CommitteeMemberDto memberDto = new CommitteeMemberDto();
        memberDto.setCom_mem_id(id);
        memberDto.setMember_id((String) cmbMemberId.getValue());
        memberDto.setName(lblName.getText());
        memberDto.setDate(String.valueOf(dateDate.getValue()));
        memberDto.setPosition(String.valueOf(cmbPosition.getSelectionModel().getSelectedItem()));

        boolean isUpdated = committeeMemberBO.updateCommitteeMember(memberDto);

        if(isUpdated){
            new Alert(Alert.AlertType.CONFIRMATION," Committee Member Updated !").show();
            Navigation.switchPaging(pagingPane,"CommitteeMemberForm.fxml");
            CommitteeMemberFormController.getInstance().getAllId();
        }else{
            new Alert(Alert.AlertType.ERROR," Committee Member doesn't Updated !").show();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"CommitteeMemberForm.fxml");
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"CommitteeMemberForm.fxml");
    }
}
