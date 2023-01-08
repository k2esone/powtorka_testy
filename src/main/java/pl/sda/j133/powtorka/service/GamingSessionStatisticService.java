package pl.sda.j133.powtorka.service;

import pl.sda.j133.powtorka.model.GamingSession;
import pl.sda.j133.powtorka.model.User;
import pl.sda.j133.powtorka.structs.GamingSessionTime;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class GamingSessionStatisticService implements GamingSessionStatistics {


    @Override
    public int calculateAverageTimeForGivenGameInSeconds(User user, String gameId) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .filter(gamingSession -> gamingSession.getGameIdentifier().equals(gameId))
                .toList();

        OptionalDouble averageTime = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .average();

        if (averageTime.isEmpty()) {
            throw new IllegalArgumentException("No average time, probably no sessions found");
        }

        return (int) averageTime.getAsDouble();
    }

    @Override
    public int calculateAverageSessionTimeInTotalInSeconds(User user) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .toList();

        OptionalDouble averageTime = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .average();

        if (averageTime.isEmpty()) {
            throw new IllegalArgumentException("No average time, probably no sessions found");
        }

        return (int) averageTime.getAsDouble();

    }

    @Override
    public int calculateAverageTimeForGivenGameInSecondsLast7Days(User user, String gameId) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .filter(gamingSession -> gamingSession.getGameIdentifier().equals(gameId))
                .toList();


        OptionalDouble averageTime = sessions.stream()
                .filter(session -> Duration.between(session.getTimeStarted(), LocalDateTime.now()).getSeconds() <= 604800)
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .average();

        if (averageTime.isEmpty()) {
            throw new IllegalArgumentException("No average time, probably no sessions found");
        }

        return (int) averageTime.getAsDouble();
    }

    @Override
    public int calculateAverageSessionTimeInTotalInSecondsLast7Days(User user) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .toList();

        OptionalDouble averageTime = sessions.stream()
                .filter(session -> Duration.between(session.getTimeStarted(), LocalDateTime.now()).getSeconds() <= 604800)
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .average();

        if (averageTime.isEmpty()) {
            throw new IllegalArgumentException("No average time, probably no sessions found");
        }

        return (int) averageTime.getAsDouble();
    }

    @Override
    public int calculateLongestGameSessionInSeconds(User user) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .toList();

        OptionalLong longestSession = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .max();

        if (longestSession.isEmpty()) {
            throw new IllegalArgumentException("No longest time, probably no sessions found");
        }

        return (int) longestSession.getAsLong();

    }

    @Override
    public int calculateShortestGameSessionInSeconds(User user) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .toList();

        OptionalLong longestSession = sessions.stream()
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .min();

        if (longestSession.isEmpty()) {
            throw new IllegalArgumentException("No shortest time, probably no sessions found");
        }

        return (int) longestSession.getAsLong();
    }

    @Override
    public LocalDateTime dateAndTimeOfTheLatestSession(User user) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .toList();

        if (sessions.isEmpty()) {
throw new IllegalArgumentException("No date and time, probably no sessions found");
        }

        LocalDateTime latestSessionDateStart = sessions.stream()
                .map(GamingSession::getTimeStarted)
                .max(LocalDateTime::compareTo)
                .get();

        LocalDateTime latestSessionDateFinish = sessions.stream()
                .map(GamingSession::getTimeFinished)
                .max(LocalDateTime::compareTo)
                .get();

        long lenght = Duration.between(latestSessionDateStart, latestSessionDateFinish).getSeconds();
        System.out.println("Czas ostatniej sesji wynosi: " + lenght + " sekund");

        return latestSessionDateStart;
    }

    @Override
    public int calculateAverageMatchesPlayedForGivenGame(User user, String gameId) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .filter(session -> session.getGameIdentifier().equals(gameId))
                .toList();

        OptionalDouble averageMatches = sessions.stream()
                .mapToLong(GamingSession::getMatchesPlayed)
                .average();

        if (averageMatches.isEmpty()) {
            throw new IllegalArgumentException("No average matches, probably no sessions found");
        }

        return (int) averageMatches.getAsDouble();


    }

    @Override
    public int calculateAverageMatchesPlayedInTotal(User user) {
        List<GamingSession> sessions = user.getGamingSessions()
                .stream()
                .toList();

        OptionalDouble averageMatches = sessions.stream()
                .mapToLong(GamingSession::getMatchesPlayed)
                .average();

        if (averageMatches.isEmpty()) {
            throw new IllegalArgumentException("No average matches, probably no sessions found");
        }

        return (int) averageMatches.getAsDouble();

    }

    @Override
    public int calculateAverageRatioWinToLoseForGivenGame(User user, String gameId) {
        return 0;
    }

    @Override
    public int calculateAverageRatioWinToLoseInTotal(User user) {
        return 0;
    }












    @Override
    public int calculateAverageSessionTimeInLast7DaysSeconds(User user) {
        OptionalDouble averageTime = user.getGamingSessions().stream()
                .filter(gamingSession -> gamingSession.getTimeStarted().isAfter(LocalDateTime.now().minusDays(7)))
                .mapToLong(session -> Duration.between(session.getTimeStarted(), session.getTimeFinished()).getSeconds())
                .average();

        if (averageTime.isEmpty()) {
            throw new IllegalArgumentException("No average time, probably no sessions found");
        }

        return (int) averageTime.getAsDouble();
    }

    @Override
    public LocalDateTime findLastSessionDateTime(User user) {
//        GamingSession gamingSession = user.getGamingSessions().stream()
//                .max(Comparator.comparing(GamingSession::getTimeStarted))
//                .orElseThrow(() -> new IllegalArgumentException("No session found."));

        Optional<GamingSession> gamingSessionOptional = user.getGamingSessions().stream()
                .max(Comparator.comparing(GamingSession::getTimeStarted));

        if (gamingSessionOptional.isEmpty()) {
            throw new IllegalArgumentException("No session found.");
        }

        return gamingSessionOptional.get().getTimeStarted();
    }

    @Override
    public GamingSessionTime findLastSessionTimes(User user) {
        Optional<GamingSession> gamingSessionOptional = user.getGamingSessions().stream()
                .max(Comparator.comparing(GamingSession::getTimeStarted));

        if (gamingSessionOptional.isEmpty()) {
            throw new IllegalArgumentException("No session found.");
        }

        GamingSession gamingSession = gamingSessionOptional.get();
        return new GamingSessionTime(
                gamingSession.getTimeStarted(),
                gamingSession.getTimeFinished(),
                Duration.between(gamingSession.getTimeStarted(), gamingSession.getTimeFinished()).getSeconds()
        );
    }

}
