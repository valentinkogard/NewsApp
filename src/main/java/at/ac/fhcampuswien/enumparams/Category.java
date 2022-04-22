package at.ac.fhcampuswien.enumparams;

public enum Category {

    BUSINESS("business"),
    ENTERTAINMENT("entertainment"),
    GENERAL("general"),
    HEALTH("health"),
    SCIENCE("science"),
    SPORTS("sports"),
    TECHNOLOGY("technology");

    public final String value;

    Category (String value) {
        this.value=value;
    }
}
