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
// - sredni czas gry @TODO
// - najdluzszy czas gry
// - najkrotszy czas gry
// - sredni czas gry z ostatnich 7 dni  @TODO
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
assertEquals(4050, result); // session1 = 90min + session2 = 45min + 30 seconds / 135 * 60 = 8100

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
assertEquals(5100, result); // 90min + 45min + 120min = 255 * 60 = 15300 / 3 = 5100
    }


}
