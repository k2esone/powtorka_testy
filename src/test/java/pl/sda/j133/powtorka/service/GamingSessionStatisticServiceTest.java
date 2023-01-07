package pl.sda.j133.powtorka.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.j133.powtorka.model.GamingSession;
import pl.sda.j133.powtorka.model.User;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Liczenie statystyki czasu:
// - sredni czas gry @TODO DONE
// - najdluzszy czas gry @TODO DONE
// - najkrotszy czas gry @TODO DONE
// - sredni czas gry z ostatnich 7 dni  @TODO DONE
// - data i czas ostatniej sesji

// Liczenie statystyki rozegranych meczy:
// - srednia ilosc rozegranych meczy
// - srednie ratio zwyciestw do porazek
//      - okres 7 dni
//      - okres od poczatku (wszystkie)
//      - per sesja (oblicz jaka jest statystyka z jednej wybranej sesji)
// - wyznacz ranking top3

class GamingSessionStatisticServiceTest {

    private final GamingSessionStatistics gamingSessionStatistics = new GamingSessionStatisticService();

    @Test
    @DisplayName("Oblicza sredni czas gry per wybrana gra")
    public void test_calculateAverageSessionTimeForGivenGame() {

        User testUser = new User("irrelevant", "irrelevant");
        testUser.setGamingSessions(
                new HashSet<>(
                        List.of(
                                new GamingSession(
                                        LocalDateTime.of(2000, 1, 1, 3, 0, 0),
                                        LocalDateTime.of(2000, 1, 1, 4, 30, 0),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "fifa-01"),
                                new GamingSession(
                                        LocalDateTime.of(2000, 1, 1, 4, 55, 0),
                                        LocalDateTime.of(2000, 1, 1, 5, 40, 30),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "fifa-01"),
                                new GamingSession(
                                        LocalDateTime.of(2000, 1, 1, 6, 20, 0),
                                        LocalDateTime.of(2000, 1, 1, 8, 20, 45),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "metin-01")


                        )
                )
        );

        // TESTING
        int result = gamingSessionStatistics.calculateAverageTimeForGivenGameInSeconds(testUser, "fifa-01");
assertEquals(4050, result); // (session1 = 90min + session2 = 45min + 30 seconds) / 2 sessions = 135/2 * 60 = 4050

    }

    @Test
    @DisplayName("Oblicza sredni czas gry ze wszystkich sesji")
    public void test_calculateAverageSessionTimeTotal() {
        User testUser = new User("irrelevant", "irrelevant");
        testUser.setGamingSessions(
                new HashSet<>(
                        List.of(
                                new GamingSession(
                                        LocalDateTime.of(2000, 1, 1, 3, 0, 0),
                                        LocalDateTime.of(2000, 1, 1, 4, 30, 0),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "fifa-01"),
                                new GamingSession(
                                        LocalDateTime.of(2000, 1, 1, 4, 55, 0),
                                        LocalDateTime.of(2000, 1, 1, 5, 40, 30),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "fifa-01"),
                                new GamingSession(
                                        LocalDateTime.of(2000, 1, 1, 6, 20, 0),
                                        LocalDateTime.of(2000, 1, 1, 8, 20, 45),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "metin-01")


                        )
                )
        );

        // TESTING
int result = gamingSessionStatistics.calculateAverageSessionTimeInTotalInSeconds(testUser);
assertEquals(5125, result); // (90min + 45min + 120min + 75sec) / 3 sessions = 255 * 60 = 15300 / 3 = 5125
    }

    @Test
    @DisplayName("Oblicza sredni czas per gra z ostatnich 7 dni")
    public void test_calculateAverageSessionTimeForGivenGameFromLast7Days(){
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 1, 4, 30, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 28, 4, 55, 0),
                                LocalDateTime.of(2022, 12, 28, 5, 40, 30),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 4, 7, 0, 0),
                                LocalDateTime.of(2023, 1, 4, 9, 40, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 1, 8, 20, 45),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 29, 2, 30, 0),
                                LocalDateTime.of(2022, 12, 29, 5, 50, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01")
                )
        ));

        //TESTING
        int result = gamingSessionStatistics.calculateAverageTimeForGivenGameInSecondsLast7Days(testUser, "fifa-01");
        assertEquals(7500, result); // (90min + 160min) / 2 sessions = 125min * 60 = 7500

    }
    @Test
    @DisplayName("Oblicza sredni czas wszystkich gier z ostatnich 7 dni")
    public void test_calculateAverageSessionTimeTotalFromLast7Days(){
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 1, 4, 30, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 28, 4, 55, 0),
                                LocalDateTime.of(2022, 12, 28, 5, 40, 30),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 4, 7, 0, 0),
                                LocalDateTime.of(2023, 1, 4, 9, 40, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 1, 8, 20, 45),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 29, 2, 30, 0),
                                LocalDateTime.of(2022, 12, 29, 5, 50, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01")
                )
        ));

        //TESTING
        int result = gamingSessionStatistics.calculateAverageSessionTimeInTotalInSecondsLast7Days(testUser);
        assertEquals(7415, result); // (90min + 160min + 120min + 45 sec) / 3 sessions = 7415

    }

    @Test
    @DisplayName("Oblicza najdluzszy czas gry")
    public void test_calculateLongestGameSession(){
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 1, 4, 30, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 28, 4, 55, 0),
                                LocalDateTime.of(2022, 12, 28, 5, 40, 30),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 4, 7, 0, 0),
                                LocalDateTime.of(2023, 1, 4, 9, 40, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 1, 8, 20, 45),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 29, 2, 30, 0),
                                LocalDateTime.of(2022, 12, 29, 5, 50, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01")
                )
        ));

        //TESTING
        int result = gamingSessionStatistics.calculateLongestGameSessionInSeconds(testUser);
        assertEquals(12000, result);

    }

    @Test
    @DisplayName("Oblicza najkrotszy czas gry")
    public void test_calculateShortestGameSession(){
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 1, 4, 30, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 28, 4, 55, 0),
                                LocalDateTime.of(2022, 12, 28, 5, 40, 30),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 4, 7, 0, 0),
                                LocalDateTime.of(2023, 1, 4, 9, 40, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 1, 8, 20, 45),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 29, 2, 30, 0),
                                LocalDateTime.of(2022, 12, 29, 5, 50, 0),
                                0, // irrelevant
                                0, // irrelevant
                                0, // irrelevant
                                "metin-01")
                )
        ));

        //TESTING

        int result = gamingSessionStatistics.calculateShortestGameSessionInSeconds(testUser);
        assertEquals(2730, result);
    }


}
