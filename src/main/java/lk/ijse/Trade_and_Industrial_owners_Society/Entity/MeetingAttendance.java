package lk.ijse.Trade_and_Industrial_owners_Society.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class MeetingAttendance {
    private String meeting_id;
    private String member_id;
    private String member_name;
    private String date;
    private String time;
}
