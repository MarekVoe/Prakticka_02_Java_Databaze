package dev.marekvoe.prakticka_02;

public enum Zobrazeni {
    DOSTUPNE_KNIHY("Dostupné knihy"),
    CTENARI("Čtenáři"),
    VYPUJCKY("Výpujčky"),
    NEVYPUJCENE("Nevypujčené knihy");

    final String name;
    Zobrazeni(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
