package pl.sda.j133.struktura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Narty extends Produkt {
    @OneToOne
    private Zestaw zestaw; // jeśli nie jest w zestawie to zestaw == null

    @OneToMany
    private Set<Wypozyczenie> wypozyczenia;

    @Override
    public boolean sprawdzDostepnosc() {
        if (getZestaw() != null) {
//            log.info("Nie można wynająć, narty są częścią zestawu.");
            return false;
        }

        return !sprawdzCzyWypozyczone();
    }
}
