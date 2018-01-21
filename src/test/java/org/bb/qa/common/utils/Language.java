package org.bb.qa.common.utils;

public enum Language {
    HEBREW("he"),
    ENGLISH("en"),
    RUSSIAN("ru"),
    SPANISH("es"),
    ITALIAN("it"),
    GERMAN("de"),
    DUTCH("nl"),
    FRENCH("fr"),
    PORTUGUESE("pt"),
    TURKISH("tr"),
    POLISH("pl"),
    ARABIC("ar"),
    HUNGARIAN("hu"),
    FINNISH("fi"),
    LITHUANIAN("lt"),
    JAPANESE("ja"),
    BULGARIAN("bg"),
    GEORGIAN("ka"),
    NORWEGIAN("no"),
    SWEDISH("sv"),
    CROATIAN("hr"),
    CHINESE("zh"),
    PERSIAN("fa"),
    ROMANIAN("ro"),
    HINDI("hi"),
    UKRAINIAN("ua"),
    MACEDONIAN("mk"),
    SLOVENIAN("sl"),
    LATVIAN("lv"),
    SLOVAK("sk"),
    CZECH("cs"),
    UNKNOWN("xx"),
    DEFAULT(""),;


    private String code;

    Language(String code) {
        this.code = code;
    }

    public static Language lookup(String code) {
        for (Language lang : Language.values()) {
            if (lang.getCode().equalsIgnoreCase(code)) {
                return lang;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }
}
