package pl.sda.j133.powtorka.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;


@Getter
@Setter
@ToString
public class User {
@EqualsAndHashCode.Include
    private final String id;  // uuid

    private String username;

@ToString.Exclude
    private String password;

    private Set<GamingSession> gamingSessions = new HashSet<>();

    public User(String username, String password) {
        this.id = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }
}
