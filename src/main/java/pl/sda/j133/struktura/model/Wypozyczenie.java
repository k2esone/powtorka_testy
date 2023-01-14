package pl.sda.j133.struktura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Wypozyczenie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int dlugoscWypozyczenia;    // ilosc dni na które wypozyczylismy
    private double cenaZaDzien;         // cena za dzien wypozyczenia

    @ManyToOne
    private Produkt produkt;

    @OneToOne
    private Zwrot zwrot;
}
