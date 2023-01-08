package pl.sda.j133.powtorka.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Duration;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class GamingSession {
    private LocalDateTime timeStarted;
    private LocalDateTime timeFinished;

    private int matchesPlayed; // ile meczy rozegral uzytkownik

    private int matchesWon; // ile meczy wygral
    private int matchesLost; // ile meczy przegral

    private String gameIdentifier;  // identyfikator gry do jej rozroznienia
                                    // fifa-01
                                    // cs-1.6-01

    public long getSessionDuration() {
        return Duration.between(timeStarted, timeFinished).getSeconds();
    }

}
