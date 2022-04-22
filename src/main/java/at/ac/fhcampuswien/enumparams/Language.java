package at.ac.fhcampuswien.enumparams;

public enum Language {

    ARABIC("ar"),
    GERMAN("de"),
    ENGLISH("en"),
    SPANISH("es"),
    FRENCH("fr"),
    HEBREW("he"),
    ITALIAN("it"),
    DUTCH("nl"),
    NORWEGIAN("no"),
    PORTUGUESE("pt"),
    RUSSIAN("ru"),
    SWEDISH("sv"),
    CHINESE("zh");

    public final String value;

    Language (String value) {
        this.value=value;
    }
}
