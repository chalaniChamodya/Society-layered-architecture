package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MembershipFeeBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SubscriptionFeeBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MembershipFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SubscriptionFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SubscriptionFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class SubscriptionFeeUpdateFormController {
    private static String id;
    public AnchorPane pagingPane;
    public JFXButton btnMembership;
    public JFXButton btnSubscription;
    public VBox vBox;
    public DatePicker dateDate;
    public ComboBox cmbMemberId;
    public Label lblName;
    public JFXButton btnUpdate;
    public JFXButton btnCancel;
    public ComboBox cmbDueType;
    public Label lblDueID;
    public TextField txtAmount;
    public static SubscriptionFeeUpdateFormController controller;
    public  String dueId;
    public String dueType;
    public String name;

    MembershipFeeBO membershipFeeBO = new MembershipFeeBoImpl();
    SubscriptionFeeBO subscriptionFeeBO = new SubscriptionFeeBoImpl();
    MemberBO memberBO = new MemberBoImpl();

    public SubscriptionFeeUpdateFormController(){controller = this;}

    public static SubscriptionFeeUpdateFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        //btnSelected(btnSubscription);
        setDataInMemberIdComboBox();
        getSubFeeId();
        setData();
    }

    private void setData() {
        if(id.startsWith("SF")){
            dueType = "Subscription Fee";
            SubscriptionFeeDto feeDto = null;
            try {
                feeDto = subscriptionFeeBO.getData(id);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            lblDueID.setText(feeDto.getSubscription_fee_id());
            System.out.println(feeDto.getSubscription_fee_id());
            cmbDueType.setValue(dueType);
            cmbMemberId.setValue(feeDto.getMember_id());
            lblName.setText(feeDto.getMember_name());
            txtAmount.setText(feeDto.getAmount());
            dateDate.setValue(LocalDate.parse(feeDto.getDate()));
        }else if(id.startsWith("MF")){
            dueType = "Membership Fee";
            MembershipFeeDto feeDto = null;
            try {
                feeDto = membershipFeeBO.getData(id);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
            lblDueID.setText(feeDto.getMember_fee_id());
            cmbDueType.setValue(dueType);
            cmbMemberId.setValue(feeDto.getMember_id());
            lblName.setText(feeDto.getMember_name());
            txtAmount.setText(feeDto.getAmount());
            dateDate.setValue(LocalDate.parse(feeDto.getDate()));
        }
    }

    private void getSubFeeId() {
        lblDueID.setText(id);
    }

    public static void getId(String id) {
        SubscriptionFeeUpdateFormController.id = id;
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        if(id.startsWith("SF")){
            list = subscriptionFeeBO.getAllSubFeeId();

            vBox.getChildren().clear();
            for(int i = 0; i< list.size(); i++){
                loadTableData(list.get(i));
            }
        }else if(id.startsWith("MF")){
            list = membershipFeeBO.getAllMemFeeId();

            vBox.getChildren().clear();
            for(int i = 0; i< list.size(); i++){
                loadTableData(list.get(i));
            }
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SubscriptionFeeUpdateFormController.class.getResource("/View/MembershipDueBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            MembershipDueBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setDataInMemberIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> memberId = memberBO.getAllMemberId();
        cmbMemberId.getItems().addAll(memberId);
    }

    public void btnMembershipOnAction(ActionEvent actionEvent) {
    }

    public void btnSubscriptionOnAction(ActionEvent actionEvent) {
    }

    public void cmbMemberIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        name = memberBO.getMemberName(String.valueOf(cmbMemberId.getValue()));
        lblName.setText(name);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, IOException, ClassNotFoundException {
        if(id.startsWith("SF")){
            SubscriptionFeeDto feeDto = new SubscriptionFeeDto();

            feeDto.setSubscription_fee_id(id);
            feeDto.setMember_id(String.valueOf(cmbMemberId.getValue()));
            feeDto.setMember_name(lblName.getText());
            feeDto.setDate(String.valueOf(dateDate.getValue()));
            feeDto.setAmount(txtAmount.getText());

            boolean isUpdated = subscriptionFeeBO.updateSubFee(feeDto);

            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Fee Updated !").show();
                Navigation.switchPaging(pagingPane,"SubscriptionFeeForm.fxml");
                MembersFormController.getInstance().getAllId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fee doesn't Updated !").show();
            }
        }else if(id.startsWith("MF")){
            MembershipFeeDto feeDto = new MembershipFeeDto();
            feeDto.setMember_fee_id(id);
            feeDto.setMember_id(String.valueOf(cmbMemberId.getValue()));
            feeDto.setMember_name(lblName.getText());
            feeDto.setDate(String.valueOf(dateDate.getValue()));
            feeDto.setAmount(txtAmount.getText());

            boolean isUpdated = membershipFeeBO.updateMemFee(feeDto);

            if(isUpdated){
                new Alert(Alert.AlertType.CONFIRMATION,"Fee Updated !").show();
                Navigation.switchPaging(pagingPane,"MembershipFeeForm.fxml");
                MembersFormController.getInstance().getAllId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Fee doesn't Updated !").show();
            }
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) throws IOException {
        if(id.startsWith("SF")){
            Navigation.switchPaging(pagingPane,"SubscriptionFeeForm.fxml");
        }else{
            Navigation.switchPaging(pagingPane,"MembershipFeeForm.fxml");
        }
    }

    public void cmbDueTypeOnAction(ActionEvent actionEvent) {
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"SubscriptionFeeForm.fxml");
    }
}
