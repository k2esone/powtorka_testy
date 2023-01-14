package pl.sda.j133.struktura.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SkuterSniezny extends Produkt {
    @OneToMany
    private Set<Wypozyczenie> wypozyczenia;

    @Override
    public boolean sprawdzDostepnosc() {
        return !sprawdzCzyWypozyczone();
    }
}
