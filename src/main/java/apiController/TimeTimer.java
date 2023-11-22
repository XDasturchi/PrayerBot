package apiController;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeTimer {
    private LocalTime bomdod;
    private LocalTime peshin;
    private LocalTime asr;
    private LocalTime shom;
    private LocalTime xufton;
}
