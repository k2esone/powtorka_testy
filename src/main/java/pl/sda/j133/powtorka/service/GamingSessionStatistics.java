package pl.sda.j133.powtorka.service;

import pl.sda.j133.powtorka.model.User;

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

}
