package pl.sda.j133.powtorka.service;

import pl.sda.j133.powtorka.model.GamingSession;
import pl.sda.j133.powtorka.model.User;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.LongStream;
import java.util.stream.Stream;

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

        LocalDateTime latestSessionDate = sessions.stream()
                .map(GamingSession::getTimeStarted)
                .max(LocalDateTime::compareTo)
                .get();




        return latestSessionDate;
    }
}
