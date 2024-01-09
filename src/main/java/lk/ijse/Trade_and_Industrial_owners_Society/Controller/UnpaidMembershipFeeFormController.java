package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MembershipFeeBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.SubscriptionFeeBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MembershipFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.SubscriptionFeeBO;
import lk.ijse.Trade_and_Industrial_owners_Society.SendText;
import lk.ijse.Trade_and_Industrial_owners_Society.Utill.Navigation;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;
import java.util.ArrayList;

import static lk.ijse.Trade_and_Industrial_owners_Society.Utill.ChangeButton.btnSelected;

public class UnpaidMembershipFeeFormController {
    public AnchorPane pagingPane;
    public JFXButton btnInformAll;
    public VBox vBox;
    private static UnpaidMembershipFeeFormController controller;

    public UnpaidMembershipFeeFormController(){controller = this;}

    public static UnpaidMembershipFeeFormController getInstance(){return controller;}

    MemberBO memberBO = new MemberBoImpl();
    MembershipFeeBO membershipFeeBO = new MembershipFeeBoImpl();

    public void initialize() throws SQLException, ClassNotFoundException {
        getAllId();
    }

    public void getAllId() throws SQLException, ClassNotFoundException {
        ArrayList<String> list = null;
        list = membershipFeeBO.getAllUnpaidMembershipFeeId();

        vBox.getChildren().clear();
        for(int i = 0; i< list.size(); i++){
            loadTableData(list.get(i));
        }
    }

    private void loadTableData(String id) {
        try {
            FXMLLoader loader = new FXMLLoader(UnpaidMembershipFeeFormController.class.getResource("/View/UnpaidFeeBarForm.fxml"));
            Parent root = null;
            root = loader.load();
            UnpaidFeeBarFormController controller = loader.getController();
            controller.setData(id);
            vBox.getChildren().add(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void btnInformAllOnAction(ActionEvent actionEvent) throws SQLException, GeneralSecurityException, IOException, MessagingException, ClassNotFoundException {
        btnSelected(btnInformAll);
        ArrayList<String> emailList = memberBO.getAllMemberEmailAddress_mem();
        System.out.println(emailList);

        for (int i = 0; i < emailList.size(); i++) {
            String email = emailList.get(i);
            System.out.println(email);
            new SendText().sendMail("Reminder","Pay this year's your membership fee as soon as possible.!" ,email);
        }
    }

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        Navigation.switchPaging(pagingPane,"DashboardForm.fxml");
    }
}
