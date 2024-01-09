package lk.ijse.Trade_and_Industrial_owners_Society.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MembershipFee {
    private String member_fee_id;
    private  String member_id;
    private String member_name;
    private String date;
    private String amount;
}
