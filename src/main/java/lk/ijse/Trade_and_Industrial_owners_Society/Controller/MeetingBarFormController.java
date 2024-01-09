package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.CommitteeMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.GeneralMeetingBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.CommitteeMeetingBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.GeneralMeetingBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.CommitteeMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.GeneralMeetingDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;

public class MeetingBarFormController {
    public Label meetingId;
    public Label date;
    public Label Location;
    public ImageView btnDelete;
    public ImageView btnUpdate;

    GeneralMeetingBO generalMeetingBO = new GeneralMeetingBoImpl();
    CommitteeMeetingBO committeeMeetingBO = new CommitteeMeetingBoImpl();

    public void setData(String id){

        if(id.startsWith("G")){
            try {
                GeneralMeetingDto meetingTm = null;
                meetingTm = generalMeetingBO.getData(id);
                this.meetingId.setText(meetingTm.getGeneral_meeting_id());
                date.setText(meetingTm.getDate());
                Location.setText(meetingTm.getLocation());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }else if(id.startsWith("C")){
            try {
                CommitteeMeetingDto meetingTm = null;
                meetingTm = committeeMeetingBO.getData(id);
                this.meetingId.setText(meetingTm.getCommittee_meeting_id());
                date.setText(meetingTm.getDate());
                Location.setText(meetingTm.getLocation());
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String id = meetingId.getText();
        if(id.startsWith("G")){
            boolean isDeleted = generalMeetingBO.deleteGeneralMeeting(id);

            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"meeting deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't deleted !").show();
            }
            GeneralMeetingFormController.getInstance().getAllId();
        }else if(id.startsWith("C")){
            boolean isDeleted = committeeMeetingBO.deleteCommitteeMeeting(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"meeting deleted !").show();
            }else{
                new Alert(Alert.AlertType.ERROR,"Doesn't deleted !").show();
            }
            CommitteeMeetingFormController.getInstance().getAllId();
        }
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) throws IOException {
        MemberUpdateFormController.getId(meetingId.getText());
        if(meetingId.getText().startsWith("G")){
            Navigation.barPane("GeneralMeetingUpdateForm.fxml");
        }else if(meetingId.getText().startsWith("C")){
            //Navigation.switchNavigation("CommitteeMeetingUpdateForm.fxml", mouseEvent);
        }
    }
}
