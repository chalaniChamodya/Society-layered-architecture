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
import lk.ijse.Trade_and_Industrial_owners_Society.SendText;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;

public class MembershipFeeFormController {
    public AnchorPane pagingPane;
    public JFXButton btnMembership;
    public JFXButton btnSubscription;
    public VBox vBox;
    public DatePicker dateDate;
    public ComboBox cmbMemberId;
    public Label lblName;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public ComboBox cmbDueType;
    public Label lblDueID;
    public TextField txtAmount;
    public static MembershipFeeFormController controller;
    public  String dueId;
    public String dueType;
    public String name;

    SubscriptionFeeBO subscriptionFeeBO = new SubscriptionFeeBoImpl();
    MembershipFeeBO membershipFeeBO = new MembershipFeeBoImpl();
    MemberBO memberBO = new MemberBoImpl();

    public MembershipFeeFormController(){controller = this;}

    public static MembershipFeeFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        ChangeButton.btnSelected(btnMembership);
        setDataInDueComboBox();
        setDataInMemberIdComboBox();
    }

    private void setDataInMemberIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> memberId = memberBO.getAllMemberId();
        cmbMemberId.getItems().addAll(memberId);
    }

    private void setDataInDueComboBox() {
        ArrayList<String> dueTypes = new ArrayList<>();
        dueTypes.add("Membership Fee");
        dueTypes.add("Subscription Fee");

        cmbDueType.getItems().addAll(dueTypes);
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = membershipFeeBO.getAllMemFeeId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(MembershipFeeFormController.class.getResource("/View/MembershipDueBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            MembershipDueBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnMembershipOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnMembership);
        ChangeButton.btnUnselected(btnSubscription);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void btnSubscriptionOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"SubscriptionFeeForm.fxml");
    }

    public void cmbMemberIdOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        name = memberBO.getMemberName(String.valueOf(cmbMemberId.getValue()));
        lblName.setText(name);
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        ChangeButton.btnUnselected(btnSubscription);
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnSelected(btnMembership);
        ChangeButton.btnUnselected(btnCancel);

        String dueType = (String) cmbDueType.getValue();
        if(dueType.equals("Membership Fee")){
            String id = lblDueID.getText();
            System.out.println(id);
            String member_id = String.valueOf(cmbMemberId.getValue());
            String member_name = lblName.getText();
            String date = String.valueOf(dateDate.getValue());
            String amount = txtAmount.getText();

            String email = memberBO.getMemberEmailAddress(member_id);

            MembershipFeeDto membershipFeeDto = new MembershipFeeDto(id, member_id, member_name, date, amount);

            boolean isSaved = membershipFeeBO.saveMemFee(membershipFeeDto);

            if(isSaved){
                Navigation.switchPaging(pagingPane,"MembershipFeeForm.fxml");
                try {
                    new SendText().sendMail("Payment receipt","Payment successful!",email);
                } catch (GeneralSecurityException | IOException | MessagingException e) {
                    e.printStackTrace();
                }
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't Saved !").show();
            }
        }else if(dueType.equals("Subscription Fee")){
            String id = lblDueID.getText();
            String member_id = String.valueOf(cmbMemberId.getValue());
            String member_name = lblName.getText();
            String date = String.valueOf(dateDate.getValue());
            String amount = txtAmount.getText();

            String email = memberBO.getMemberEmailAddress(member_id);
            System.out.println(email);

            SubscriptionFeeDto subscriptionFeeDto = new SubscriptionFeeDto(id, member_id, member_name, date, amount);

            boolean isSaved = subscriptionFeeBO.saveSubFee(subscriptionFeeDto);

            if(isSaved){
                try {
                    new SendText().sendMail("Payment Receipt","Payment Successful!",email);
                } catch (GeneralSecurityException | IOException | MessagingException e) {
                    e.printStackTrace();
                }
                Navigation.switchPaging(pagingPane,"SubscriptionFeeForm.fxml");
            }else{
                new Alert(Alert.AlertType.ERROR, "Doesn't Saved Yet!").show();
            }
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnUnselected(btnSubscription);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnSelected(btnMembership);
        ChangeButton.btnSelected(btnCancel);
    }

    public void cmbDueTypeOnAction(ActionEvent actionEvent) {
        dueType = (String) cmbDueType.getValue();
        generateNextDueId(dueType);
    }

    private void generateNextDueId(String dueType) {
        try {
            if (dueType.equals("Membership Fee")) {
                dueId = membershipFeeBO.generateNewMemFeeId();
                lblDueID.setText(dueId);
            }else if(dueType.equals("Subscription Fee")){
                dueId = subscriptionFeeBO.generateNewSubFeeId();
                lblDueID.setText(dueId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"SubscriptionFeeForm.fxml");
    }
}
