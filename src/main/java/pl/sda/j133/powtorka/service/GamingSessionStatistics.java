package pl.sda.j133.powtorka.service;

import pl.sda.j133.powtorka.model.User;

import java.time.LocalDateTime;

public interface GamingSessionStatistics {

    // Calculates average session time for given game in seconds.
    // @param user - user with gaming sessions
    // @param gameId - game to calculate session time.
    // @return average session time in seconds

    public int calculateAverageTimeForGivenGameInSeconds(User user, String gameId);


     // Calculates average session time for all games in seconds.
     // @param user - user with gaming sessions
     // @return average session time in seconds

    public int calculateAverageSessionTimeInTotalInSeconds(User user);

    // Calculates average session time for given game in seconds.
    // @param user - user with gaming sessions
    // @param gameId - game to calculate session time.
    // @return average session time in seconds

    public int calculateAverageTimeForGivenGameInSecondsLast7Days(User user, String gameId);

    // Calculates average session time for all games in seconds.
    // @param user - user with gaming sessions
    // @return average session time in seconds

    public int calculateAverageSessionTimeInTotalInSecondsLast7Days(User user);

    // Calculates the longest session in seconds.
    // @param user - user with gaming sessions.
    // @return longest session in seconds.
    public int calculateLongestGameSessionInSeconds(User user);

    // Calculates the shortest session in seconds.
    // @param user - user with gaming sessions.
    // @return shortest session in seconds.
    public int calculateShortestGameSessionInSeconds(User user);

    public LocalDateTime dateAndTimeOfTheLatestSession(User user);

}
