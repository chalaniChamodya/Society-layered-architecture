package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MembershipFeeBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SubscriptionFeeBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MembershipFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SubscriptionFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MembershipFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SubscriptionFeeDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class MembershipDueBarFormController {
    public Label fee_id;
    public Label memberName;
    public Label date;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    SubscriptionFeeBO subscriptionFeeBO = new SubscriptionFeeBoImpl();
    MembershipFeeBO membershipFeeBO = new MembershipFeeBoImpl();

    public void setData(String id) {

        if(id.startsWith("SF")){
            try {
                SubscriptionFeeDto dueTm = null;
                dueTm = subscriptionFeeBO.getData(id);
                this.fee_id.setText(dueTm.getSubscription_fee_id());
                memberName.setText(dueTm.getMember_name());
                date.setText(dueTm.getDate());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(id.startsWith("MF")){
            try {
                MembershipFeeDto dueTm = null;
                dueTm = membershipFeeBO.getData(id);
                this.fee_id.setText(dueTm.getMember_fee_id());
                memberName.setText(dueTm.getMember_name());
                date.setText(dueTm.getDate());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = fee_id.getText();
        if(id.startsWith("SF")){
            boolean isDeleted = subscriptionFeeBO.deleteSubFee(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Fee Deleted!").show();
                SubscriptionFeeFormController.getInstance().getAllId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't Deleted!").show();
            }
        }else if(id.startsWith("MF")){
            boolean isDeleted = membershipFeeBO.deleteMemFee(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION, "Fee Deleted!").show();
               // MembershipDuesFormController.getInstance().getAllId();
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't Deleted!").show();
            }
        }
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws IOException {
        SubscriptionFeeUpdateFormController.getId(fee_id.getText());
        if(fee_id.getText().startsWith("SF")){
            Navigation.barPane("SubscriptionFeeUpdateForm.fxml");
        }else if(fee_id.getText().startsWith("MF")){
            Navigation.barPane("SubscriptionFeeUpdateForm.fxml");
        }
    }
}
