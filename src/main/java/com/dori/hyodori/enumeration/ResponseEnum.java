package com.dori.hyodori.enumeration;

public enum ResponseEnum {
    YES("네"),
    NO("아니요");

    private final String displayName;

    ResponseEnum(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
