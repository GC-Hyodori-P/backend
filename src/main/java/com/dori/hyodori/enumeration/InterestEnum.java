package com.dori.hyodori.enumeration;

public enum InterestEnum {
    EXERCISE("운동 및 산책"),
    MEDITATION("명상과 힐링"),
    MUSIC("음악 감상"),
    READING("독서와 글쓰기"),
    GARDENING("원예와 식물키우기"),
    CRAFTS("손으로 하는 취미"),
    RELIGION("종교"),
    MOVIES("영화 및 드라마"),
    HEALTHY_DIET("건강 식단"),
    NEWS("뉴스 및 정치");

    private final String displayName;
    InterestEnum(String displayName) {
        this.displayName = displayName;
    }
    public String getDisplayName() {
        return displayName;
    }

    public static InterestEnum fromDisplayName(String displayName) {
        for (InterestEnum interest : values()) {
            if (interest.getDisplayName().equals(displayName)) {
                return interest;
            }
        }
        throw new IllegalArgumentException("Unknown interest: " + displayName);
    }

}
