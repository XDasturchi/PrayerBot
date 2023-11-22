package apiController;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TimerRepository {

    private static final TimerRepository timerRepository = new TimerRepository();
    private TimeTimer timer = null;

    public void add(TimeTimer timer) {
        this.timer = timer;
    }

    public TimeTimer getTimer() {
        return timer;
    }

    public static TimerRepository getInstance() {
        return timerRepository;
    }


}
