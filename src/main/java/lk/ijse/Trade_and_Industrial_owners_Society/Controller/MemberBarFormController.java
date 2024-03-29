package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class MemberBarFormController {
    public Label member_id;
    public Label name;
    public Label contact_no;
    public Label business_type;
    public Label nic;
    public ImageView btnDelete;
    public ImageView btnUpdate;
    public AnchorPane barPane;

    MemberBO memberBO = new MemberBoImpl();

    public void setData(String id){
        MemberDto memberTm = null;
        try {
            memberTm = memberBO.getData(id);
            this.member_id.setText(memberTm.getMember_id());
            name.setText(memberTm.getName_with_initials());
            contact_no.setText(memberTm.getPersonal_contact_num());
            business_type.setText(memberTm.getBusiness_type());
            nic.setText(memberTm.getNic());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = member_id.getText();
        boolean isDeleted = memberBO.deleteMember(id);

        if(isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"member deleted !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Doesn't deleted !").show();
        }
        MembersFormController.getInstance().getAllId();
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws IOException {
        MemberUpdateFormController.getId(member_id.getText());
        Navigation.barPane("MemberUpdateForm.fxml");
    }
}
