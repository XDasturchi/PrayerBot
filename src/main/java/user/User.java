package user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class User {
    private long userId;
    private State state;
    private boolean morningStatus = true;      // Bomdod
    private boolean noonStatus = true;         // Peshin
    private boolean afternoonStatus = true;    // Asr
    private boolean eveningStatus = true;      // Shom
    private boolean nightStatus = true;        // Xufton

    public User(long user_id, State state) {
        this.userId = user_id;
        this.state = state;
    }
}
