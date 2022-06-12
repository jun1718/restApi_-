package com.nhnacademy.exam.gateway.gateEnum.member;

public enum MemberAuthorityEnum {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    private final String value;
    MemberAuthorityEnum(String value) {
        this.value = value;
    }

    public static String getAuthorityThroughParameter(String value) {
        String valueUpper = value.toUpperCase();
        return MemberAuthorityEnum.valueOf(valueUpper).getValue();
    }

    public String getValue() {
        return value;
    }
}
