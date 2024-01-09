package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MeetingAttendanceBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SpecialScholarshipBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MeetingAttendanceBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SpecialScholarshipBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.SpecialScholDto;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Map;

public class SpecialScholarshipFormController {
    public AnchorPane pagingPane;
    public JFXButton btnSpecialSchol;
    public JFXButton btnScholarship;
    public JFXButton btnDeathBenefit;
    public VBox vBox;
    public Label lblBenefitId;
    public DatePicker dateDate;
    public TextField txtAmount;
    public ComboBox txtMemberId;
    public JFXButton btnAdd;
    public JFXButton btnCancel;
    public TextField txtMemberName;

    SpecialScholarshipBO scholarshipBO = new SpecialScholarshipBoImpl();
    MemberBO memberBO = new MemberBoImpl();
    MeetingAttendanceBO meetingAttendanceBO = new MeetingAttendanceBoImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        selectEligibleMembers();
        ChangeButton.btnSelected(btnSpecialSchol);
        generateNextScholId();
        getAllId();
    }

    private void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = scholarshipBO.getAllSpecialScholId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(SpecialScholarshipFormController.class.getResource("/View/DonationBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            DonationBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateNextScholId() throws SQLException, ClassNotFoundException {
        lblBenefitId.setText(scholarshipBO.generateNewSpecialScholId());
    }

    private void selectEligibleMembers() throws SQLException, ClassNotFoundException {
        LocalDate currentDate = LocalDate.now();
        ArrayList<String> eligibleMemberList = new ArrayList<>();


        Map<String, LocalDate> memberJoinedDates = memberBO.calculateMemberDuration();

        LocalDate joined_date ;
        for(Map.Entry<String,LocalDate> entry : memberJoinedDates.entrySet()){
            String member_id = entry.getKey();
            joined_date = entry.getValue();
        }

        Map<String, Double> meetingAttendance = meetingAttendanceBO.calculateMeetingAttendance();
        for (Map.Entry<String, Double> entry : meetingAttendance.entrySet()){
            String memberId = entry.getKey();
            Double attendance = entry.getValue();

            if(attendance > 80){
                LocalDate joinedDate = memberJoinedDates.get(memberId);
                Period period = Period.between(joinedDate, currentDate);
                int years = period.getYears();

                if(years > 5){
                    eligibleMemberList.add(memberId);
                    System.out.println(eligibleMemberList);
                }
            }
        }
        setDataInComboBox(eligibleMemberList);
        System.out.println(eligibleMemberList);
        //getEligibleId(eligibleMemberList);
    }

    private void getEligibleId(ArrayList<String> eligibleMemberList) {
        vBox.getChildren().clear();
        for(int i = 0; i< eligibleMemberList.size(); i++){
            //loadTableData(eligibleMemberList.get(i));
        }
    }

//    private void loadTableData(String id) {
//        try {
//            FXMLLoader loader = new FXMLLoader(SpecialScholarshipFormController.class.getResource("/View/DonationBarForm.fxml"));
//            Parent root = null;
//            root = loader.load();
//            DonationBarFormController controller = loader.getController();
//            controller.setSpecialScholData(id);
//            vBox.getChildren().add(root);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    private void setDataInComboBox(ArrayList<String> eligibleMemberList) {
        txtMemberId.getItems().addAll(eligibleMemberList);
    }

    public void btnSpecialScholOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnSpecialSchol);
        ChangeButton.btnUnselected(btnDeathBenefit);
        ChangeButton.btnUnselected(btnScholarship);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnCancel);
    }

    public void btnScholarshipOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"ScholarshipForm.fxml");
    }

    public void btnDeathBenefitOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"DeathBenefitForm.fxml");
    }

    public void btnAddOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        ChangeButton.btnSelected(btnSpecialSchol);
        ChangeButton.btnSelected(btnAdd);
        ChangeButton.btnUnselected(btnDeathBenefit);
        ChangeButton.btnUnselected(btnScholarship);
        ChangeButton.btnUnselected(btnCancel);

        String schoId = lblBenefitId.getText();
        String memberId = String.valueOf(txtMemberId.getValue());
        String name = txtMemberName.getText();
        String date = String.valueOf(dateDate.getValue());
        String amount = txtAmount.getText();

        SpecialScholDto scholDto = new SpecialScholDto(schoId, memberId, name, date, amount);
        boolean isSaved = scholarshipBO.saveSpecialSchol(scholDto);
        if(isSaved){
           // clearFeilds();
            generateNextScholId();
            getAllId();
        }
    }

    public void btnCancelOnAction(ActionEvent actionEvent) {
        ChangeButton.btnSelected(btnSpecialSchol);
        ChangeButton.btnUnselected(btnAdd);
        ChangeButton.btnUnselected(btnDeathBenefit);
        ChangeButton.btnUnselected(btnScholarship);
        ChangeButton.btnSelected(btnCancel);
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"DeathBenefitForm.fxml");
    }
}
