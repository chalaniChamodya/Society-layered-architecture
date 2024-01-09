package lk.ijse.Trade_and_Industrial_owners_Society.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.Impl.MemberBoImpl;
import lk.ijse.Trade_and_Industrial_owners_Society.BO.Custom.MemberBO;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.MemberDto;
import lk.ijse.Trade_and_Industrial_owners_Society.SendText;
import lk.ijse.Trade_and_Industrial_owners_Society.Dto.TM.MemberTm;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.sql.SQLException;

public class UnpaidFeeBarFormController {
    public Label member_id;
    public Label name;
    public Label contact_no;
    public Label business_type;
    public Label nic;
    public Button btnInform;

   MemberBO memberBO = new MemberBoImpl();

    public void setData(String id) {
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

    public void btnInformOnAction(ActionEvent actionEvent) throws SQLException, GeneralSecurityException, IOException, MessagingException, ClassNotFoundException {
        String id = member_id.getText();
        System.out.println(id);
        String email = memberBO.getMemberEmailAddress(id);
        System.out.println(email);
        new SendText().sendMail("Payment Reminder","Pay this month's your subscription fee as soon as possible.!",email);
    }
}
