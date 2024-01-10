package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.BOFactory;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.CommitteeMemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class CommitteeMemberBarFormController {
    private static String id;
    public Label comMemId;
    public Label name;
    public Label position;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    CommitteeMemberBO committeeMemberBO = (CommitteeMemberBO) BOFactory.getBoFactory().getTypes(BOFactory.BOTypes.COMMITTEE_MEMBER);

    public static void getId(){CommitteeMemberBarFormController.id = id;}

    public void setData(String id) {
        CommitteeMemberDto tm = null;
        try {
            tm = committeeMemberBO.getData(id);
            this.comMemId.setText(tm.getCom_mem_id());
            name.setText(tm.getName());
            position.setText(tm.getPosition());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = comMemId.getText();
        boolean isDeleted = committeeMemberBO.deleteCommitteeMember(id);

        if(isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Committee member deleted !").show();
        }else{
            new Alert(Alert.AlertType.ERROR,"Doesn't deleted !").show();
        }
        CommitteeMemberFormController.getInstance().getAllId();
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws IOException {
        CommitteeMemberUpdateFormController.getId(comMemId.getText());
        Navigation.barPane("CommitteeMemberUpdateForm.fxml");
    }
}
