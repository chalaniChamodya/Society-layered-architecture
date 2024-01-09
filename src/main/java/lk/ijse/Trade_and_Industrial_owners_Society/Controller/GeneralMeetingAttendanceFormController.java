package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.GeneralMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.GeneralMeetingBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MeetingAttendanceBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MeetingAttendanceBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MeetingAttendanceDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.QRCodeReader;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import static lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton.btnSelected;
import static lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton.btnUnselected;

public class GeneralMeetingAttendanceFormController {

    public AnchorPane pagingPane;
    public VBox vBox;
    public TextField txtMemberNme;
    public TextField txtTme;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public ComboBox cmbGenMetingId;
    public ComboBox cmbMemberID;

    private static GeneralMeetingAttendanceFormController controller;
    public JFXButton btnQr;
    //public TextField txtMeetingID;

    MeetingAttendanceBO meetingAttendanceBO = new MeetingAttendanceBoImpl();
    MemberBO memberBO = new MemberBoImpl();
    GeneralMeetingBO generalMeetingBO = new GeneralMeetingBoImpl();

    public GeneralMeetingAttendanceFormController(){controller = this;}

    public static GeneralMeetingAttendanceFormController getInstance(){return controller;}

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        setDataInMeetingIdComboBox();
        setDataInMemberIdComboBox();
    }

    private void setDataInMemberIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> memberId = memberBO.getAllMemberId();
        cmbMemberID.getItems().addAll(memberId);
    }

    private void setDataInMeetingIdComboBox() throws SQLException, ClassNotFoundException {
        ArrayList<String> MeetingId = generalMeetingBO.getAllGeneralMeetingId();
        cmbGenMetingId.getItems().addAll(MeetingId);
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = generalMeetingBO.getAllGeneralMeetingId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(GeneralMeetingAttendanceFormController.class.getResource("/View/MeetingAttendanceBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            MeetingAttendanceBarFormController controller = loader.getController();
            controller.setDataInGeneral(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnSelected(btnAdd);
        btnUnselected(btnCancel);

        String meeting_id = String.valueOf(cmbGenMetingId.getValue());
        String member_id = String.valueOf(cmbMemberID.getValue());
        String name = txtMemberNme.getText();
        String time = txtTme.getText();
        String date = String.valueOf(LocalDate.now());

        MeetingAttendanceDto dto = new MeetingAttendanceDto(meeting_id,member_id,name, date,time);

        boolean isSaved = meetingAttendanceBO.save(dto);

        if(isSaved){
            getAllId();
            clearFields();
            btnUnselected(btnQr);
            btnUnselected(btnAdd);
            //Navigation.switchNavigation("FundingProgramForm.fxml", actionEvent);
        }else{
            new Alert(Alert.AlertType.ERROR, "Doesn't saved yet !").show();
        }
    }

    private void clearFields() {
        cmbGenMetingId.setValue("");
        cmbMemberID.setValue("");
        txtMemberNme.setText("");
        txtTme.setText("");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        btnSelected(btnCancel);
        btnUnselected(btnAdd);
    }

    public void btnQRScanOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        btnSelected(btnQr);
        String memberId = QRCodeReader.initWebcam();
        cmbGenMetingId.setValue(generalMeetingBO.getTodayGeneralMeetingId());
        cmbMemberID.setValue(memberId);
        txtMemberNme.setText(memberBO.getMemberName(memberId));
        txtTme.setText(String.valueOf(LocalTime.now()));
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"GeneralMeetingForm.fxml");
    }
}
