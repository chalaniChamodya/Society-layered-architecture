package lk.ijse.Trade_and_Industrial_owners_Society.Dto.TM;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class FamilyMemberTm {
    private String id;
    private String member_id;
    private String relationship;
    private String isAlive;
}
