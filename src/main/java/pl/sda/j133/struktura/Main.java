package pl.sda.j133.struktura;

import pl.sda.j133.struktura.model.Buty;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        Buty.ButyBuilder butyBuilder = Buty.builder();

        Buty buty = butyBuilder // 5
                .zestaw(null)
//                .id(5L)
                .wypozyczenia(new HashSet<>())
                .build();

        Buty buty2 = butyBuilder // 5
//                .setId(6L)
                .build();
    }
}
