package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MeetingAttendanceBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MeetingAttendanceBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MeetingAttendanceDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.TM.MeetingAttendanceTm;

import java.sql.SQLException;

public class MeetingAttendanceBarFormController {
    public Label MeetingId;
    public Label MemberID;
    public Label memberName;
    public ImageView btnDelete;
    public ImageView btnUpdate;
    MeetingAttendanceBO meetingAttendanceBO = new MeetingAttendanceBoImpl();

    public void btnDeleteOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        String meetingId = MeetingId.getText();
        boolean isDeleted = meetingAttendanceBO.deleteAttendance(meetingId, memberName.getText());

        if(isDeleted){
            new Alert(Alert.AlertType.CONFIRMATION,"Attendance Deleted !").show();
        }else {
            new Alert(Alert.AlertType.ERROR,"Doesn't Deleted !").show();
        }
        GeneralMeetingAttendanceFormController.getInstance().getAllId();
    }

    public void btnUpdateOnAction(MouseEvent mouseEvent) {

    }

    public void setDataInGeneral(String id) {
        MeetingAttendanceDto attendanceTm = null;
        try {
            attendanceTm = meetingAttendanceBO.getData(id);
            this.MeetingId.setText(attendanceTm.getMeeting_id());
            MemberID.setText(attendanceTm.getMember_id());
            memberName.setText(attendanceTm.getMember_name());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
