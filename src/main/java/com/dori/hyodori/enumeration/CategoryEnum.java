package com.dori.hyodori.enumeration;

public enum CategoryEnum {
    SATISFACTION("만족도"),
    INTEREST("흥미"),
    POSITIVITY("긍정"),
    EMOTION("감정"),
    SELF_ESTEEM("자존감"),
    HEALTH("건강");

    private final String displayName;

    CategoryEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
