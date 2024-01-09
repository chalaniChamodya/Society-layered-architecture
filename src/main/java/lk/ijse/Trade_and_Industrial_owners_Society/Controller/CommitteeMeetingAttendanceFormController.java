package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.util.NavigableMap;

public class CommitteeMeetingAttendanceFormController {
    public AnchorPane pagingPane;
    public VBox vBox;
    public TextField txtMemberNme;
    public TextField txtTme;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public ComboBox cmbGenMetingId;
    public ComboBox cmbMemberID;


    public void initialize(){
    }

    public void btnAddOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnCancel);
        ChangeButton.btnUnselected(btnAdd);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"CommitteeMeetingForm.fxml");
    }
}
