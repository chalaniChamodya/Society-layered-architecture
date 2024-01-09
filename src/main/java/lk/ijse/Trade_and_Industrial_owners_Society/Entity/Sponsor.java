package lk.ijse.Trade_and_Industrial_owners_Society.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Sponsor {
    private String sponsor_id;
    private String program_id;
    private String sponsor_name;
    private String description;
    private String date;
    private String amount;
}
