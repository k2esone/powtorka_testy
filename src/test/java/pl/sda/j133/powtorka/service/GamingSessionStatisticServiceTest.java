package pl.sda.j133.powtorka.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.sda.j133.powtorka.model.GamingSession;
import pl.sda.j133.powtorka.model.User;
import pl.sda.j133.powtorka.structs.GamingSessionTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

// Liczenie statystyki czasu:
// - sredni czas gry @TODO DONE
// - najdluzszy czas gry @TODO DONE
// - najkrotszy czas gry @TODO DONE
// - sredni czas gry z ostatnich 7 dni  @TODO DONE
// - data i czas ostatniej sesji @TODO DONE

// Liczenie statystyki rozegranych meczy:
// - srednia ilosc rozegranych meczy @TODO DONE
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
        assertEquals(4065, result); // (session1 = 90min + session2 = 45min + 30 seconds) / 2 sessions = 135/2 * 60 = 4065

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
    public void test_calculateAverageSessionTimeForGivenGameFromLast7Days() {
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 3, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 3, 4, 30, 0),
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
                                LocalDateTime.of(2023, 1, 3, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 3, 8, 20, 45),
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
    public void test_calculateAverageSessionTimeTotalFromLast7Days() {
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 3, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 3, 4, 30, 0),
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
                                LocalDateTime.of(2023, 1, 3, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 3, 8, 20, 45),
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
    public void test_calculateLongestGameSession() {
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
    public void test_calculateShortestGameSession() {
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

    @Test
    @DisplayName("Oblicza date i czas ostatniej sesji")
    public void test_DateAndTimeOfLastSession() {
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
        LocalDateTime result = gamingSessionStatistics.dateAndTimeOfTheLatestSession(testUser);
        assertEquals(LocalDateTime.of(2023, 1, 4, 7, 0, 0), result);
    }

    @Test
    @DisplayName("Oblicza srednia ilosc rozegranych meczy dla danej gry")
    public void test_calculateAverageMatchesPlayedForGivenGame() {
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0), // irrevelant
                                23,
                                15,
                                8,
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                7,
                                6,
                                1,
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                36,
                                11,
                                25,
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                104,
                                52,
                                52,
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                45,
                                15,
                                30,
                                "metin-01")
                )
        ));

        //TESTING
        int result = gamingSessionStatistics.calculateAverageMatchesPlayedForGivenGame(testUser, "fifa-01");
        assertEquals(22, result);
    }

    @Test
    @DisplayName("Oblicza srednia ilosc rozegranych meczy dla wszystkich gier")
    public void test_calculateAverageMatchesPlayedInTotal() {
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                23,
                                15,
                                8,
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                7,
                                6,
                                1,
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                36,
                                11,
                                25,
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                104,
                                52,
                                52,
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                LocalDateTime.of(1, 1, 1, 0, 0, 0), // irrevelant
                                45,
                                15,
                                30,
                                "metin-01")
                )
        ));

        //TESTING
        int result = gamingSessionStatistics.calculateAverageMatchesPlayedInTotal(testUser);
        assertEquals(43, result);
    }

    @Test
    @DisplayName("Srednie ratio zwyciest do porazek dla danej gry")
    public void test_averageRatioWinToLoseForGivenGame() {
        User testUser = new User("irrevelant", "irrevelant");
        testUser.setGamingSessions(new HashSet<>(
                List.of(
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 3, 0, 0),
                                LocalDateTime.of(2023, 1, 1, 4, 30, 0),
                                23, // irrelevant
                                15, // irrelevant
                                8, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 28, 4, 55, 0),
                                LocalDateTime.of(2022, 12, 28, 5, 40, 30),
                                7, // irrelevant
                                6, // irrelevant
                                1, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 4, 7, 0, 0),
                                LocalDateTime.of(2023, 1, 4, 9, 40, 0),
                                36, // irrelevant
                                11, // irrelevant
                                25, // irrelevant
                                "fifa-01"),
                        new GamingSession(
                                LocalDateTime.of(2023, 1, 1, 6, 20, 0),
                                LocalDateTime.of(2023, 1, 1, 8, 20, 45),
                                104, // irrelevant
                                52, // irrelevant
                                52, // irrelevant
                                "metin-01"),
                        new GamingSession(
                                LocalDateTime.of(2022, 12, 29, 2, 30, 0),
                                LocalDateTime.of(2022, 12, 29, 5, 50, 0),
                                45, // irrelevant
                                15, // irrelevant
                                30, // irrelevant
                                "metin-01")
                )
        ));

        //TESTING
    }

    @Test
    @DisplayName("Srednie ratio zwyciest do porazek dla wszystkich gier")
    public void test_averageRatioWinToLoseInTotal() {

    }





    @Test
    @DisplayName("Oblicza średni czas gry z sesji rozegranych podczas ostatnich 7 dni")
    public void test_calculateAverageSessionTimeFromSessionsInLast7Days() {
        // PREPARATION
        User testUser = new User("irrelevant", "irrelevant");

        long totalTimeValid = 0;
        final long numberOfSessions = 10;
        for (int i = 0; i < numberOfSessions; i++) {
            GamingSession generatedSession = createRandomGamingSessionBetweenDates(
                    LocalDateTime.now().minusDays(7).plusHours(1),
                    LocalDateTime.now(),
                    10800,
                    List.of("fifa-01", "cs-1.6-01", "metin-01"));
            totalTimeValid += generatedSession.getSessionDuration();

            testUser.getGamingSessions().add(generatedSession);
        }

        for (int i = 0; i < numberOfSessions; i++) {
            GamingSession generatedSession = createRandomGamingSessionBetweenDates(
                    LocalDateTime.now().minusDays(365),
                    LocalDateTime.now().minusDays(7).minusSeconds(1),
                    10800,
                    List.of("fifa-01", "cs-1.6-01", "metin-01"));
            testUser.getGamingSessions().add(generatedSession);
        }

        // TESTING
        int result = gamingSessionStatistics.calculateAverageSessionTimeInLast7DaysSeconds(testUser);
        assertEquals(totalTimeValid/numberOfSessions, result);
    }

    @Test
    @DisplayName("Znajduje datę i czas ostatniej sesji")
    public void test_findLastSession() {
        // PREPARATION
        final LocalDateTime expectedResult = LocalDateTime.of(2020, 2, 1, 4, 55, 0);
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
                                        expectedResult,
                                        LocalDateTime.of(2020, 2, 2, 10, 40, 30),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "fifa-01"),
                                new GamingSession(
                                        LocalDateTime.of(2020, 1, 1, 6, 20, 0),
                                        LocalDateTime.of(2020, 1, 1, 8, 20, 45),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "metin-01")
                        )
                )
        );


        // TESTING
        LocalDateTime result = gamingSessionStatistics.findLastSessionDateTime(testUser);
        assertEquals(expectedResult, result);
    }

    @Test
    @DisplayName("Znajduje datę i czas i długość ostatniej sesji")
    public void test_findLastSessionTimes() {
        // PREPARATION
        final LocalDateTime timeStart = LocalDateTime.of(2020, 2, 1, 4, 55, 0);
        final LocalDateTime timeEnd = LocalDateTime.of(2020, 2, 2, 10, 40, 30);
        final long expectedResultDuration = Duration.between(timeStart, timeEnd).getSeconds();

        final GamingSessionTime expectedResult = new GamingSessionTime(timeStart, timeEnd, expectedResultDuration);

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
                                        timeStart,
                                        timeEnd,
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "fifa-01"),
                                new GamingSession(
                                        LocalDateTime.of(2020, 1, 1, 6, 20, 0),
                                        LocalDateTime.of(2020, 1, 1, 8, 20, 45),
                                        0, // irrelevant
                                        0, // irrelevant
                                        0, // irrelevant
                                        "metin-01")
                        )
                )
        );

        // TESTING
        GamingSessionTime result = gamingSessionStatistics.findLastSessionTimes(testUser);
        assertEquals(expectedResult, result);
    }

    private GamingSession createRandomGamingSessionBetweenDates(
            LocalDateTime początekZakresuZKtóregoLosujemyPoczątekSesji,
            LocalDateTime koniecZakresuZKtóregoLosujemyPoczątekSesji,
            long maxSessionLength,
            List<String> gameNames) {
        Duration duration = Duration.between(początekZakresuZKtóregoLosujemyPoczątekSesji, koniecZakresuZKtóregoLosujemyPoczątekSesji);
        Long seconds = duration.getSeconds();

        Random generator = new Random();
        Long randomSecond = generator.nextLong(seconds);

        LocalDateTime sessionStart = początekZakresuZKtóregoLosujemyPoczątekSesji.plusSeconds(randomSecond);
        LocalDateTime sessionFinish = sessionStart.plusSeconds(generator.nextLong(maxSessionLength));

        return new GamingSession(
                sessionStart,
                sessionFinish,
                0,
                0,
                0,
                gameNames.get(generator.nextInt(gameNames.size())));
    }

}
