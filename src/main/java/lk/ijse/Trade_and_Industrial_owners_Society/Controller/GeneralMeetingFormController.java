package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.GeneralMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.CommitteeMeetingBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.GeneralMeetingBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.QueryBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.QueryBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.SendText;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class GeneralMeetingFormController {
    public AnchorPane pagingPane;
    public JFXButton btnCommittee;
    public JFXButton btnGeneral;
    public VBox vBox;
    public TextField txtMeetingId;
    public DatePicker dateDate;
    public TextField timeTime;
    public TextField txtLocation;
    public TextField txtPurpose;
    public ComboBox txtMeetingType;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public JFXButton btnGeneralAttendance;
    public String meetingType;
    public String meetingId;
    private static GeneralMeetingFormController controller;

    GeneralMeetingBO generalMeetingBO = new GeneralMeetingBoImpl();
    CommitteeMeetingBO committeeMeetingBO = new CommitteeMeetingBoImpl();
    MemberBO memberBO = new MemberBoImpl();
    QueryBO queryBO = new QueryBoImpl();

    public GeneralMeetingFormController(){
        controller = this;
    }

    public static GeneralMeetingFormController getInstance(){
        return controller;
    }

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
        setDataInComboBox();
        ChangeButton.btnSelected(btnGeneral);
    }

    private void setDataInComboBox() {
        ArrayList<String> meetingType = new ArrayList<>();
        meetingType.add("General Meeting");
        meetingType.add("Committee Meeting");

        txtMeetingType.getItems().addAll(meetingType);
    }

    public String getMeetingType(){
        return (String) txtMeetingType.getValue();
    }

    private void generateNextMeetingId(String meetingType) {
        try {
            if (meetingType.equals("General Meeting")) {
                meetingId = generalMeetingBO.generateNewGeneralMeetingId();
                txtMeetingId.setText(meetingId);
            }else if(meetingType.equals("Committee Meeting")){
                meetingId = committeeMeetingBO.generateNewCommitteeMetingId();
                txtMeetingId.setText(meetingId);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
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
            FXMLLoader loader = new FXMLLoader(GeneralMeetingFormController.class.getResource("/View/MeetingBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            MeetingBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnCommitteeOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"CommitteeMeetingForm.fxml");
    }

    public void btnGeneralOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnGeneral);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
        ChangeButton.btnUnselected(btnCommittee);
        ChangeButton.btnUnselected(btnGeneralAttendance);
    }

    public void btnGeneralMeetingAttendanceOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"GeneralMeetingAttendanceForm.fxml");
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, GeneralSecurityException, IOException, MessagingException {
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnSelected(btnGeneral);
        ChangeButton.btnUnselected(btnCancel);
        ChangeButton.btnUnselected(btnCommittee);
        ChangeButton.btnUnselected(btnGeneralAttendance);

        String meetingType = getMeetingType();
        if(meetingType.equals("General Meeting")){
            String id = txtMeetingId.getText();
            String date = String.valueOf(dateDate.getValue());
            String time = timeTime.getText();
            String description = txtPurpose.getText();
            String location = txtLocation.getText();

            GeneralMeetingDto generalMeetingDto = new GeneralMeetingDto(id, date, time, description, location);

            boolean isSaved = generalMeetingBO.saveGeneralMeeting(generalMeetingDto);

            //String email= null;

            System.out.println(isSaved);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"meeting Saved!").show();
                getAllId();
                clearFeilds();
                ArrayList<String> emailList = memberBO.getAllMemberEmailAddress_mem();
                System.out.println(emailList.size());
                for (int i = 0; i < emailList.size(); i++) {
                    String email = emailList.get(i);
                    String msg = "Meeting Type : General Meeting" + "\nDate : "+ generalMeetingDto.getDate() + "\nTime : "+ generalMeetingDto.getTime() +"\nLocation : "+ generalMeetingDto.getLocation() ;
                    new SendText().sendMail("Next General Meeting", msg,email);
                }
                Navigation.switchPaging(pagingPane,"GeneralMeetingForm.fxml");
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't Saved Yet!").show();
            }
        }else if(meetingType.equals("Committee Meeting")){
            String id = txtMeetingId.getText();
            String date = String.valueOf(dateDate.getValue());
            String time = timeTime.getText();
            String description = txtPurpose.getText();
            String location = txtLocation.getText();

            CommitteeMeetingDto committeeMeetingDto = new CommitteeMeetingDto(id, date, time, description, location);

            boolean isSaved = committeeMeetingBO.saveCommitteeMeting(committeeMeetingDto);

            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"meeting Saved!").show();
                getAllId();
                clearFeilds();
                ArrayList<String> emailList = queryBO.getCommitteeMemberMailAddress();
                System.out.println(emailList.size());
                for (int i = 0; i < emailList.size(); i++) {
                    String email = emailList.get(i);
                    String msg = "Meeting Type : Committee Meeting" + "\nDate : "+ committeeMeetingDto.getDate() + "\nTime : "+ committeeMeetingDto.getTime() +"\nLocation : "+ committeeMeetingDto.getLocation() ;
                    new SendText().sendMail("Next Committee Meeting", msg,email);
                }
              Navigation.switchPaging(pagingPane,"CommitteeMeetingForm.fxml");
            }else{
                new Alert(Alert.AlertType.ERROR, "Doesn't Saved Yet!").show();
            }
        }
    }

    private void clearFeilds() {
        //dateDate.setValue(LocalDate.parse(""));
        txtLocation.setText("");
        txtMeetingType.setValue("");
        timeTime.setText("");
        txtPurpose.setText("");
        txtMeetingId.setText("");
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnSelected(btnGeneral);
        ChangeButton.btnSelected(btnCancel);
        ChangeButton.btnUnselected(btnCommittee);
        ChangeButton.btnUnselected(btnGeneralAttendance);

        clearFeilds();
    }

    public void cmbMeetingTypeOnAction(ActionEvent actionEvent) {
        meetingType = getMeetingType();
        generateNextMeetingId(meetingType);
    }

    public void EnterOnDate(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            keyEvent.consume();
            timeTime.requestFocus();
        }
    }

    public void EnterOnTime(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            keyEvent.consume();
            txtLocation.requestFocus();
        }
    }

    public void EnterOnLocation(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String Locaton = txtLocation.getText();
            boolean isBusinesAddressValidated = Pattern.matches("[A-Za-z\\s]{3,}",Locaton);
            if (!isBusinesAddressValidated) {
                new Alert(Alert.AlertType.ERROR, "Invalid Location").show();
            }
            keyEvent.consume();
            txtPurpose.requestFocus();
        }
    }
}
