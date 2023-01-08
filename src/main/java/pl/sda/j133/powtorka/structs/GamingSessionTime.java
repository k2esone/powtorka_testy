package pl.sda.j133.powtorka.structs;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GamingSessionTime {
    private final LocalDateTime timeStarted;
    private final LocalDateTime timeFinished;
    private final long duration;

    public GamingSessionTime(LocalDateTime timeStarted, LocalDateTime timeFinished, long duration) {
        this.timeStarted = timeStarted;
        this.timeFinished = timeFinished;
        this.duration = duration;
    }
}
