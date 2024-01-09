package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.FamilyMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.FamilyMemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.FamilyMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class FamilyMemberBarFormController {
    private static String id;
    public Label fam_mem_Id;
    public Label memberId;
    public Label relationship;
    public Label isAlive;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    FamilyMemberBO familyMemberBO = new FamilyMemberBoImpl();

    public static void getId(String id){
        FamilyMemberBarFormController.id = id;
    }

    public void setData(String id) {
        FamilyMemberDto familyTm = null;

        try {
            familyTm = familyMemberBO.getData(id);
            this.memberId.setText(familyTm.getMember_id());
            fam_mem_Id.setText(familyTm.getFamily_mem_id());
            relationship.setText(familyTm.getRelationship());
            isAlive.setText(familyTm.getIsAlive());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = fam_mem_Id.getText();
        boolean isDeleted = familyMemberBO.deleteFamilyMember(id);

        if(isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"member deleted !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Doesn't deleted !").show();
        }

        FamilyMemberFormController.getInstance().getAllId();
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws IOException {
        FamilyMemberUpdateFormController.getId(fam_mem_Id.getText());
        Navigation.barPane("FamilyMemberUpdateForm.fxml");
    }
}
