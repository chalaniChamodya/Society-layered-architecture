package lk.ijse.Trade_and_Industrial_owners_Society.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class GeneralMeeting {
    private String general_meeting_id;
    private String date;
    private String time;
    private String Description;
    private String location;
}
